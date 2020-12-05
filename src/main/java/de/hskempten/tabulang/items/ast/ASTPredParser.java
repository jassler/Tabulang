package de.hskempten.tabulang.items.ast;

import de.hskempten.tabulang.items.*;
import de.hskempten.tabulang.items.ast.interfaces.PredAST;
import de.hskempten.tabulang.items.ast.interfaces.TermAST;
import de.hskempten.tabulang.items.ast.nodes.BinBoolAST;
import de.hskempten.tabulang.items.ast.nodes.BinRelAST;
import de.hskempten.tabulang.items.ast.nodes.PredTermAST;

import java.util.ArrayList;
import java.util.Stack;

public class ASTPredParser {

    ASTPredParser.ShuntingYardBuilder syBuilder = new ASTPredParser.ShuntingYardBuilder();

    public PredAST parse(LanguageItem originalPred) throws Exception {

        traversePred(originalPred);
        PredAST parsedPred = predParser(syBuilder.getOutput());

        return parsedPred;
    }

    private void traversePred(LanguageItem originalPred) throws Exception {
        LanguageItem actPred = originalPred;
        int i = 20;
        while (i > 0) {
            switch (actPred.getLanguageItemType()) {
                case TERM_IDENTIFIER -> syBuilder.add(((TermItem) actPred).getMyIdentifier());
                case TERM_ORDINAL -> {
                    switch (((TermItem) actPred).getMyOrdinal().getLanguageItemType()) {
                        case ORDINAL_NUMBER -> syBuilder.add(((TermItem) actPred).getMyOrdinal().getMyNumber());
                        case ORDINAL_TUPEL -> syBuilder.add(((TermItem) actPred).getMyOrdinal().getMyTupel());
                        case ORDINAL_QUOTEDSTRING -> syBuilder.add(((TermItem) actPred).getMyOrdinal().getMyQuotedString());
                        case ORDINAL_NULL -> syBuilder.add(((TermItem) actPred).getMyOrdinal());
                        default -> throw new IllegalStateException("Unexpected value: " + ((TermItem) actPred).getMyOrdinal().getLanguageItemType());
                    }
                }
                case PRED_BINRELSYM, PRED_BRACKET, PREDR_BOOL, PRED_TERM -> {
                    syBuilder.add(actPred);
                }
                case PREDR_NULL -> {
                    return;
                }

                default -> {
                    throw new Exception("ASTTermParser case not yet implemented: " + actPred.getLanguageItemType());
                }
            }
            actPred = switch (actPred.getLanguageItemType()) {
                case TERM_IDENTIFIER, TERM_ORDINAL, TERM_DIRECTIONAL, TERM_BRACKET -> ((TermItem) actPred).getMyTermR();
                case TERMR_OPERATOR -> ((TermRItem) actPred).getMyTerm();
                case PRED_BINRELSYM, PRED_BRACKET -> ((PredItem) actPred).getMyPredR();
                case PREDR_BOOL -> ((PredRItem) actPred).getMyPred();
                case PRED_TERM -> ((PredItem) actPred).getMyPredR();
                default -> {
                    throw new IllegalStateException("Unexpected value: " + actPred.getLanguageItemType());
                }
            };
            i--;
        }
    }

    private PredAST predParser(ArrayList<LanguageItem> items) throws Exception {


        LanguageItem actItem = items.get(items.size() - 1);
        items.remove(items.size() - 1);

        switch (actItem.getLanguageItemType()) {
            case PRED_BINRELSYM -> {
                TermAST leftTerm = new ASTTermParser().parse(((PredItem) actItem).getMyTerm());
                BinRelAST.BinRelSym binRelSym = switch (((PredItem) actItem).getMyBinResSym().getMyString()) {
                    case "=" -> BinRelAST.BinRelSym.EQUAL;
                    case "<" -> BinRelAST.BinRelSym.LOWER_THAN;
                    case ">" -> BinRelAST.BinRelSym.GREATER_THAN;
                    case "<=" -> BinRelAST.BinRelSym.LOWER_EQUAL_THAN;
                    case ">=" -> BinRelAST.BinRelSym.GREATER_EQUAL_THAN;
                    case "!=" -> BinRelAST.BinRelSym.NOT_EQUAL;
                    default -> throw new IllegalStateException("Unexpected value: " + ((PredItem) actItem).getMyBinResSym().getMyString());
                };
                TermAST rightTerm = new ASTTermParser().parse(((PredItem) actItem).getMySecondTerm());
                return new BinRelAST(leftTerm, binRelSym, rightTerm);
            }
            case PRED_TERM -> {
                TermAST term = new ASTTermParser().parse(((PredItem) actItem).getMyTerm());
                return new PredTermAST(term);
            }
            case PREDR_BOOL -> {
                PredAST right = predParser(items);
                PredAST left = predParser(items);
                BinBoolAST.BinBool binBool = switch (((PredRItem) actItem).getMyBinBool().getMyString()) {
                    case "and" -> BinBoolAST.BinBool.AND;
                    case "or" -> BinBoolAST.BinBool.OR;
                    case "xor" -> BinBoolAST.BinBool.XOR;
                    case "iff" -> BinBoolAST.BinBool.IFF;
                    case "impl" -> BinBoolAST.BinBool.IMPL;
                    default -> throw new IllegalStateException("Unexpected value: " + ((PredRItem) actItem).getMyBinBool().getMyString());
                };
                return new BinBoolAST(left, binBool, right);
            }
            default -> throw new IllegalStateException("Unexpected value: " + actItem.getLanguageItemType());
        }

    }


    private static class ShuntingYardBuilder {
        Stack<LanguageItem> stack;
        ArrayList<LanguageItem> output;

        public ShuntingYardBuilder() {
            this.stack = new Stack<LanguageItem>();
            this.output = new ArrayList<LanguageItem>();
        }

        public void add(LanguageItem item) {
            switch (item.getLanguageItemType()) {
                case STATEMENT_IDENTIFIER, ORDINAL_NUMBER, TUPEL_EMPTY, TUPEL_ONE, TUPEL_MULTI, ORDINAL_QUOTEDSTRING, ORDINAL_NULL,
                        PRED_BINRELSYM, PRED_BRACKET, PRED_TERM -> {
                    output.add(item);
                }
                case TERM_BRACKET -> {
                    stack.push(item);
                }
                case TERMR_BRACKET -> {
                    while (!stack.isEmpty()
                            && !stack.peek().getLanguageItemType().equals(LanguageItemType.TERM_BRACKET)) {
                        output.add(stack.pop());
                    }
                    stack.pop();
                }
                case OPERATOR_ADD, OPERATOR_SUBTRACT, OPERATOR_MULTIPLY, OPERATOR_DIVIDE,
                        OPERATOR_POWER, OPERATOR_DIV, OPERATOR_MOD, TERM_DIRECTIONAL_H, TERM_DIRECTIONAL_V,
                        PREDR_BOOL -> {
                    while (!stack.isEmpty()
                            && isHigherPrecedence(item.getLanguageItemType())) {
                        output.add(stack.pop());
                    }
                    stack.push(item);
                }
                default -> throw new IllegalStateException("Unexpected value: " + item.getLanguageItemType());
            }
        }

        public boolean isHigherPrecedence(LanguageItemType type) {
            return LanguageItemType.isLeftAssociative(type)
                    && LanguageItemType.getPrecedence(type)
                    <= LanguageItemType.getPrecedence(stack.peek().getLanguageItemType())
                    || LanguageItemType.isRightAssociative(type)
                    && LanguageItemType.getPrecedence(type)
                    < LanguageItemType.getPrecedence(stack.peek().getLanguageItemType());
        }

        public ArrayList<LanguageItem> getOutput() throws Exception {
            while (!stack.isEmpty()) {
                if (LanguageItemType.TERM_BRACKET.equals(stack.peek().getLanguageItemType())) {
                    throw new Exception("Shunting Yard Builder Term invalid");
                }
                output.add(stack.pop());
            }
            return output;
        }

    }


}
