package de.hskempten.tabulang;

import de.hskempten.tabulang.nodes.Assignment;
import de.hskempten.tabulang.tokenizer.Lexer;
import de.hskempten.tabulang.tokenizer.ParseTimeException;
import de.hskempten.tabulang.tokenizer.Token;
import de.hskempten.tabulang.tokenizer.TokenExpression;

public class Tabulang {

    public static void main(String[] args) throws ParseTimeException {
        Lexer l = new Lexer();
        l.setText("start := 10; a := 4; c := a + 2 * 3 - 20;");

        l.addExpression(new TokenExpression("variable", "[a-zA-Z]\\w*"));
        l.addExpression(new TokenExpression("number", "[1-9][0-9]*"));
        // l.addExpression(new TokenExpression("binNumber", "0b[0-9]+"));
        // 0b14
        l.addExpression(new TokenExpression(";", ";"));
        l.addExpression(new TokenExpression(":=", ":="));
        l.addExpression(new TokenExpression("binaryOperator", "\\+|-|\\*|/"));

        Interpreter i = new Interpreter();

        while(!l.isDone()) {
            // assuming that everything must be an Assignment
            // later on, we can change it to Statement or Function or something
            i.evaluate(new Assignment(l));
        }

        System.out.println(i);
    }

}
