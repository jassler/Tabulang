package de.hskempten.tabulang.nodes;

import de.hskempten.tabulang.Interpreter;
import de.hskempten.tabulang.tokenizer.Lexer;
import de.hskempten.tabulang.tokenizer.ParseTimeException;
import de.hskempten.tabulang.tokenizer.Token;

public class AnyStatement extends Node {

    Node n;

    public AnyStatement(Lexer l) throws ParseTimeException {
        super(l.lookahead());

        switch (l.lookahead().getType()) {
            case "keyword" -> {
                switch (l.lookahead().getContent()) {
                    case "return":
                        this.n = new ReturnStmnt(l);
                        break;
                    case "set":
                        this.n = new SetStmnt(l);
                    case "hiding":
                    case "group":
                        this.n = new GroupStmnt(l);
                        break;
                    /*
                    case "function":
                        this.n = new ProceduralF(l);
                        break;

                    case "if":
                        this.n = new IfStmnt(l);
                        break;
                    */
                    default:
                        this.n = new Statement(l);
                        break;
                        //throw new ParseTimeException("Unknown keyword: " + l.lookahead().getContent());
                }
            }
            //case "variable" -> this.n = new Assignment(l);
            //case "number" -> this.e = new Number(l);
            default -> this.n = new Statement(l);  //l.expectedException("number or variable", l.lookahead());
        }
    }

    @Override
    public Number evaluate(Interpreter i) {
        return n.evaluate(i);
    }
}
