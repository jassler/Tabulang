package de.hskempten.tabulang.astNodes;

import de.hskempten.tabulang.datatypes.Identifier;
import de.hskempten.tabulang.datatypes.Tuple;
import de.hskempten.tabulang.datatypes.exceptions.VariableNotDeclaredException;
import de.hskempten.tabulang.interpretTest.Interpretation;

public class CountNode extends TermNode{
    private Node node;

    public CountNode(Node node) {
        this.node = node;
    }

    public Node getNode() {
        return node;
    }

    public void setNode(TupleNode node) {
        this.node = node;
    }

    @Override
    public Object evaluateNode(Interpretation interpretation) {
        Object o = node.evaluateNode(interpretation);
        if(o instanceof Identifier){
            o = getIdentifierValue((Identifier) o, interpretation);
        }
        if(o instanceof Tuple){
            return ((Tuple) o).size();
        } else {
            return 1;
        }
    }
}
