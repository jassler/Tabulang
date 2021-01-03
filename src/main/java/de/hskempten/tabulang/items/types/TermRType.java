package de.hskempten.tabulang.items.types;

import de.hskempten.tabulang.TokenType;
import de.hskempten.tabulang.items.*;
import de.hskempten.tabulang.tokenizer.Lexer;
import de.hskempten.tabulang.tokenizer.ParseTimeException;

public class TermRType implements Parser {

    public static TermRType instance = new TermRType();

    @Override
    public TermRItem parse(Lexer l) throws ParseTimeException {
        TermRItem item;

        //myPreds darf nicht null sein; pred+
        PredItem myPred;
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
            case "keyword" -> {

                switch (l.lookahead().getContent()) {
                    case "mark" -> {
                        myMarkStmnt = MarkStmntType.instance.parse(l);
                        myTermR = TermRType.instance.parse(l);
                        item = new TermRItem(myTermR, myMarkStmnt);
                    }
                    case "filter" -> {
                        myString = l.lookahead().getContent();
                        l.getNextTokenAndExpect(TokenType.KEYWORD);
                        //TODO there should be only one pred in Syntax
                        myPred = PredType.instance.parse(l);
                        myTermR = TermRType.instance.parse(l);
                        item = new TermRItem(myPred, myTermR, myString);
                    }
                    case "intersect", "unite" -> {
                        myString = l.lookahead().getContent();
                        l.getNextTokenAndExpect(TokenType.KEYWORD);
                        myTerm = TermType.instance.parse(l);
                        myTermR = TermRType.instance.parse(l);
                        item = new TermRItem(myTermR, myTerm, myString);
                    }
                    default -> {
                        item = new TermRItem();
                    }
                }
            }
            case "." -> {
                l.getNextTokenAndExpect(TokenType.DOT);
                myTerm = TermType.instance.parse(l);
                myTermR = TermRType.instance.parse(l);
                item = new TermRItem(myTermR, myTerm);
            }
            case "bracket" -> {
                if ("(".equals(l.lookahead().getContent())) {
                    // TODO case for funCall
                    myTupel = TupelType.instance.parse(l);
                    myTermR = TermRType.instance.parse(l);
                    item = new TermRItem(myTermR, myTupel);
                } else {
                    item = new TermRItem();
                    if (")".equals(l.lookahead().getContent())) {
                        item.setLanguageItemType(LanguageItemType.TERMR_BRACKET);
                    }
                }
            }
            default -> {
                item = new TermRItem();
            }
        }

        return item;
    }
}
