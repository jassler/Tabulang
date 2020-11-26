package de.hskempten.tabulang.nodes;

import de.hskempten.tabulang.Interpreter;
import de.hskempten.tabulang.TokenType;
import de.hskempten.tabulang.tokenizer.Lexer;
import de.hskempten.tabulang.tokenizer.ParseTimeException;

public class SetStmnt extends Node {
    private Term setTerm;

    public SetStmnt(Lexer l) throws ParseTimeException {
        super(l.lookahead());

        l.getNextTokenAndExpect(TokenType.KEYWORD);
        setTerm = new Term(l);
        l.getNextTokenAndExpect(TokenType.SEMICOLON);
    }

    @Override
    public Number evaluate(Interpreter i) {
        return null;
    }
}
