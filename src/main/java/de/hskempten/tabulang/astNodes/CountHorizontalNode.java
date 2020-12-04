package de.hskempten.tabulang.astNodes;

import de.hskempten.tabulang.datatypes.Table;
import de.hskempten.tabulang.datatypes.Tuple;
import de.hskempten.tabulang.interpretTest.Interpretation;

public class CountHorizontalNode extends Node{
    private Node node;

    public CountHorizontalNode(Node node) {
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
        if(!(o instanceof Table)){
            if(o instanceof Tuple){
                return ((Tuple) o).size();
            } else {
                return 1;
            }
        } else {
            if(((Table<?>) o).getTuples().size() > 0){
                return ((Table<?>) o).getTuples().get(0).size();
            } else {
                return 0;
            }
        }
    }
}
