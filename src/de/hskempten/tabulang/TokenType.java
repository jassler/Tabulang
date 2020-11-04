package de.hskempten.tabulang;

import de.hskempten.tabulang.tokenizer.TokenExpression;

public class TokenType {

    public static final TokenExpression VARIABLE;
    public static final TokenExpression NUMBER;
    public static final TokenExpression SEMICOLON;
    public static final TokenExpression ASSIGN;
    public static final TokenExpression BINARY_OPERATOR;
    public static final TokenExpression KEYWORD;
    public static final TokenExpression BRACKET;

    public static final TokenExpression[] TOKEN_EXPRESSIONS;

    static {
        KEYWORD = new TokenExpression("keyword", "(function)|(for)|(in)|(if)");
        VARIABLE = new TokenExpression("variable", "[a-zA-Z]\\w*");
        NUMBER = new TokenExpression("number", "-?[0-9]+(\\.[0-9]+)?");
        SEMICOLON = new TokenExpression(";", ";");
        ASSIGN = new TokenExpression(":=", ":=");
        BINARY_OPERATOR = new TokenExpression("binaryOperator", "\\+|-|\\*|/");
        BRACKET = new TokenExpression("bracket", "\\{|\\}|\\(|\\)");


        TOKEN_EXPRESSIONS = new TokenExpression[]{
                KEYWORD,
                VARIABLE,
                NUMBER,
                SEMICOLON,
                ASSIGN,
                BINARY_OPERATOR,
                BRACKET
        };
    }
}
