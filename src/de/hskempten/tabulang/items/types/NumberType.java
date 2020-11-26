package de.hskempten.tabulang.items.types;

import de.hskempten.tabulang.TokenType;
import de.hskempten.tabulang.items.NumberItem;
import de.hskempten.tabulang.tokenizer.Lexer;
import de.hskempten.tabulang.tokenizer.ParseTimeException;

import java.math.BigInteger;

public class NumberType implements Parser {

    public static NumberType instance = new NumberType();

    @Override
    public NumberItem parse(Lexer l) throws ParseTimeException {
        NumberItem item;

        BigInteger myNumber;

        myNumber = new BigInteger(l.lookahead().getContent());
        l.getNextTokenAndExpect(TokenType.NUMBER);
        item = new NumberItem(myNumber);

        return item;
    }
}
