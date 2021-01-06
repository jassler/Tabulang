package de.hskempten.tabulang.items.types;

import de.hskempten.tabulang.TokenType;
import de.hskempten.tabulang.items.QuotedStringItem;
import de.hskempten.tabulang.tokenizer.Lexer;
import de.hskempten.tabulang.tokenizer.ParseTimeException;
import de.hskempten.tabulang.tokenizer.TextPosition;
import de.hskempten.tabulang.types.LanguageType;

public class QuotedStringType implements LanguageType {

    public static QuotedStringType instance = new QuotedStringType();

    @Override
    public QuotedStringItem parse(Lexer l) throws ParseTimeException {
        //any char except the quote char
        String myString;

        TextPosition startP = l.lookahead().getPosition();
        myString = l.lookahead().getContent();
        l.getNextTokenAndExpect(TokenType.QUOTEDSTRING);

        myString = myString.substring(1, myString.length() - 1);
        QuotedStringItem item = new QuotedStringItem(myString);

        TextPosition endP = l.lookbehind().getPosition();
        item.setTextPosition(new TextPosition(startP, endP));
        return item;
    }
}
