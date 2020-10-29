package de.hskempten.tabulang.nodes;

import de.hskempten.tabulang.Interpreter;
import de.hskempten.tabulang.tokenizer.Lexer;
import de.hskempten.tabulang.tokenizer.ParseTimeException;
import de.hskempten.tabulang.tokenizer.Token;

public class Variable extends Node {

    private String literal;

    public Variable(Lexer l) throws ParseTimeException {
        super(l.lookahead());

        Token t = l.getNextTokenAndExpect("variable");
        this.literal = t.getContent();
    }

    @Override
    public Number evaluate(Interpreter i) {
        return i.getValue(literal);
    }
}
