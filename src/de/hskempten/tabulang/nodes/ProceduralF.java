package de.hskempten.tabulang.nodes;

import de.hskempten.tabulang.Interpreter;
import de.hskempten.tabulang.tokenizer.Lexer;
import de.hskempten.tabulang.tokenizer.ParseTimeException;
import de.hskempten.tabulang.tokenizer.Token;

public class ProceduralF extends Node{
    public ProceduralF(Lexer l) throws ParseTimeException {
        super(l.lookahead());
    }

    @Override
    public Number evaluate(Interpreter i) {
        return null;
    }
}
