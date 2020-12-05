package de.hskempten.tabulang.items.ast;

import de.hskempten.tabulang.items.*;
import de.hskempten.tabulang.items.ast.interfaces.TermAST;
import de.hskempten.tabulang.items.ast.nodes.*;

import java.util.ArrayList;
import java.util.Stack;

public class ASTTermParser {

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
                        case ORDINAL_NULL -> syBuilder.add(((TermItem) actTerm).getMyOrdinal());
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
                case TERM_DIRECTIONAL -> {
                    syBuilder.add(((TermItem) actTerm).getMyDirectionalTerm());
                }
                default -> {
                    throw new Exception("ASTTermParser case not yet implemented: " + actTerm.getLanguageItemType());
                }
            }
            actTerm = switch (actTerm.getLanguageItemType()) {
                case TERM_IDENTIFIER, TERM_ORDINAL, TERM_DIRECTIONAL -> actTerm.getMyTermR();
                case TERMR_OPERATOR -> ((TermRItem) actTerm).getMyTerm();
                case TERM_BRACKET -> actTerm.getMyTermR();
                default -> {
                    throw new IllegalStateException("Unexpected value: " + actTerm.getLanguageItemType());
                }
            };
            i--;
        }
    }

    private TermAST termParser(ArrayList<LanguageItem> items) throws Exception {


        LanguageItem actItem = items.get(items.size() - 1);
        items.remove(items.size() - 1);

        switch (actItem.getLanguageItemType()) {
            case OPERATOR_ADD -> {
                TermAST right = termParser(items);
                TermAST left = termParser(items);
                return new AddAST(left, right);
            }
            case OPERATOR_SUBTRACT -> {
                TermAST right = termParser(items);
                TermAST left = termParser(items);
                return new SubtractAST(left, right);
            }
            case OPERATOR_MULTIPLY -> {
                TermAST right = termParser(items);
                TermAST left = termParser(items);
                return new MultiplyAST(left, right);
            }
            case OPERATOR_MOD -> {
                TermAST right = termParser(items);
                TermAST left = termParser(items);
                return new ModAST(left, right);
            }
            case OPERATOR_DIV -> {
                TermAST right = termParser(items);
                TermAST left = termParser(items);
                return new DivAST(left, right);
            }
            case OPERATOR_DIVIDE -> {
                TermAST right = termParser(items);
                TermAST left = termParser(items);
                return new DivideAST(left, right);
            }
            case OPERATOR_POWER -> {
                TermAST right = termParser(items);
                TermAST left = termParser(items);
                return new PowerAST(left, right);
            }
            case ORDINAL_NUMBER -> {
                NumberAST item = new NumberAST((NumberItem) actItem);

                return item;
            }
            case STATEMENT_IDENTIFIER -> {
                IdentifierAST item = new IdentifierAST(((IdentifierItem) actItem).getMyString());
                return item;
            }
            case TUPEL_EMPTY -> {
                TupelAST item = new TupelAST();
                return item;
            }
            case TUPEL_ONE -> {
                TupelItem tupel = (TupelItem) actItem;
                TermAST term = new ASTTermParser().parse(tupel.getMyTerm());
                TupelAST item = new TupelAST(term);
                return item;
            }
            case TUPEL_MULTI -> {
                TupelItem tupel = (TupelItem) actItem;
                ArrayList<TermAST> terms = new ArrayList<TermAST>();
                terms.add(new ASTTermParser().parse(tupel.getMyTerm()));
                for (int i = 0; i < tupel.getMyTerms().size(); i++) {
                    terms.add(new ASTTermParser().parse(tupel.getMyTerms().get(i)));
                }
                TupelAST item = new TupelAST(terms);
                return item;
            }
            case ORDINAL_QUOTEDSTRING -> {
                String string = ((QuotedStringItem) actItem).getMyString();
                QuotedStringAST item = new QuotedStringAST(string);
                return item;
            }
            case ORDINAL_NULL -> {
                NullAST item = new NullAST();
                return item;
            }
            case TERM_DIRECTIONAL_H, TERM_DIRECTIONAL_V -> {
                DirectionalAST item;
                TermAST term = new ASTTermParser().parse(((DirectionalTermItem)actItem).getMyTerm());
                switch(actItem.getLanguageItemType()){
                    case TERM_DIRECTIONAL_H->  item = new DirectionalAST(DirectionalAST.Dir.HORIZONTAL, term);
                    case TERM_DIRECTIONAL_V->  item = new DirectionalAST(DirectionalAST.Dir.VERTICAL, term);
                    default -> throw new IllegalStateException("Unexpected value: " + actItem.getLanguageItemType());
                }
                return item;
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
                case STATEMENT_IDENTIFIER, ORDINAL_NUMBER, TUPEL_EMPTY, TUPEL_ONE, TUPEL_MULTI, ORDINAL_QUOTEDSTRING, ORDINAL_NULL -> {
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
                        OPERATOR_POWER, OPERATOR_DIV, OPERATOR_MOD, TERM_DIRECTIONAL_H, TERM_DIRECTIONAL_V -> {
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
