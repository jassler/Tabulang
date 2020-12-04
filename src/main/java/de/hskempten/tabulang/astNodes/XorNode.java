package de.hskempten.tabulang.astNodes;

import de.hskempten.tabulang.interpretTest.Interpretation;

public class XorNode extends BinaryNode{
    public XorNode(Node leftNode, Node rightNode) {
        super(leftNode, rightNode);
    }

    @Override
    public Object evaluateNode(Interpretation interpretation) {
        return (Boolean) getLeftNode().evaluateNode(interpretation) ^ (Boolean) getRightNode().evaluateNode(interpretation);
    }
}
