package de.hskempten.tabulang.astNodes;

import de.hskempten.tabulang.datatypes.Table;
import de.hskempten.tabulang.datatypes.Tuple;
import de.hskempten.tabulang.interpretTest.Interpretation;

public class CountHorizontalNode extends TermNode{
    private Node node;

    public CountHorizontalNode(Node node) {
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
            if(((Table<?>) o).getRows().size() > 0){
                return ((Table<?>) o).getRows().get(0).size();
            } else {
                return 0;
            }
        }
    }
}
