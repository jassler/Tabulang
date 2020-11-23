package de.hskempten.tests;

import de.hskempten.tabulang.Interpreter;
import de.hskempten.tabulang.TokenType;
import de.hskempten.tabulang.items.*;
import de.hskempten.tabulang.parser.TabulangParser;
import de.hskempten.tabulang.tokenizer.Lexer;
import de.hskempten.tabulang.tokenizer.ParseTimeException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LexerTest {
    private Lexer l;
    private TabulangParser parser;

    @BeforeEach
    void setUp() {
        l = new Lexer();

        for (var t : TokenType.TOKEN_EXPRESSIONS) {
            l.addExpression(t);
        }
        l.addOneLineCommentMarker("//");


        Interpreter i = new Interpreter();

        parser = new TabulangParser(l, i);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void keywordLexer() throws ParseTimeException {
        //TODO why doesn't lexer recognize the commented keywords as keywords?
        l.setText("after as average background before count direction distinct else exists filter forall font " +
                //"fontStyle " +
                "for " +
                //"foreground " +
                "from function group hiding horizontal " +
                //"horizontalflip " +
                "if in " +
                //"intersect " +
                "mark " +
                "not null return set size suchthat unite using vertical " +
                //"verticalflip" +
                "");
        l.lookahead();
        while (!l.isDone()){
            System.out.println("Type: "+ l.lookahead().getType() + " Content: "+ l.lookahead().getContent());
            assertEquals("keyword",l.lookahead().getType());
            l.getNextToken();
        }

    }

}
