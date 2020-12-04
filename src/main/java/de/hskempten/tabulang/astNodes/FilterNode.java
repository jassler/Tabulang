package de.hskempten.tabulang.astNodes;

import de.hskempten.tabulang.datatypes.Table;
import de.hskempten.tabulang.interpretTest.Interpretation;

import java.util.ArrayList;
import java.util.function.Predicate;

public class FilterNode extends BinaryNode{
    public FilterNode(Node leftNode, Node rightNode) {
        super(leftNode, rightNode);
    }

    @Override
    public Object evaluateNode(Interpretation interpretation) {
        Object o = getLeftNode().evaluateNode(interpretation);
        //TODO
        //return ((Table) o).filter();
        return null;
    }
}
