package de.hskempten.tabulang;

import de.hskempten.tabulang.tokenizer.TokenExpression;

public class TokenType {

    public static final TokenExpression VARIABLE;
    public static final TokenExpression NUMBER;
    public static final TokenExpression SEMICOLON;
    public static final TokenExpression ASSIGN;
    public static final TokenExpression BINARY_OPERATOR;

    public static final TokenExpression[] TOKEN_EXPRESSIONS;

    static {
        VARIABLE = new TokenExpression("variable", "[a-zA-Z]\\w*");
        NUMBER = new TokenExpression("number", "0|[1-9][0-9]*");
        SEMICOLON = new TokenExpression(";", ";");
        ASSIGN = new TokenExpression(":=", ":=");
        BINARY_OPERATOR = new TokenExpression("binaryOperator", "\\+|-|\\*|/");

        TOKEN_EXPRESSIONS = new TokenExpression[]{
                VARIABLE,
                NUMBER,
                SEMICOLON,
                ASSIGN,
                BINARY_OPERATOR
        };
    }
}
