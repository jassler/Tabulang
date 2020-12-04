package de.hskempten.tabulang.items.ast;

import de.hskempten.tabulang.items.*;
import de.hskempten.tabulang.items.ast.interfaces.TermAST;
import de.hskempten.tabulang.items.ast.nodes.*;

import java.util.ArrayList;

public class ASTTermParser {

    public static ASTTermParser instance = new ASTTermParser();
    ParserTerm pTermO = new ParserTerm();

    public TermAST parse(TermItem item) throws Exception {

        LanguageItemType termRType = LanguageItemType.NULL;
        TermItem actualTerm = item;
        int sec = 20;

        while (sec > 0 && !termRType.equals(LanguageItemType.TERMR_NULL)) {
            switch (actualTerm.getLanguageItemType()) {
                case TERM_IDENTIFIER -> pTermO.addItem(actualTerm.getMyIdentifier());
                case TERM_LOOP -> pTermO.addItem(actualTerm.getMyLoop());
                case TERM_ORDINAL -> pTermO.addItem(actualTerm.getMyOrdinal());
                case TERM_DIRECTIONAL -> pTermO.addItem(actualTerm.getMyDirectionalTerm());
                case TERM_AGGREGATION -> pTermO.addItem(actualTerm.getMyAggregationT());
                case TERM_DISTINCT -> pTermO.addItem(actualTerm.getMyDistinctT());
                case TERM_FUNCALL -> pTermO.addItem(actualTerm.getMyFunCall());
                case TERM_BRACKET -> {
                    parseBracket(actualTerm.getMyTerm());
                    pTermO.addItem(actualTerm.getMyTermR());
                }
                default -> {
                    System.out.println(actualTerm.getLanguageItemType().name());
                    throw new Exception("Not implemented Type in ASTTermParser: " + actualTerm.getLanguageItemType());
                }
            }

            termRType = actualTerm.getMyTermR().getLanguageItemType();

            pTermO.addType(switch (termRType) {
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
        TermAST parsedTerm = termParser(pTermO);


        return parsedTerm;
    }

    private void parseBracket(TermItem actTerm) throws Exception {

        pTermO.addItem(actTerm);
        pTermO.addType(LanguageItemType.TERM_BRACKET);

        int secI = 20;

        TermItem myActualTerm = actTerm;
        LanguageItemType termRTypeI = myActualTerm.getMyTermR().getLanguageItemType();
        while (secI > 0 && !termRTypeI.equals(LanguageItemType.TERMR_BRACKET)) {

            switch (myActualTerm.getLanguageItemType()) {
                case TERM_IDENTIFIER -> pTermO.addItem(myActualTerm.getMyIdentifier());
                case TERM_LOOP -> pTermO.addItem(myActualTerm.getMyLoop());
                case TERM_ORDINAL -> pTermO.addItem(myActualTerm.getMyOrdinal());
                case TERM_DIRECTIONAL -> pTermO.addItem(myActualTerm.getMyDirectionalTerm());
                case TERM_AGGREGATION -> pTermO.addItem(myActualTerm.getMyAggregationT());
                case TERM_DISTINCT -> pTermO.addItem(myActualTerm.getMyDistinctT());
                case TERM_FUNCALL -> pTermO.addItem(myActualTerm.getMyFunCall());
                case TERM_BRACKET -> {
                    parseBracket(myActualTerm.getMyTerm());
                    pTermO.addItem(myActualTerm.getMyTermR());
                }
                default -> {
                    System.out.println(myActualTerm.getLanguageItemType().name());
                    throw new Exception("Not implemented Type in ASTTermParser: " + myActualTerm.getLanguageItemType());
                }
            }
            if (LanguageItemType.TERM_BRACKET.equals(myActualTerm.getLanguageItemType())) {
                termRTypeI = myActualTerm.getLanguageItemType();
            } else {
                termRTypeI = myActualTerm.getMyTermR().getLanguageItemType();
            }
            pTermO.addType(switch (termRTypeI) {
                case TERMR_OPERATOR -> myActualTerm.getMyTermR().getMyOperator().getLanguageItemType();
                default -> termRTypeI;
            });

            try {
                if (LanguageItemType.TERM_BRACKET.equals(termRTypeI)) {
                    parseBracket(myActualTerm);
                }
                TermItem oldMyActualTerm = myActualTerm;
                myActualTerm = myActualTerm.getMyTermR().getMyTerm();

            } catch (Exception e) {
                break;
            }


            secI--;
        }

    }


    private TermAST termParser(ParserTerm pTerm) throws Exception {

        if (pTerm.getTypes().size() == 1) {
            pTerm.getTypes().set(0, LanguageItemType.TERMR_NULL);
            return getLastTermAST(pTerm);
        }

        int divider = getNextDivider(pTerm.getTypes());

        if (divider == pTerm.getTypes().size() - 1) {
            pTerm.getItems().remove(divider);
            pTerm.getTypes().remove(divider - 1);
            pTerm.getItems().remove(0);
            pTerm.getTypes().remove(0);
            divider = getNextDivider(pTerm.getTypes());
        }

        TermAST left;
        if (divider > 0) {
            ArrayList<LanguageItem> leftItems = new ArrayList<LanguageItem>(pTerm.getItems().subList(0, divider + 1));
            ArrayList<LanguageItemType> leftTypes = new ArrayList<LanguageItemType>(pTerm.getTypes().subList(0, divider + 1));
            leftTypes.set(leftTypes.size() - 1, LanguageItemType.TERMR_NULL);
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
            case STATEMENT_IDENTIFIER -> new IdentifierAST(((IdentifierItem) item).getMyString());

            // new OrdinalNode((OrdinalItem) item);
            case ORDINAL_NUMBER -> new NumberAST((((OrdinalItem) item).getMyNumber()));

            default -> throw new IllegalStateException("Unexpected value: " + item.getLanguageItemType());
        };

    }

    private int getNextDivider(ArrayList<LanguageItemType> types) throws Exception {
        int lowestPrecedence = 100;
        int lowestPosition = -99;
        int inBracket = 0;
        for (int i = types.size() - 1; i >= 0; i--) {
            if (types.get(i).equals(LanguageItemType.TERMR_BRACKET)) inBracket++;
            if (inBracket == 0) {
                if (LanguageItemType.getPrecedence(types.get(i)) < lowestPrecedence) {
                    lowestPrecedence = LanguageItemType.getPrecedence(types.get(i));
                    if (lowestPrecedence >= 0) {
                        lowestPosition = i;
                    } else {
                        throw new Exception("Illegal Type in ASTTermParser " + types.get(i));
                    }
                } else if (LanguageItemType.OPERATOR_POWER.equals(types.get(i)) && lowestPrecedence == LanguageItemType.getPrecedence(types.get(i))) {
                    lowestPrecedence = LanguageItemType.getPrecedence(types.get(i));
                    lowestPosition = i;
                }
            }
            if (types.get(i).equals(LanguageItemType.TERM_BRACKET)) inBracket--;
        }
        return lowestPosition;

    }


    private class ParserTerm {
        ArrayList<LanguageItem> items;
        ArrayList<LanguageItemType> types;

        public ParserTerm() {
            this.items = new ArrayList<LanguageItem>();
            this.types = new ArrayList<LanguageItemType>();
        }

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

        public void addItem(LanguageItem item) {
            this.items.add(item);
        }

        public ArrayList<LanguageItemType> getTypes() {
            return types;
        }

        public void setTypes(ArrayList<LanguageItemType> types) {
            this.types = types;
        }

        public void addType(LanguageItemType type) {
            this.types.add(type);
        }
    }


}
