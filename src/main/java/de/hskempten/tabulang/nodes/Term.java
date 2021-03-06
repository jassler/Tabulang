package de.hskempten.tabulang.nodes;

import de.hskempten.tabulang.Interpreter;
import de.hskempten.tabulang.TokenType;
import de.hskempten.tabulang.tokenizer.Lexer;
import de.hskempten.tabulang.tokenizer.ParseTimeException;

import java.util.ArrayList;
import java.util.List;

public class Term extends Node {

    List<Node> terms = new ArrayList<Node>();

    public Term(Lexer l) throws ParseTimeException {
        super(l.lookahead());

        //TODO find break conditions for term (loop, markStmnt, groupStmnt)
        while (!(l.lookahead().getType().equals(";")||l.lookahead().getType().equals(TokenType.BRACKET))){
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

    @Override
    public String toString() {
        StringBuilder body = new StringBuilder();
        for (Node i : terms ){
            if (body.length()>0){
                body.append(",");
            }
            body.append(i.getToken().getContent());
        }
        return  body.toString();
    }
}
