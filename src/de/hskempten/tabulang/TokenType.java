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
    public static final TokenExpression COMMA;
    public static final TokenExpression QUOTEDSTRING;

    public static final TokenExpression[] TOKEN_EXPRESSIONS;

    static {
        KEYWORD = new TokenExpression("keyword", "(after)|(and)|(as)|(background)|(before)|(direction)|" +
                "(distinct)|(else)|(exists)|(filter)|(forall)|(font)|(fontStyle)|(for)|(foreground)|(from)|(function)|" +
                "(hiding)|(horizontal)|(horizontalflip)|(if)|(iff)|(impl)|(in)|(intersect)|(mark)|(not)|(null)|(or)|" +
                "(return)|(set)|(size)|(suchthat)|(unite)|(using)|(vertical)|(verticalflip)|(xor)");
        VARIABLE = new TokenExpression("variable", "[a-zA-Z]\\w*");
        NUMBER = new TokenExpression("number", "-?[0-9]+(\\.[0-9]+)?");
        SEMICOLON = new TokenExpression(";", ";");
        ASSIGN = new TokenExpression(":=", ":=");
        BINARY_OPERATOR = new TokenExpression("binaryOperator", "\\+|-|\\*|/");
        BRACKET = new TokenExpression("bracket", "\\{|\\}|\\(|\\)");
        COMMA = new TokenExpression("comma", ",");
        QUOTEDSTRING = new TokenExpression("quotedString", "'[~']'");


        TOKEN_EXPRESSIONS = new TokenExpression[]{
                KEYWORD,
                VARIABLE,
                NUMBER,
                SEMICOLON,
                ASSIGN,
                BINARY_OPERATOR,
                BRACKET,
                COMMA,
                QUOTEDSTRING
        };
    }
}
