package de.hskempten.tabulang.astNodes;

import de.hskempten.tabulang.interpretTest.Interpretation;

public class NotNode extends PredicateNode{
    private Node node;

    public NotNode(Node node) {
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
        return ! (Boolean) node.evaluateNode(interpretation);
    }
}
