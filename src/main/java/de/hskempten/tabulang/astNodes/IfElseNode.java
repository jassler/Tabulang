package de.hskempten.tabulang.astNodes;

import de.hskempten.tabulang.interpretTest.Interpretation;

import java.util.HashMap;

public class IfElseNode extends TernaryStatementNode{
    public IfElseNode(Node left, Node middle, Node right) {
        super(left, middle, right);
    }

    @Override
    public Object evaluateNode(Interpretation interpretation) {
        Object left = getLeft().evaluateNode(interpretation);
        if (left instanceof Boolean) {
            if ((Boolean) left) {
                return getMiddle().evaluateNode(interpretation);
            } else {
                return getRight().evaluateNode(interpretation);
            }
        } else {
            throw new IllegalArgumentException("Expected Boolean but got: " + left.getClass().getSimpleName());
        }
    }
}
