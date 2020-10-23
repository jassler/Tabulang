package de.hskempten.tabulang.nodes;

import de.hskempten.tabulang.tokenizer.Lexer;
import de.hskempten.tabulang.tokenizer.ParseTimeException;
import de.hskempten.tabulang.tokenizer.Token;

public class Expression extends Node {

    private Number n;

    public Expression(Lexer l) throws ParseTimeException {
        super(l.lookahead());

        switch(l.lookahead().getType()) {
            case "number":
                this.n = new Number(l);
                break;
            default:
                l.expectedException("number or something else I guess", l.lookahead());
        }
    }

    public Number getValue() {
        return n;
    }

    @Override
    public String toString() {
        return "Expression{" +
                "n=" + n +
                '}';
    }
}
