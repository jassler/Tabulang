package de.hskempten.tabulang.nodes;

import de.hskempten.tabulang.tokenizer.Lexer;
import de.hskempten.tabulang.tokenizer.ParseTimeException;
import de.hskempten.tabulang.tokenizer.Token;

import java.math.BigInteger;

public class Number extends Node {

    private BigInteger value;

    public Number(Lexer lexer) throws ParseTimeException {
        super(lexer.lookahead());

        Token t = lexer.getNextTokenAndExpect("number");
        value = new BigInteger(t.getContent());
    }

    public BigInteger getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Number{" +
                "value=" + value +
                '}';
    }
}
