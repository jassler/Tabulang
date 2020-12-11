package de.hskempten.tabulang.astNodes;

import de.hskempten.tabulang.interpretTest.Interpretation;

public class IffNode extends BinaryNode{
    public IffNode(Node leftNode, Node rightNode) {
        super(leftNode, rightNode);
    }

    @Override
    public Object evaluateNode(Interpretation interpretation) {
        if(getLeftNode().evaluateNode(interpretation) == getRightNode().evaluateNode(interpretation)){
            return true;
        } else {
            return false;
        }
    }
}
