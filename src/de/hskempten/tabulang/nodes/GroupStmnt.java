package de.hskempten.tabulang.nodes;

import de.hskempten.tabulang.Interpreter;
import de.hskempten.tabulang.tokenizer.Lexer;
import de.hskempten.tabulang.tokenizer.ParseTimeException;

public class GroupStmnt extends Node {
    public GroupStmnt(Lexer l) throws ParseTimeException {
        super(l.lookahead());
    }

    @Override
    public Number evaluate(Interpreter i) {
        return null;
    }
}
