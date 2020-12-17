package de.hskempten.tabulang.astNodes;

import de.hskempten.tabulang.interpretTest.Interpretation;

public class OrNode extends BinaryPredicateNode{
    public OrNode(Node leftNode, Node rightNode) {
        super(leftNode, rightNode);
    }

    @Override
    public Object evaluateNode(Interpretation interpretation) {
        Boolean left = getBooleanValue(getLeftNode(), interpretation);
        Boolean right = getBooleanValue(getRightNode(), interpretation);
        return left || right;
    }
}