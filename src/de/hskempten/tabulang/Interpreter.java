package de.hskempten.tabulang;

import de.hskempten.tabulang.nodes.Number;
import de.hskempten.tabulang.nodes.Node;

import java.util.HashMap;

public class Interpreter {

    private HashMap<String, Number> environment;

    public Interpreter() {
        this.environment = new HashMap<>();
    }

    public void evaluate(Node n) {
        n.evaluate(this);
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
