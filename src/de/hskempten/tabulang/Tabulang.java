package de.hskempten.tabulang;

import de.hskempten.tabulang.nodes.Assignment;
import de.hskempten.tabulang.tokenizer.*;

import java.math.BigInteger;

public class Tabulang {

    public static void main(String[] args) throws ParseTimeException {
        Lexer l = new Lexer();

        l.addExpression(new TokenExpression("variable", "[a-zA-Z]\\w*"));
        l.addExpression(new TokenExpression("number", "0|[1-9][0-9]*"));
        // l.addExpression(new TokenExpression("binNumber", "0b[0-9]+"));
        // 0b14
        l.addExpression(new TokenExpression(";", ";"));
        l.addExpression(new TokenExpression(":=", ":="));
        l.addExpression(new TokenExpression("binaryOperator", "\\+|-|\\*|/"));


        l.setText("a := 9; b := 3; result := a + b * 3 - 20;");
        Interpreter i = new Interpreter();

        while(!l.isDone()) {
            // assuming that everything must be an Assignment
            // later on, we can change it to Statement or Function or something
            var a = new Assignment(l);
            i.evaluate(a);
        }

        System.out.println("Evaluated \"" + l.getText() + "\" and got:\n");
        System.out.println(i);
    }

}
