package de.hskempten.tabulang.items.types;

import de.hskempten.tabulang.TokenType;
import de.hskempten.tabulang.items.NumberItem;
import de.hskempten.tabulang.items.OrdinalItem;
import de.hskempten.tabulang.items.QuotedStringItem;
import de.hskempten.tabulang.items.TupelItem;
import de.hskempten.tabulang.tokenizer.Lexer;
import de.hskempten.tabulang.tokenizer.ParseTimeException;
import de.hskempten.tabulang.tokenizer.TextPosition;

public class OrdinalType implements Parser {

    public static OrdinalType instance = new OrdinalType();

    @Override
    public OrdinalItem parse(Lexer l) throws ParseTimeException {
        OrdinalItem item;

        //"null"
        String myString;
        NumberItem myNumber;
        QuotedStringItem myQuotedString;
        TupelItem myTupel;

        TextPosition startP = l.lookahead().getPosition();
        switch (l.lookahead().getType()) {
            case "number" -> {
                myNumber = NumberType.instance.parse(l);
                item = new OrdinalItem(myNumber);
            }
            case "quotedString" -> {
                myQuotedString = QuotedStringType.instance.parse(l);
                item = new OrdinalItem(myQuotedString);
            }
            case "bracket" -> {
                myTupel = TupelType.instance.parse(l);
                item = new OrdinalItem(myTupel);
            }
            case "keyword" -> {
                if ("null".equals(l.lookahead().getContent())) {
                    item = new OrdinalItem("null");
                    l.getNextTokenAndExpect(TokenType.KEYWORD);
                } else {
                    throw new ParseTimeException(l, "Not yet implemented case in Ordinal: " + l.lookahead().getType());

                }
            }
            default -> throw new ParseTimeException(l, "Not yet implemented case in Ordinal: " + l.lookahead().getType());
        }

        TextPosition endP = l.lookbehind().getPosition();
        item.setTextPosition(new TextPosition(startP, endP));
        return item;
    }
}
