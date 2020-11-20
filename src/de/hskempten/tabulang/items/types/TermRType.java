package de.hskempten.tabulang.items.types;

import de.hskempten.tabulang.items.*;
import de.hskempten.tabulang.tokenizer.Lexer;
import de.hskempten.tabulang.tokenizer.ParseTimeException;

import java.util.ArrayList;

public class TermRType implements Parser {

    public static TermRType instance = new TermRType();

    @Override
    public TermRItem parse(Lexer l) throws ParseTimeException {
        TermRItem item;

        //myPreds darf nicht null sein; pred+
        ArrayList<PredItem> myPreds;
        TermRItem myTermR;
        TermItem myTerm;
        OperatorItem myOperator;
        MarkStmntItem myMarkStmnt;
        TupelItem myTupel;
        //"filter", "intersect", "unite", "."
        String myString;

        switch (l.lookahead().getType()) {
            case "binaryOperator" -> {
                myOperator = OperatorType.instance.parse(l);
                myTerm = TermType.instance.parse(l);
                myTermR = TermRType.instance.parse(l);
                item = new TermRItem(myTermR, myTerm, myOperator);
            }
            case ";", "comma", "bracket" -> {
                item = new TermRItem();
            }
            case "keyword" -> {

                switch (l.lookahead().getContent()) {
                    case "using", "as", "function", "if" -> {
                        item = new TermRItem();
                    }
                    case "mark" -> {
                        myMarkStmnt = MarkStmntType.instance.parse(l);
                        myTermR = TermRType.instance.parse(l);
                        item = new TermRItem(myTermR, myMarkStmnt);
                    }
                    default -> throw new ParseTimeException(l, "Illegal keyword or not yet implemented: " + l.lookahead().getContent());
                }
            }
            default -> throw new ParseTimeException(l, "Not yet implemented type case in termR: " + l.lookahead().getType());
        }

        //TODO Implement TermRItem cases


        return item;
    }
}
