package de.hskempten.tabulang.nodes;

import de.hskempten.tabulang.Interpreter;
import de.hskempten.tabulang.tokenizer.Lexer;
import de.hskempten.tabulang.tokenizer.ParseTimeException;

import java.util.ArrayList;
import java.util.List;

public class Term extends Node {

    List<Node> terms = new ArrayList<Node>();

    public Term(Lexer l) throws ParseTimeException {
        super(l.lookahead());

        while (!l.lookahead().getType().equals(";")){
            switch (l.lookahead().getType()){
                case "variable":
                    terms.add(new Variable(l));
            }
        }
    }

    @Override
    public Number evaluate(Interpreter i) {
        return null;
    }
}
