package de.hskempten.tabulang;

import de.hskempten.tabulang.items.ProgramItem;
import de.hskempten.tabulang.nodes.Assignment;
import de.hskempten.tabulang.parser.TabulangParser;
import de.hskempten.tabulang.tokenizer.*;

import java.math.BigInteger;

public class Tabulang {

    public static void main(String[] args) throws ParseTimeException {
        Lexer l = new Lexer();

        for(var t : TokenType.TOKEN_EXPRESSIONS) {
            l.addExpression(t);
        }

        //l.setText("a := 9; b := 3; result := a + b * 3 - 20;");
        l.setText("function xyz(asdf,jkl){return asdf;}a := 9; b := 3; result := a + b * 3 - 20;");
        Interpreter i = new Interpreter();

        TabulangParser parser = new TabulangParser(l, i);
        parser.parse();
        l.reset();
        ProgramItem prg = parser.parseN();
        System.out.println(prg);
        /*
        while(!l.isDone()) {
            // assuming that everything must be an Assignment
            // later on, we can change it to Statement or Function or something
            var a = new Assignment(l);
            i.evaluate(a);
        }
         */

        System.out.println("Evaluated \"" + l.getText() + "\" and got:\n");
        System.out.println(i);
    }

}
