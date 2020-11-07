package de.hskempten.tabulang.items.types;

import de.hskempten.tabulang.items.NumberItem;
import de.hskempten.tabulang.items.OrdinalItem;
import de.hskempten.tabulang.items.QuotedStringItem;
import de.hskempten.tabulang.items.TupelItem;
import de.hskempten.tabulang.tokenizer.Lexer;
import de.hskempten.tabulang.tokenizer.ParseTimeException;

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


        switch (l.lookahead().getType()) {
            case "number" -> {
                myNumber = NumberType.instance.parse(l);
                item = new OrdinalItem(myNumber);
            }
            default -> throw new ParseTimeException("Not yet implemented case in Ordinal: " + l.lookahead().getType());
        }


        return item;
    }
}
