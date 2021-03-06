package de.hskempten.tabulang.nodes;

import de.hskempten.tabulang.Interpreter;
import de.hskempten.tabulang.TokenType;
import de.hskempten.tabulang.tokenizer.Lexer;
import de.hskempten.tabulang.tokenizer.ParseTimeException;
import de.hskempten.tabulang.tokenizer.Token;

public class Assignment extends Node {

    private String identifier;
    private Expression value;

    public Assignment(Lexer lexer) throws ParseTimeException {
        super(lexer.lookahead());

        Token idToken = lexer.getNextTokenAndExpect(TokenType.VARIABLE);
        lexer.getNextTokenAndExpect(TokenType.ASSIGN);
        Expression value = new Expression(lexer);

        this.identifier = idToken.getContent();
        this.value = value;

        lexer.getNextTokenAndExpect(TokenType.SEMICOLON);
    }

    public String getIdentifier() {
        return identifier;
    }

    public Expression getValue() {
        return value;
    }

    public Number evaluate(Interpreter i) {
        Number n = value.evaluate(i);
        i.setValue(identifier, value.evaluate(i));
        return n;
    }

    @Override
    public String toString() {
        return "AssignmentItem{" +
                "identifier='" + identifier + '\'' +
                ", value=" + value +
                '}';
    }
}
