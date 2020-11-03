package de.hskempten.tabulang.nodes;

import de.hskempten.tabulang.Interpreter;
import de.hskempten.tabulang.tokenizer.Lexer;
import de.hskempten.tabulang.tokenizer.ParseTimeException;
import de.hskempten.tabulang.tokenizer.Token;

public class Directive extends Node {

    Node n;
    public Directive(Lexer l) throws ParseTimeException {
        super(l.lookahead());

        switch (l.lookahead().getType()) {
            case "keyword" -> {
                l.lookahead().getContent();
                switch (l.lookahead().getContent()) {
                    case "function":
                        this.n = new ProceduralF(l);
                        break;
                    case "if":
                        this.n = new IfStmnt(l);
                        break;

                    default:
                        throw new ParseTimeException("Unknown keyword: " + l.lookahead().getContent());
                }
            }
            case "variable" -> this.n = new Assignment(l);
            //case "number" -> this.e = new Number(l);
            default -> l.expectedException("number or variable", l.lookahead());
        }
    }

    @Override
    public Number evaluate(Interpreter i) {
        return n.evaluate(i);
    }
}
