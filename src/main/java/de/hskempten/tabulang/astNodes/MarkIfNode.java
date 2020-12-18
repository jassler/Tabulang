package de.hskempten.tabulang.astNodes;

import de.hskempten.tabulang.interpretTest.Interpretation;

public class MarkIfNode extends QuaternaryTermNode{
    public MarkIfNode(Node left, Node middleLeft, Node middleRight, Node right) {
        super(left, middleLeft, middleRight, right);
    }

    //TODO placeholder; remove once parser uses 4 parameters
    public MarkIfNode(Node middleLeft, Node middleRight, Node right) {
        super(null, middleLeft, middleRight, right);
    }

    @Override
    public Object evaluateNode(Interpretation interpretation) {
        return null;
    }
}
