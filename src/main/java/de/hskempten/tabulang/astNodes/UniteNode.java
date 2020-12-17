package de.hskempten.tabulang.astNodes;

import de.hskempten.tabulang.datatypes.Table;
import de.hskempten.tabulang.interpretTest.Interpretation;

public class UniteNode extends BinaryNode{
    public UniteNode(Node leftNode, Node rightNode) {
        super(leftNode, rightNode);
    }

    @Override
    public Object evaluateNode(Interpretation interpretation) {
        Object left =  getLeftNode().evaluateNode(interpretation);
        if(left instanceof Table) {
            Object right = getRightNode().evaluateNode(interpretation);
            if(right instanceof Table) {
                return ((Table) left).union((Table) right);
            } else {
                throw new IllegalArgumentException("Expected Table on right side of 'unite' but got " + right.getClass().getSimpleName());
            }
        } else {
            throw new IllegalArgumentException("Expected Table on left side of 'unite' but got " + left.getClass().getSimpleName());
        }
    }
}
