package de.hskempten.tabulang.items.ast;

import de.hskempten.tabulang.items.*;
import de.hskempten.tabulang.items.ast.interfaces.TermAST;
import de.hskempten.tabulang.items.ast.nodes.*;

import java.util.ArrayList;
import java.util.Stack;

public class ASTTermParser {

    public static ASTTermParser instance = new ASTTermParser();

    ShuntingYardBuilder syBuilder = new ShuntingYardBuilder();

    public TermAST parse(TermItem originalTerm) throws Exception {


        traverseTerm(originalTerm);
        TermAST parsedTerm = termParser(syBuilder.getOutput());


        return parsedTerm;
    }

    private void traverseTerm(TermItem originalTerm) throws Exception {
        TermOrRItem actTerm = originalTerm;
        int i = 20;
        while (i > 0) {
            switch (actTerm.getLanguageItemType()) {
                case TERM_IDENTIFIER -> syBuilder.add(((TermItem) actTerm).getMyIdentifier());
                case TERM_ORDINAL -> {
                    switch (((TermItem) actTerm).getMyOrdinal().getLanguageItemType()) {
                        case ORDINAL_NUMBER -> syBuilder.add(((TermItem) actTerm).getMyOrdinal().getMyNumber());
                        case ORDINAL_TUPEL -> syBuilder.add(((TermItem) actTerm).getMyOrdinal().getMyTupel());
                        case ORDINAL_QUOTEDSTRING -> syBuilder.add(((TermItem) actTerm).getMyOrdinal().getMyQuotedString());
                        default -> throw new IllegalStateException("Unexpected value: " + ((TermItem) actTerm).getMyOrdinal().getLanguageItemType());
                    }
                }
                case TERMR_OPERATOR -> syBuilder.add(((TermRItem) actTerm).getMyOperator());
                case TERM_BRACKET -> {
                    syBuilder.add(actTerm);
                    traverseTerm(((TermItem) actTerm).getMyTerm());
                }
                case TERMR_BRACKET -> {
                    syBuilder.add(actTerm);
                    return;
                }
                case TERMR_NULL -> {
                    return;
                }
                default -> {
                    throw new Exception("ASTTermParser case not yet implemented: " + actTerm.getLanguageItemType());
                }
            }
            actTerm = switch (actTerm.getLanguageItemType()) {
                case TERM_IDENTIFIER, TERM_ORDINAL -> actTerm.getMyTermR();
                case TERMR_OPERATOR -> ((TermRItem) actTerm).getMyTerm();
                case TERM_BRACKET -> actTerm.getMyTermR();
                default -> {
                    throw new IllegalStateException("Unexpected value: " + actTerm.getLanguageItemType());
                }
            };
            i--;
        }
    }

    private TermAST termParser(ArrayList<LanguageItem> items) {


        switch (items.get(items.size() - 1).getLanguageItemType()) {
            case OPERATOR_ADD -> {
                items.remove(items.size() - 1);
                TermAST right = termParser(items);
                TermAST left = termParser(items);
                return new AddAST(left, right);
            }
            case OPERATOR_SUBTRACT -> {
                items.remove(items.size() - 1);
                TermAST right = termParser(items);
                TermAST left = termParser(items);
                return new SubtractAST(left, right);
            }
            case OPERATOR_MULTIPLY -> {
                items.remove(items.size() - 1);
                TermAST right = termParser(items);
                TermAST left = termParser(items);
                return new MultiplyAST(left, right);
            }
            case OPERATOR_MOD -> {
                items.remove(items.size() - 1);
                TermAST right = termParser(items);
                TermAST left = termParser(items);
                return new ModAST(left, right);
            }
            case OPERATOR_DIV -> {
                items.remove(items.size() - 1);
                TermAST right = termParser(items);
                TermAST left = termParser(items);
                return new DivAST(left, right);
            }
            case OPERATOR_DIVIDE -> {
                items.remove(items.size() - 1);
                TermAST right = termParser(items);
                TermAST left = termParser(items);
                return new DivideAST(left, right);
            }
            case OPERATOR_POWER -> {
                items.remove(items.size() - 1);
                TermAST right = termParser(items);
                TermAST left = termParser(items);
                return new PowerAST(left, right);
            }
            case ORDINAL_NUMBER -> {
                NumberAST item = new NumberAST((NumberItem) items.get(items.size() - 1));
                items.remove(items.size() - 1);
                return item;
            }
            case STATEMENT_IDENTIFIER -> {
                IdentifierAST item = new IdentifierAST(((IdentifierItem) items.get(items.size() - 1)).getMyString());
                items.remove(items.size() - 1);
                return item;
            }
            default -> throw new IllegalStateException("Unexpected value: " + items.get(items.size() - 1).getLanguageItemType());
        }

    }


    private class ShuntingYardBuilder {
        Stack<LanguageItem> stack;
        ArrayList<LanguageItem> output;

        public ShuntingYardBuilder() {
            this.stack = new Stack<LanguageItem>();
            this.output = new ArrayList<LanguageItem>();
        }

        public void add(LanguageItem item) {
            switch (item.getLanguageItemType()) {
                case STATEMENT_IDENTIFIER, ORDINAL_NUMBER -> {
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
                        OPERATOR_POWER, OPERATOR_DIV, OPERATOR_MOD -> {
                    while (!stack.isEmpty()
                            && isHigherPrecedence(item.getLanguageItemType())) {
                        output.add(stack.pop());
                    }
                    stack.push(item);
                }
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
