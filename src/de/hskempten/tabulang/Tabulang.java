package de.hskempten.tabulang;

import de.hskempten.tabulang.tokenizer.Lexer;
import de.hskempten.tabulang.tokenizer.ParseTimeException;
import de.hskempten.tabulang.tokenizer.Token;
import de.hskempten.tabulang.tokenizer.TokenExpression;

public class Tabulang {

    public static void main(String[] args) throws ParseTimeException {
        Lexer l = new Lexer();
        l.setText("start 123 end");

        l.addExpression(new TokenExpression("StartType", "start"));
        l.addExpression(new TokenExpression("NumberType", "[0-9]+"));
        l.addExpression(new TokenExpression("EndType", "end"));

        while(l.lookahead() != l.EOFToken) {
            Token t = l.getNextToken();
            System.out.println(t.toString());
        }
    }

}
