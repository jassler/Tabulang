package de.hskempten.tabulang.nodes;

import de.hskempten.tabulang.Interpreter;
import de.hskempten.tabulang.tokenizer.Lexer;
import de.hskempten.tabulang.tokenizer.ParseTimeException;

public class AnyStatement extends Node {

    Node n;

    public AnyStatement(Lexer l) throws ParseTimeException {
        super(l.lookahead());

        switch (l.lookahead().getType()) {
            case "keyword" -> {
                /*
                    case "function":
                        this.n = new ProceduralF(l);
                        break;

                    case "if":
                        this.n = new IfStmnt(l);
                        break;
                    */
                //throw new ParseTimeException("Unknown keyword: " + l.lookahead().getContent());
                switch (l.lookahead().getContent()) {
                    case "function" -> this.n = new ProceduralF(l);
                    case "return" -> this.n = new ReturnStmnt(l);
                    case "set" -> this.n = new SetStmnt(l);
                    case "hiding", "group" -> this.n = new GroupStmnt(l);
                    default -> this.n = new Statement(l);
                }
            }
            //case "variable" -> this.n = new AssignmentAST(l);
            //case "number" -> this.e = new NumberAST(l);
            default -> this.n = new Statement(l);  //l.expectedException("number or variable", l.lookahead());
        }
    }

    @Override
    public Number evaluate(Interpreter i) {
        return n.evaluate(i);
    }
}
