package de.hskempten.tabulang.nodes;

import de.hskempten.tabulang.Interpreter;
import de.hskempten.tabulang.tokenizer.Lexer;
import de.hskempten.tabulang.tokenizer.ParseTimeException;

public class VarDef extends Node {
    Node n;
    public VarDef(Lexer l) throws ParseTimeException {
        super(l.lookahead());

        switch (l.lookahead().getType()) {
            case "keyword" -> {
                if ("function".equals(l.lookahead().getContent())) {
                    this.n = new ProceduralF(l);
                } else {
                    throw new ParseTimeException("Illegal keyword: " + l.lookahead().getContent());
                }
            }
            case "variable" -> this.n = new Assignment(l);
            default -> throw new ParseTimeException("Illegal type: " + l.lookahead().getContent());
        }
    }

    @Override
    public Number evaluate(Interpreter i) {
        return n.evaluate(i);
    }
}
