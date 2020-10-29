package de.hskempten.tabulang.nodes;

import de.hskempten.tabulang.Interpreter;
import de.hskempten.tabulang.tokenizer.Lexer;
import de.hskempten.tabulang.tokenizer.ParseTimeException;
import de.hskempten.tabulang.tokenizer.Token;

public class BinaryExpression extends Node {

    private Node left;
    private Node right;

    private String operator;

    public BinaryExpression(Lexer l, Node left) throws ParseTimeException {
        super(l.lookahead());

        this.left = left;

        Token t = l.getNextTokenAndExpect("binaryOperator");
        this.operator = t.getContent();

        right = new Expression(l, getPrecedence(this.operator));
    }

    @Override
    public Number evaluate(Interpreter i) {

        Number l = left.evaluate(i);
        Number r = right.evaluate(i);
        return switch (operator) {
            case "+" -> l.add(r);
            case "*" -> l.multiply(r);
            case "-" -> l.subtract(r);
            case "/" -> l.divide(r);
            default -> throw new RuntimeException("Unknown binary operator \"" + operator + "\"");
        };
    }

    /**
     * Precedence of binary operators.
     * Starts at 1, higher number means higher precedence.
     *
     * 1: + -
     * 2: * /
     *
     * Missing: &&, ||, <, <=, =>, >
     *
     * @param operator String operator
     * @return Precedence number, lowest starting at 1
     */
    public static int getPrecedence(String operator) {
        return switch (operator) {
            case "+", "-" -> 1;
            case "*", "/" -> 2;
            default -> throw new RuntimeException("Unknown binary operator \"" + operator + "\"");
        };
    }
}
