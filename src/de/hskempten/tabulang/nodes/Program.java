package de.hskempten.tabulang.nodes;

import de.hskempten.tabulang.Interpreter;
import de.hskempten.tabulang.tokenizer.Lexer;
import de.hskempten.tabulang.tokenizer.ParseTimeException;
import de.hskempten.tabulang.tokenizer.Token;

import java.util.ArrayList;
import java.util.List;

public class Program extends Node {

    List<Directive> lines = new ArrayList<Directive>();

    public Program(Lexer l) throws ParseTimeException {
        super(l.lookahead());

        while (!l.isDone()) {
            lines.add(new Directive(l));
        }
    }

    @Override
    public Number evaluate(Interpreter i) {
        for (Directive x : lines) {
            x.evaluate(i);
        }
        return null;
    }
}
