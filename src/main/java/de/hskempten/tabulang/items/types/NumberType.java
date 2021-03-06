package de.hskempten.tabulang.items.types;

import de.hskempten.tabulang.TokenType;
import de.hskempten.tabulang.items.NumberItem;
import de.hskempten.tabulang.tokenizer.Lexer;
import de.hskempten.tabulang.tokenizer.ParseTimeException;
import de.hskempten.tabulang.tokenizer.TextPosition;

public class NumberType implements Parser {

    public static NumberType instance = new NumberType();

    @Override
    public NumberItem parse(Lexer l) throws ParseTimeException {
        NumberItem item;

        String myNumber;

        TextPosition startP = l.lookahead().getPosition();
        myNumber = l.lookahead().getContent();
        l.getNextTokenAndExpect(TokenType.NUMBER);
        item = new NumberItem(myNumber);

        TextPosition endP = l.lookbehind().getPosition();
        item.setTextPosition(new TextPosition(startP, endP));
        return item;
    }
}
