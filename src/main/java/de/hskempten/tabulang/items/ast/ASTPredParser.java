package de.hskempten.tabulang.items.ast;

import de.hskempten.tabulang.astNodes.*;
import de.hskempten.tabulang.astNodes.PredTermNode;
import de.hskempten.tabulang.items.*;
import de.hskempten.tabulang.tokenizer.ParseTimeException;
import de.hskempten.tabulang.tokenizer.PositionedException;
import de.hskempten.tabulang.tokenizer.TextPosition;

import java.util.ArrayList;
import java.util.Stack;

public class ASTPredParser {

    ASTPredParser.ShuntingYardBuilder syBuilder = new ASTPredParser.ShuntingYardBuilder();

    public PredicateNode parse(LanguageItemAbstract originalPred) throws PositionedException {

        traversePred(originalPred);
        PredicateNode parsedPred = predParser(syBuilder.getOutput());

        return parsedPred;
    }

    private void traversePred(LanguageItemAbstract originalPred) throws PositionedException {
        LanguageItemAbstract actPred = originalPred;
        while (true) {
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
                case PRED_BINRELSYM, PREDR_BOOL, PRED_TERM, PRED_IN, PRED_NOT, PRED_BOOLEAN, PRED_INDEX -> {
                    syBuilder.add(actPred);
                }
                case PREDR_NULL -> {
                    return;
                }
                case PRED_BRACKET -> {
                    syBuilder.add(actPred);
                    traversePred(((PredItem) actPred).getMyPred());
                }
                case PREDR_BRACKET -> {
                    syBuilder.add(actPred);
                    return;
                }
                case PRED_QUANTIFIED -> {
                    switch (((PredItem) actPred).getMyPQuantified().getLanguageItemType()) {
                        case QUANTIFIED_EXISTS -> syBuilder.add(((PredItem) actPred).getMyPQuantified().getMyExistsPred());
                        case QUANTIFIED_FORALL -> syBuilder.add(((PredItem) actPred).getMyPQuantified().getMyForallPred());
                        default -> throw new IllegalStateException("Unexpected value: " + ((PredItem) actPred).getMyPQuantified().getLanguageItemType());
                    }
                }
                case PRED_FUNCALL -> {
                    syBuilder.add(((PredItem) actPred).getMyFunCallItem());
                }
                default -> {
                    throw new ParseTimeException("ASTTermParser case not yet implemented: " + actPred.getLanguageItemType(), actPred.getTextPosition());
                }
            }
            actPred = switch (actPred.getLanguageItemType()) {
                case TERM_IDENTIFIER, TERM_ORDINAL, TERM_DIRECTIONAL, TERM_BRACKET -> ((TermItem) actPred).getMyTermR();
                case TERMR_OPERATOR -> ((TermRItem) actPred).getMyTerm();
                case PRED_BINRELSYM, PRED_BRACKET, PRED_NOT, PRED_FUNCALL, PRED_BOOLEAN, PRED_INDEX -> ((PredItem) actPred).getMyPredR();
                case PREDR_BOOL -> ((PredRItem) actPred).getMyPred();
                case PRED_TERM, PRED_IN, PRED_QUANTIFIED -> ((PredItem) actPred).getMyPredR();
                default -> {
                    throw new ParseTimeException("Unexpected value: " + actPred.getLanguageItemType(), actPred.getTextPosition());
                }
            };
        }
    }

    private PredicateNode predParser(ArrayList<LanguageItemAbstract> items) throws PositionedException {


        LanguageItemAbstract actItem = items.get(items.size() - 1);
        items.remove(items.size() - 1);
        TextPosition textPosition = actItem.getTextPosition();
        switch (actItem.getLanguageItemType()) {
            case PRED_BINRELSYM -> {
                TermNode leftTerm = new ASTTermParser().parse(((PredItem) actItem).getMyTerm());
                TermNode rightTerm = new ASTTermParser().parse(((PredItem) actItem).getMySecondTerm());
                textPosition = new TextPosition(leftTerm.getTextPosition(), rightTerm.getTextPosition());
                return switch (((PredItem) actItem).getMyBinRelSym().getMyString()) {
                    case "=" -> new EqualsNode(leftTerm, rightTerm, textPosition);
                    case "<" -> new LessThanNode(leftTerm, rightTerm, textPosition);
                    case ">" -> new GreaterThanNode(leftTerm, rightTerm, textPosition);
                    case "<=" -> new LessThanOrEqualToNode(leftTerm, rightTerm, textPosition);
                    case ">=" -> new GreaterThanOrEqualToNode(leftTerm, rightTerm, textPosition);
                    case "!=" -> new NotEqualNode(leftTerm, rightTerm, textPosition);
                    default -> throw new ParseTimeException("Unexpected value: " + ((PredItem) actItem).getMyBinRelSym().getMyString(), actItem.getTextPosition());
                };
            }
            case PRED_TERM -> {
                TermNode term = new ASTTermParser().parse(((PredItem) actItem).getMyTerm());
                return new PredTermNode(term, textPosition);
            }
            case PREDR_BOOL -> {
                PredicateNode right = predParser(items);
                PredicateNode left = predParser(items);
                textPosition = new TextPosition(right.getTextPosition(), left.getTextPosition());
                return switch (((PredRItem) actItem).getMyBinBool().getMyString()) {
                    case "and" -> new AndNode(left, right, textPosition);
                    case "or" -> new OrNode(left, right, textPosition);
                    case "xor" -> new XorNode(left, right, textPosition);
                    case "iff" -> new IffNode(left, right, textPosition);
                    case "impl" -> new ImplNode(left, right, textPosition);
                    default -> throw new ParseTimeException("Unexpected value: " + ((PredRItem) actItem).getMyBinBool().getMyString(), actItem.getTextPosition());
                };
            }
            case PRED_NOT -> {
                PredicateNode pred = new ASTPredParser().parse(((PredItem) actItem).getMyPred());
                return new NotNode(pred, textPosition);
            }
            case PRED_IN -> {
                IdentifierNode identifier = new IdentifierNode(((PredItem) actItem).getMyIdentifier().getMyString(), textPosition);
                TermNode term = new ASTTermParser().parse(((PredItem) actItem).getMyTerm());
                return new InTupleNode(identifier, term, textPosition);
            }
            case PRED_INDEX -> {
                QuotedStringItem quotedString = ((PredItem) actItem).getMyTerm().getMyOrdinal().getMyQuotedString();
                IdentifierNode identifier = new IdentifierNode(quotedString.getMyString(), quotedString.getTextPosition());
                TermNode rightTerm = new ASTTermParser().parse(((PredItem) actItem).getMySecondTerm());
                return switch (((PredItem) actItem).getMyBinRelSym().getMyString()) {
                    case "=" -> new EqualsNode(identifier, rightTerm, textPosition);
                    case "<" -> new LessThanNode(identifier, rightTerm, textPosition);
                    case ">" -> new GreaterThanNode(identifier, rightTerm, textPosition);
                    case "<=" -> new LessThanOrEqualToNode(identifier, rightTerm, textPosition);
                    case ">=" -> new GreaterThanOrEqualToNode(identifier, rightTerm, textPosition);
                    case "!=" -> new NotEqualNode(identifier, rightTerm, textPosition);
                    default -> throw new ParseTimeException("Unexpected value: " + ((PredItem) actItem).getMyBinRelSym().getMyString(), actItem.getTextPosition());
                };
            }
            case QUANTIFIED_EXISTS -> {
                ExistsPredItem exi = ((ExistsPredItem) actItem);
                String string = exi.getMyIdentifier().getMyString();
                TermNode term = new ASTTermParser().parse(exi.getMyTerm());
                PredicateNode pred = new ASTPredParser().parse(exi.getMyPred());
                return new ExistsSuchThatNode(term, pred, string, textPosition);
            }
            case QUANTIFIED_FORALL -> {
                ForallPredItem fora = ((ForallPredItem) actItem);
                String string = fora.getMyIdentifier().getMyString();
                TermNode term = new ASTTermParser().parse(fora.getMyTerm());
                PredicateNode pred = new ASTPredParser().parse(fora.getMyPred());
                return new ForAllSuchThatNode(term, pred, string, textPosition);
            }
            case TERM_FUNCALL -> {
                IdentifierNode identifier = new IdentifierNode(((FunCallItem) actItem).getMyIdentifier().getMyString(), textPosition);
                ArrayList<TermNode> terms = new ArrayList<TermNode>();
                for (int i = 0; i < ((FunCallItem) actItem).getTerms().size(); i++) {
                    terms.add(new ASTTermParser().parse(((FunCallItem) actItem).getTerms().get(i)));
                }
                return new FunctionCallPredNode(identifier, terms, textPosition);
            }
            case PRED_BOOLEAN -> {
                return new BooleanNode(((PredItem) actItem).getMyBoolean(), textPosition);
            }
            default -> throw new ParseTimeException("Unexpected value: " + actItem.getLanguageItemType(), actItem.getTextPosition());
        }

    }


    private static class ShuntingYardBuilder {
        Stack<LanguageItemAbstract> stack;
        ArrayList<LanguageItemAbstract> output;

        public ShuntingYardBuilder() {
            this.stack = new Stack<LanguageItemAbstract>();
            this.output = new ArrayList<LanguageItemAbstract>();
        }

        public void add(LanguageItemAbstract item) {
            switch (item.getLanguageItemType()) {
                case PRED_BINRELSYM, PRED_TERM, PRED_IN, QUANTIFIED_EXISTS, QUANTIFIED_FORALL, PRED_NOT, TERM_FUNCALL,
                        PRED_BOOLEAN, PRED_INDEX -> {
                    output.add(item);
                }
                case PRED_BRACKET -> {
                    stack.push(item);
                }
                case PREDR_BRACKET -> {
                    while (!stack.isEmpty()
                            && !stack.peek().getLanguageItemType().equals(LanguageItemType.PRED_BRACKET)) {
                        output.add(stack.pop());
                    }
                    if (!stack.isEmpty())
                        stack.pop();
                }
                case /*OPERATOR_ADD, OPERATOR_SUBTRACT, OPERATOR_MULTIPLY, OPERATOR_DIVIDE,
                        OPERATOR_POWER, OPERATOR_DIV, OPERATOR_MOD, TERM_DIRECTIONAL_H, TERM_DIRECTIONAL_V,*/
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

        public ArrayList<LanguageItemAbstract> getOutput() throws PositionedException {
            while (!stack.isEmpty()) {
                if (LanguageItemType.TERM_BRACKET.equals(stack.peek().getLanguageItemType())) {
                    throw new ParseTimeException("Shunting Yard Builder Term invalid", stack.peek().getTextPosition());
                }
                output.add(stack.pop());
            }
            return output;
        }

    }


}
