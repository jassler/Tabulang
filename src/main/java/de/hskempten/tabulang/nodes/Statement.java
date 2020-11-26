package de.hskempten.tabulang.nodes;

import de.hskempten.tabulang.Interpreter;
import de.hskempten.tabulang.tokenizer.Lexer;
import de.hskempten.tabulang.tokenizer.ParseTimeException;

public class Statement extends Node {
    Node n;


    public Statement(Lexer l) throws ParseTimeException {
        super(l.lookahead());

        switch (l.lookahead().getType()) {
            case "keyword" -> {
                switch (l.lookahead().getContent()) {
                    case "for" -> this.n = new Loop(l);
                    case "if" -> this.n = new IfStmnt(l);
                }
            }
            case "bracket" -> {
                if ("{".equals(l.lookahead().getContent())) {
                    this.n = new Body(l);
                } else {
                    throw new ParseTimeException("Illegal bracket: " + l.lookahead().getContent());
                }
            }
            default -> this.n = new VarDef(l);
        }
    }

    @Override
    public Number evaluate(Interpreter i) {
        return n.evaluate(i);
    }
}