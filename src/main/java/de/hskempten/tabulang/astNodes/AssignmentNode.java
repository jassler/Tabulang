package de.hskempten.tabulang.astNodes;


import de.hskempten.tabulang.interpretTest.Interpretation;

public class AssignmentNode extends BinaryNode {

    public AssignmentNode(Node leftNode, Node rightNode) {
        super(leftNode, rightNode);
    }

    @Override
    public Object evaluateNode(Interpretation i) {
        Object left = getLeftNode().evaluateNode(i);
        Object right = getRightNode().evaluateNode(i);
        i.getEnvironment().put(left.toString(), right);
        return right;
    }
}
