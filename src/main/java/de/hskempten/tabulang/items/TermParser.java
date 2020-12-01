package de.hskempten.tabulang.items;

import java.util.ArrayList;

public class TermParser {
    public static TermParser instance = new TermParser();

    public AnyTermAST parseTerm(TermItem term) {
        ArrayList<LanguageItem> items = new ArrayList<LanguageItem>();
        ArrayList<TermTypeAST> types = new ArrayList<TermTypeAST>();

        TermTypeAST termRType = TermTypeAST.TERM;
        TermItem actualTerm = term;
        int sec = 20;

        while (sec > 0 && !termRType.equals(TermTypeAST.NULL)) {
            switch (actualTerm.getMyTermTypeAST()) {
                case IDENTIFIER -> items.add(actualTerm.getMyIdentifier());
                case LOOP -> items.add(actualTerm.getMyLoop());
                case ORDINAL -> items.add(actualTerm.getMyOrdinal());
                case DIRECTIONAL -> items.add(actualTerm.getMyDirectionalTerm());
                case AGGREGATION -> items.add(actualTerm.getMyAggregationT());
                case DISTINCT -> items.add(actualTerm.getMyDistinctT());
                case FUNCALL -> items.add(actualTerm.getMyFunCall());
                default -> items.add(null);
            }
            termRType = actualTerm.getMyTermR().getMyTermTypeAST();
            types.add(termRType);
            sec--;
            try {
                actualTerm = actualTerm.getMyTermR().getMyTerm();
            } catch (Exception e) {
                break;
            }

        }

        return null;
    }
}
