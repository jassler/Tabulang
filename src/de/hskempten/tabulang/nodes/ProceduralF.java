package de.hskempten.tabulang.nodes;

import de.hskempten.tabulang.Interpreter;
import de.hskempten.tabulang.TokenType;
import de.hskempten.tabulang.tokenizer.Lexer;
import de.hskempten.tabulang.tokenizer.ParseTimeException;
import de.hskempten.tabulang.tokenizer.Token;

import java.util.ArrayList;
import java.util.List;

public class ProceduralF extends Node {

    private String identifier;
    private VList parameters;
    private Node function;


    public ProceduralF(Lexer l) throws ParseTimeException {
        super(l.lookahead());

        l.getNextToken();
        Token idToken = l.getNextTokenAndExpect(TokenType.VARIABLE);
        Token bracket = l.getNextTokenAndExpect(TokenType.BRACKET);
        if (!bracket.getContent().equals("("))
            throw new ParseTimeException("Illegal bracket: Expected '(' but got " + l.lookahead().getContent());
        VList parameterList = new VList(l);
        if (!l.lookahead().getContent().equals(")"))
            throw new ParseTimeException("Illegal bracket: Expected ')' but got " + l.lookahead().getContent());

        Node funcBody;
        l.getNextToken();
        switch (l.lookahead().getType()) {
            case "bracket" -> {
                switch (l.lookahead().getContent()) {
                    case "{":
                        funcBody = new FuncBody(l);
                        break;
                    case "(":
                        funcBody = new Term(l);
                        break;
                    default:
                        throw new ParseTimeException("Illegal bracket: Expected '{' but got " + l.lookahead().getContent());
                }
            }
            default -> funcBody = new Term(l);
        }


        this.identifier = idToken.getContent();
        this.parameters = parameterList; //.getVList()
        this.function = funcBody;


    }

    @Override
    public Number evaluate(Interpreter i) {
        return null;
    }

    @Override
    public String toString() {
        return "ProceduralF{" +
                "identifier='" + identifier + '\'' +
                ", parameters=" + parameters +
                ", function=" + function +
                '}';
    }
}
