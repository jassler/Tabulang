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
    public static final TokenExpression BINRELSYM;
    public static final TokenExpression BINBOOL;
    public static final TokenExpression DOT;

    public static final TokenExpression[] TOKEN_EXPRESSIONS;

    static {
        KEYWORD = new TokenExpression("keyword", "(after)|(as)|(average)|(background)|(before)|(count)|" +
                "(direction)|(distinct)|(else)|(exists)|(filter)|(forall)|(font)|(fontStyle)|(for)|(foreground)|(from)|" +
                "(function)|(group)|(hiding)|(horizontal)|(horizontalflip)|(if)|(in)|(intersect)|(mark)|(not)|(null)|" +
                "(return)|(set)|(size)|(suchThat)|(unite)|(using)|(vertical)|(verticalflip)");
        VARIABLE = new TokenExpression("variable", "[a-zA-Z]\\w*");
        NUMBER = new TokenExpression("number", "-?[0-9]+(\\.[0-9]+)?");
        SEMICOLON = new TokenExpression(";", ";");
        ASSIGN = new TokenExpression(":=", ":=");
        BINARY_OPERATOR = new TokenExpression("binaryOperator", "\\+|-|\\*|/|(div)|(mod)|\\^");
        BRACKET = new TokenExpression("bracket", "\\{|\\}|\\(|\\)");
        COMMA = new TokenExpression("comma", ",");
        QUOTEDSTRING = new TokenExpression("quotedString", "'[^']*'");
        BINRELSYM = new TokenExpression("binRelSym", "=|<|>|<=|>=|!=");
        BINBOOL = new TokenExpression("binBool", "(and)|(or)|(xor)|(iff)|(impl)");
        DOT = new TokenExpression(".", "\\.");


        TOKEN_EXPRESSIONS = new TokenExpression[]{
                KEYWORD,
                NUMBER,
                SEMICOLON,
                ASSIGN,
                BINARY_OPERATOR,
                BRACKET,
                COMMA,
                QUOTEDSTRING,
                BINRELSYM,
                BINBOOL,
                DOT,
                VARIABLE
        };
    }
}
