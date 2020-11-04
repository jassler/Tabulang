package de.hskempten.tabulang.nodes;

import de.hskempten.tabulang.Interpreter;
import de.hskempten.tabulang.TokenType;
import de.hskempten.tabulang.tokenizer.Lexer;
import de.hskempten.tabulang.tokenizer.ParseTimeException;
import de.hskempten.tabulang.tokenizer.Token;

import java.util.ArrayList;
import java.util.List;

public class FuncBody extends Node {

    List<Node> functionStatements = new ArrayList<Node>();
    public FuncBody(Lexer l) throws ParseTimeException {
        super(l.lookahead());

        switch (l.lookahead().getType()) {
            case "bracket":
                if (!l.lookahead().getContent().equals("{")) {
                    throw new ParseTimeException("Illegal bracket: Expected '{' but got " + l.lookahead().getContent());
                }
                l.getNextTokenAndExpect(TokenType.BRACKET);
                while (!(l.lookahead().getType().equals("bracket") && l.lookahead().getContent().equals("}"))) {
                    if (l.lookahead().getType().equals("keyword")&&l.lookahead().getContent().equals("return")){
                        this.functionStatements.add(new ReturnStmnt(l));
                    }else{
                        this.functionStatements.add(new Statement(l));
                    }
                }
                l.getNextTokenAndExpect(TokenType.BRACKET);
        }

    }

    @Override
    public Number evaluate(Interpreter i) {
        return null;
    }
}
