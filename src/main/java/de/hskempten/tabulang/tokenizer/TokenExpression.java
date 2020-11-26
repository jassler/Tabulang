package de.hskempten.tabulang.tokenizer;

public class TokenExpression {

    private static int counter = 0;

    // The type "E" is forbidden; such a token will never be scanned
    // since it stands for the empty word, which is caught by the parser
    private final String type;
    private final String expression;
    private int number;

    public TokenExpression(String type, String expression) {
        this.type = type;
        this.expression = expression;
        this.number = counter;
        counter++;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int nr) {
        number = nr;
    }

    public String getType() {
        return type;
    }

    public String getExpression() {
        return expression;
    }
}
