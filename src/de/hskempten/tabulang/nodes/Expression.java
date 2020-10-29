package de.hskempten.tabulang.nodes;

import de.hskempten.tabulang.Interpreter;
import de.hskempten.tabulang.tokenizer.Lexer;
import de.hskempten.tabulang.tokenizer.ParseTimeException;

public class Expression extends Node {

    private Node e;

    public Expression(Lexer l) throws ParseTimeException {
        this(l, 0);
    }

    public Expression(Lexer l, int precedence) throws ParseTimeException {
        super(l.lookahead());

        switch (l.lookahead().getType()) {
            case "number" -> this.e = new Number(l);
            case "variable" -> this.e = new Variable(l);
            default -> l.expectedException("number or variable", l.lookahead());
        }

        if("binaryOperator".equals(l.lookahead().getType())) {

            // if precedence == 0, we're at the top of the "calculation tree"
            // from there we eat our way through the binary operators
            if(precedence == 0) {
                while("binaryOperator".equals(l.lookahead().getType())) {
                    this.e = new BinaryExpression(l, this.e);
                }
            } else if(BinaryExpression.getPrecedence(l.lookahead().getContent()) > precedence) {
                this.e = new BinaryExpression(l, this.e);
            }

        }
    }

    public Number evaluate(Interpreter i) {
        return e.evaluate(i);
    }

    @Override
    public String toString() {
        return "Expression{" +
                "e=" + e +
                '}';
    }
}
