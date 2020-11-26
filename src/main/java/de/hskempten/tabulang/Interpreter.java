package de.hskempten.tabulang;

import de.hskempten.tabulang.nodes.Node;
import de.hskempten.tabulang.nodes.Number;
import de.hskempten.tabulang.tokenizer.ParseTimeException;

import java.util.HashMap;

public class Interpreter {

    private HashMap<String, Number> environment;

    public Interpreter() {
        this.environment = new HashMap<>();
    }

    public void evaluate(Node n) throws ParseTimeException {
        try {
            n.evaluate(this);
        } catch(Exception e) {
            // TODO Error Position currently set to first position of node, not the Node that actually generated the error
            // eg. if text is "a := 3 / 0;", it will point to the a, not the 0
            throw new ParseTimeException(n.getToken().getPosition(), "Error while evaluating node: " + e.getLocalizedMessage());
        }
    }

    public void setValue(String s, Number o) {
        environment.put(s, o);
    }

    public Number getValue(String s) {
        // TODO throw VariableNotFoundException or something
        return environment.get(s);
    }

    @Override
    public String toString() {
        return "Interpreter{" +
                "environment=" + environment +
                '}';
    }
}
