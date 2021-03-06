package de.hskempten.tabulang.items.types;

import de.hskempten.tabulang.TokenType;
import de.hskempten.tabulang.items.IntervallItem;
import de.hskempten.tabulang.items.TermItem;
import de.hskempten.tabulang.items.TupelItem;
import de.hskempten.tabulang.tokenizer.*;

import java.util.ArrayList;

public class TupelType implements Parser {

    public static TupelType instance = new TupelType();

    @Override
    public TupelItem parse(Lexer l) throws ParseTimeException {
        TupelItem item;

        TermItem myTerm = null;
        ArrayList<TermItem> myTerms = new ArrayList<>();
        IntervallItem myIntervall = null;

        TextPosition startP = l.lookahead().getPosition();
        Token bracket = l.getNextTokenAndExpect(TokenType.BRACKET);
        if (!bracket.getContent().equals("["))
            throw new ParseTimeException(l, "Illegal bracket: Expected '[' but got " + l.lookahead().getContent());
        TextPosition startInterval = l.lookahead().getPosition();

        TokenExpression kindOfTupel = null;
        while (!l.lookahead().getContent().equals("]")) {
            if (myTerm == null) {
                myTerm = TermType.instance.parse(l);
            }
            Token bracketOrComma = l.lookahead();
            switch (bracketOrComma.getType()) {
                case "bracket":
                    if (!bracketOrComma.getContent().equals("]"))
                        throw new ParseTimeException("Illegal bracket: Expected ']' but got " + l.lookahead().getContent());
                    break;
                case "comma":
                    if (TokenType.INTERVAL.equals(kindOfTupel)) {
                        throw new ParseTimeException("Comma not allowed in Interval " + l.lookahead().getContent());
                    }
                    if (kindOfTupel == null) kindOfTupel = TokenType.COMMA;

                    l.getNextTokenAndExpect(TokenType.COMMA);
                    myTerms.add(TermType.instance.parse(l));
                    break;
                case "interval":
                    if (TokenType.COMMA.equals(kindOfTupel)) {
                        throw new ParseTimeException("Interval not allowed in Tupel with multiple Terms " + l.lookahead().getContent());
                    }
                    if (kindOfTupel == null) kindOfTupel = TokenType.INTERVAL;

                    l.getNextTokenAndExpect(TokenType.INTERVAL);
                    TermItem secondIntervalTerm = TermType.instance.parse(l);
                    myIntervall = new IntervallItem(myTerm, secondIntervalTerm);
                    TextPosition endInterval = l.lookbehind().getPosition();
                    myIntervall.setTextPosition(new TextPosition(startInterval, endInterval));
                    break;
                default:
                    throw new ParseTimeException("Illegal Token: " + l.lookahead().getContent());
            }
        }


        if (!l.lookahead().getContent().equals("]"))
            throw new ParseTimeException(l, "Illegal bracket: Expected ']' but got " + l.lookahead().getContent());
        l.getNextTokenAndExpect(TokenType.BRACKET);

        if (myIntervall != null) {
            item = new TupelItem(myIntervall);
        } else if (myTerm == null) {
            item = new TupelItem();
        } else if (myTerms.isEmpty()) {
            item = new TupelItem(myTerm);
        } else {
            item = new TupelItem(myTerm, myTerms);
        }

        TextPosition endP = l.lookbehind().getPosition();
        item.setTextPosition(new TextPosition(startP, endP));
        return item;

    }
}
