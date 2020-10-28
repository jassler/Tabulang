package de.hskempten.tabulang;

import de.hskempten.tabulang.nodes.Assignment;
import de.hskempten.tabulang.tokenizer.Lexer;
import de.hskempten.tabulang.tokenizer.ParseTimeException;
import de.hskempten.tabulang.tokenizer.Token;
import de.hskempten.tabulang.tokenizer.TokenExpression;

public class Tabulang {

    public static void main(String[] args) throws ParseTimeException {
        Lexer l = new Lexer();

        l.setText("start := 10abcabc;", "main.tabl");
        l.addExpression(new TokenExpression("variable", "[a-zA-Z]\\w*"));
        l.addExpression(new TokenExpression("number", "[1-9][0-9]*"));
        // l.addExpression(new TokenExpression("binNumber", "0b[0-9]+"));
        // 0b14
        l.addExpression(new TokenExpression(";", ";"));
        l.addExpression(new TokenExpression(":=", ":="));


        Assignment a = new Assignment(l);
        System.out.println(a);
    }

}
