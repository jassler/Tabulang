package de.hskempten.tabulang.nodes;

import de.hskempten.tabulang.Interpreter;
import de.hskempten.tabulang.TokenType;
import de.hskempten.tabulang.tokenizer.Lexer;
import de.hskempten.tabulang.tokenizer.ParseTimeException;
import de.hskempten.tabulang.tokenizer.Token;

import java.math.BigInteger;

public class Number extends Node {

    private BigInteger value;

    public Number(Lexer lexer) throws ParseTimeException {
        super(lexer.lookahead());

        Token t = lexer.getNextTokenAndExpect(TokenType.NUMBER);
        value = new BigInteger(t.getContent());
    }

    public Number(BigInteger value, Token t) {
        super(t);

        this.value = value;
    }

    public BigInteger getValue() {
        return value;
    }

    public Number add(Number other) {
        return new Number(value.add(other.value), getToken());
    }

    public Number subtract(Number other) {
        return new Number(value.subtract(other.value), getToken());
    }

    public Number multiply(Number other) {
        return new Number(value.multiply(other.value), getToken());
    }

    public Number divide(Number other) {
        return new Number(value.divide(other.value), getToken());
    }

    public Number evaluate(Interpreter i) {
        return this;
    }

    @Override
    public String toString() {
        return "NumberAST{" +
                "value=" + value +
                '}';
    }
}
