package de.hskempten.tabulang.nodes;

import de.hskempten.tabulang.Interpreter;
import de.hskempten.tabulang.tokenizer.Token;

public abstract class Node {

    private Token token;

    public Node(Token token) {
        this.token = token;
    }

    public Token getToken() {
        return token;
    }

    public abstract Number evaluate(Interpreter i);
}
