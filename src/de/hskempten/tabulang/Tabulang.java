package de.hskempten.tabulang;

import de.hskempten.tabulang.nodes.Assignment;
import de.hskempten.tabulang.tokenizer.*;

import java.math.BigInteger;

public class Tabulang {

    public static void main(String[] args) throws ParseTimeException {
        Lexer l = new Lexer();

        for(var t : TokenType.TOKEN_EXPRESSIONS) {
            l.addExpression(t);
        }

        l.setText("a := 9; b := 3; result := a + b * 3 - 20;");
        Interpreter i = new Interpreter();

        while(!l.isDone()) {
            // assuming that everything must be an AssignmentItem
            // later on, we can change it to Statement or Function or something
            var a = new Assignment(l);
            i.evaluate(a);
        }

        System.out.println("Evaluated \"" + l.getText() + "\" and got:\n");
        System.out.println(i);
    }

}
