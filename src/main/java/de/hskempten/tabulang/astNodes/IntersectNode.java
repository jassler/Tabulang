package de.hskempten.tabulang.astNodes;

import de.hskempten.tabulang.datatypes.Table;
import de.hskempten.tabulang.interpretTest.Interpretation;

public class IntersectNode extends BinaryNode{
    public IntersectNode(Node leftNode, Node rightNode) {
        super(leftNode, rightNode);
    }

    @Override
    public Object evaluateNode(Interpretation interpretation) {
        Object left =  getLeftNode().evaluateNode(interpretation);
        if(left instanceof Table) {
            Object right = getRightNode().evaluateNode(interpretation);
            if(right instanceof Table) {
                return ((Table) left).intersection((Table) right);
            } else {
                throw new IllegalArgumentException("Expected Table on right side of 'intersect' but got " + right.getClass().getSimpleName());
            }
        } else {
            throw new IllegalArgumentException("Expected Table on left side of 'intersect' but got " + left.getClass().getSimpleName());
        }
    }
}
