package de.hskempten.tabulang.astNodes;

import de.hskempten.tabulang.datatypes.Table;
import de.hskempten.tabulang.datatypes.Tuple;
import de.hskempten.tabulang.interpretTest.Interpretation;

public class CountVerticalNode extends Node{
    private Node node;

    public CountVerticalNode(Node node) {
        super(NodeType.NODE);
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
        if(o instanceof Table){
            return ((Table<?>) o).getTuples().size();
        } else {
            return 1;
        }
    }
}
