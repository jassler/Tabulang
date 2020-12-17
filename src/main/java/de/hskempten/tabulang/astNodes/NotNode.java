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
        Object pred = node.evaluateNode(interpretation);
        if(pred instanceof Boolean){
            return !((Boolean)pred);
        } else {
            throw new IllegalArgumentException("Expected Boolean but got " + pred.getClass().getSimpleName());
        }
    }
}
