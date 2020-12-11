package de.hskempten.tabulang.astNodes;

import de.hskempten.tabulang.interpretTest.Interpretation;

public class ReturnNode extends BinaryNode{
    public ReturnNode(Node leftNode, Node rightNode) {
        super(leftNode, rightNode);
    }

    @Override
    public Object evaluateNode(Interpretation interpretation) {
        Object right = getRightNode().evaluateNode(interpretation);
        interpretation.getEnvironment().put("return", right);
        return right;
    }
}
