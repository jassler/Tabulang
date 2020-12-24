package de.hskempten.tabulang.astNodes;

import de.hskempten.tabulang.interpretTest.Interpretation;

public class AndNode extends BinaryPredicateNode{
    public AndNode(PredicateNode leftNode, PredicateNode rightNode) {
        super(leftNode, rightNode);
    }

    @Override
    public Object evaluateNode(Interpretation interpretation) {
        return getBooleanValue(getLeftNode(), interpretation) && getBooleanValue(getRightNode(), interpretation);
    }
}
