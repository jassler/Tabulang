package de.hskempten.tabulang.nodes;

import de.hskempten.tabulang.Interpreter;
import de.hskempten.tabulang.TokenType;
import de.hskempten.tabulang.tokenizer.Lexer;
import de.hskempten.tabulang.tokenizer.ParseTimeException;
import de.hskempten.tabulang.tokenizer.Token;

public class Loop extends Node {

    private final String identifier;
    private final Term term;
    private final LoopBody body;

    public Loop(Lexer l) throws ParseTimeException {
        super(l.lookahead());

        l.getNextToken();
        Token idToken = l.getNextTokenAndExpect(TokenType.VARIABLE);
        this.identifier = idToken.getContent();
        l.getNextTokenAndExpect(TokenType.KEYWORD);
        this.term = new Term(l);
        Token bracket = l.getNextTokenAndExpect(TokenType.BRACKET);
        if (!bracket.getContent().equals("{"))
            throw new ParseTimeException("Illegal bracket: Expected '{' but got " + l.lookahead().getContent());
        this.body = new LoopBody(l);
        if (!l.lookahead().getContent().equals("}"))
            throw new ParseTimeException("Illegal bracket: Expected '}' but got " + l.lookahead().getContent());


    }

    @Override
    public Number evaluate(Interpreter i) {
        return null;
    }

    @Override
    public String toString() {
        return "Loop{" +
                "identifier='" + identifier + '\'' +
                ", term='" + term + '\'' +
                ", body={" + body + "}" +
                '}';
    }
}
