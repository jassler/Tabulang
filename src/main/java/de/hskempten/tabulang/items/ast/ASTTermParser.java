package de.hskempten.tabulang.items.ast;

import de.hskempten.tabulang.items.*;
import de.hskempten.tabulang.items.ast.interfaces.TermAST;
import de.hskempten.tabulang.items.ast.nodes.*;

import java.util.ArrayList;

public class ASTTermParser {

    public static ASTTermParser instance = new ASTTermParser();

    public TermAST parse(TermItem item) throws Exception {
        ArrayList<LanguageItem> items = new ArrayList<LanguageItem>();
        ArrayList<LanguageItemType> types = new ArrayList<LanguageItemType>();

        LanguageItemType termRType = LanguageItemType.NULL;
        TermItem actualTerm = item;
        int sec = 20;

        while (sec > 0 && !termRType.equals(LanguageItemType.TERMR_NULL)) {
            switch (actualTerm.getLanguageItemType()) {
                case TERM_IDENTIFIER -> items.add(actualTerm.getMyIdentifier());
                case TERM_LOOP -> items.add(actualTerm.getMyLoop());
                case TERM_ORDINAL -> items.add(actualTerm.getMyOrdinal());
                case TERM_DIRECTIONAL -> items.add(actualTerm.getMyDirectionalTerm());
                case TERM_AGGREGATION -> items.add(actualTerm.getMyAggregationT());
                case TERM_DISTINCT -> items.add(actualTerm.getMyDistinctT());
                case TERM_FUNCALL -> items.add(actualTerm.getMyFunCall());
                default -> {
                    items.add(null);
                    System.out.println(actualTerm.getLanguageItemType().name());
                }
            }
            termRType = actualTerm.getMyTermR().getLanguageItemType();
            types.add(switch (termRType) {
                case TERMR_OPERATOR -> actualTerm.getMyTermR().getMyOperator().getLanguageItemType();
                default -> termRType;
            });
            sec--;
            try {
                actualTerm = actualTerm.getMyTermR().getMyTerm();
            } catch (Exception e) {
                break;
            }
        }
        ParserTerm pTerm = new ParserTerm(items, types);
        TermAST parsedTerm = termParser(pTerm);


        return parsedTerm;
    }


    private TermAST termParser(ParserTerm pTerm) throws Exception {

        if (pTerm.getTypes().size() == 1) {
            return getLastTermAST(pTerm);
        }

        int divider = getNextDivider(pTerm.getTypes());

        TermAST left;
        if (divider > 0) {
            ArrayList<LanguageItem> leftItems = new ArrayList<LanguageItem>(pTerm.getItems().subList(0, divider+1));
            ArrayList<LanguageItemType> leftTypes = new ArrayList<LanguageItemType>(pTerm.getTypes().subList(0, divider+1));
            leftTypes.set(leftTypes.size()-1,LanguageItemType.TERMR_NULL);
            left = termParser(new ParserTerm(leftItems, leftTypes));
        } else {
            left = getLastTermAST(new ParserTerm(new ArrayList<LanguageItem>(pTerm.getItems().subList(0, 1)), new ArrayList<LanguageItemType>(pTerm.getTypes().subList(0, 1))));
        }

        TermAST right;
        if (divider < pTerm.getItems().size() - 1) {
            ArrayList<LanguageItem> rightItems = new ArrayList<LanguageItem>(pTerm.getItems().subList(divider + 1, pTerm.getItems().size()));
            ArrayList<LanguageItemType> rightTypes = new ArrayList<LanguageItemType>(pTerm.getTypes().subList(divider + 1, pTerm.getTypes().size()));
            right = termParser(new ParserTerm(rightItems, rightTypes));
        } else {
            right = getLastTermAST(new ParserTerm(new ArrayList<LanguageItem>(pTerm.getItems().subList(pTerm.getItems().size() - 1, pTerm.getItems().size())), new ArrayList<LanguageItemType>(pTerm.getTypes().subList(pTerm.getItems().size() - 1, pTerm.getItems().size()))));
        }

        LanguageItemType pType = pTerm.getTypes().get(divider);
        return switch (pType) {
            //case FUNCALL -> new FunCallNode(((FunCallItem) pTerm.getItems().get(divider)).getMyIdentifier(), ((FunCallItem) pTerm.getItems().get(divider)).getMyTupel());

            case OPERATOR_ADD -> new AddAST(left, right);
            case OPERATOR_SUBTRACT -> new SubtractAST(left, right);
            case OPERATOR_MULTIPLY -> new MultiplyAST(left, right);
            case OPERATOR_DIVIDE -> new DivideAST(left, right);
            case OPERATOR_DIV -> new DivAST(left, right);
            case OPERATOR_MOD -> new ModAST(left, right);
            case OPERATOR_POWER -> new PowerAST(left, right);
            default -> throw new IllegalStateException("Unexpected value: " + pType);
        };
    }

    private TermAST getLastTermAST(ParserTerm pTerm) {
        LanguageItem item = pTerm.getItems().get(0);
        LanguageItemType type = pTerm.getTypes().get(0);
        return switch (item.getLanguageItemType()) {
            case TERM_FUNCALL -> null; //new FunCallNode(((FunCallItem) item).getMyIdentifier(), ((FunCallItem) item).getMyTupel());
            case TERM_FUNDEF -> null;
            case TERM_IDENTIFIER -> new IdentifierAST(((IdentifierItem) item).getMyString());

            // new OrdinalNode((OrdinalItem) item);
            case ORDINAL_NUMBER -> new NumberAST((((OrdinalItem) item).getMyNumber()));

            default -> throw new IllegalStateException("Unexpected value: " + type);
        };

    }

    private int getNextDivider(ArrayList<LanguageItemType> types) throws Exception {
        int lowestPrecedence = 100;
        int lowestPosition = 0;
        for (int i = types.size() - 1; i >= 0; i--) {
            if (LanguageItemType.getPrecedence(types.get(i)) < lowestPrecedence) {
                lowestPrecedence = LanguageItemType.getPrecedence(types.get(i));
                if (lowestPrecedence >= 0) {
                    lowestPosition = i;
                } else {
                    throw new Exception("Illegal Type in ASTTermParser " + types.get(i));
                }
            }else if(LanguageItemType.OPERATOR_POWER.equals(types.get(i)) && lowestPrecedence == LanguageItemType.getPrecedence(types.get(i))){
                lowestPrecedence = LanguageItemType.getPrecedence(types.get(i));
                lowestPosition = i;
            }
        }
        return lowestPosition;

    }


    private class ParserTerm {
        ArrayList<LanguageItem> items;
        ArrayList<LanguageItemType> types;

        public ParserTerm(ArrayList<LanguageItem> items, ArrayList<LanguageItemType> types) {
            this.items = items;
            this.types = types;
        }

        public ArrayList<LanguageItem> getItems() {
            return items;
        }

        public void setItems(ArrayList<LanguageItem> items) {
            this.items = items;
        }

        public ArrayList<LanguageItemType> getTypes() {
            return types;
        }

        public void setTypes(ArrayList<LanguageItemType> types) {
            this.types = types;
        }
    }


}
