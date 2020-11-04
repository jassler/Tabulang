package de.hskempten.tabulang.nodes;

import de.hskempten.tabulang.Interpreter;
import de.hskempten.tabulang.tokenizer.Lexer;
import de.hskempten.tabulang.tokenizer.ParseTimeException;

import java.util.ArrayList;
import java.util.List;

public class Program extends Node {

    List<AnyStatement> lines = new ArrayList<AnyStatement>();

    public Program(Lexer l) throws ParseTimeException {
        super(l.lookahead());

        while (!l.isDone()) {
            lines.add(new AnyStatement(l));
        }
    }

    @Override
    public Number evaluate(Interpreter i) {
        for (AnyStatement x : lines) {
            x.evaluate(i);
        }
        return null;
    }
}
