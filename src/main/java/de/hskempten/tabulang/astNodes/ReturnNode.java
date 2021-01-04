package de.hskempten.tabulang.astNodes;

import de.hskempten.tabulang.datatypes.Identifier;
import de.hskempten.tabulang.datatypes.exceptions.VariableNotDeclaredException;
import de.hskempten.tabulang.interpretTest.Interpretation;

public class ReturnNode extends StatementNode{
    private Node node;

    public ReturnNode(Node node) {
        super();
        this.node = node;
    }

    public Node getNode() {
        return node;
    }

    public void setNode(Node node) {
        this.node = node;
    }

    @Override
    public Object evaluateNode(Interpretation interpretation) {
        Object o = node.evaluateNode(interpretation);
        interpretation.getEnvironment().put("return", o);
        return o;
    }

    @Override
    public String toString() {
        return "return " + node;
    }
}
