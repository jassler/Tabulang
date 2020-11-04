package de.hskempten.tabulang.nodes;

import de.hskempten.tabulang.Interpreter;
import de.hskempten.tabulang.TokenType;
import de.hskempten.tabulang.tokenizer.Lexer;
import de.hskempten.tabulang.tokenizer.ParseTimeException;

public class ReturnStmnt extends Node {

    private Term returnTerm;


    public ReturnStmnt(Lexer l) throws ParseTimeException {
        super(l.lookahead());

        l.getNextTokenAndExpect(TokenType.KEYWORD);
        returnTerm = new Term(l);
        l.getNextTokenAndExpect(TokenType.SEMICOLON);
    }

    public Term getReturnTerm() {
        return returnTerm;
    }

    @Override
    public Number evaluate(Interpreter i) {
        return null;
    }
}
