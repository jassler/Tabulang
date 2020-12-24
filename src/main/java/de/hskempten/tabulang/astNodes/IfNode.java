package de.hskempten.tabulang.astNodes;

import de.hskempten.tabulang.interpretTest.Interpretation;

import java.util.HashMap;

public class IfNode extends BinaryStatementNode{
    public IfNode(Node leftNode, Node rightNode) {
        super(leftNode, rightNode);
    }

    @Override
    public Object evaluateNode(Interpretation interpretation) {
        Object left = getLeftNode().evaluateNode(interpretation);
        if(left instanceof Boolean){
            if((Boolean) left) {
                return getRightNode().evaluateNode(interpretation);
            } else {
                return false;
            }
        } else {
            throw new IllegalArgumentException("Expected Boolean but got: " + left.getClass().getSimpleName());
        }
    }
}
