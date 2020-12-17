package de.hskempten.tabulang.astNodes;

import de.hskempten.tabulang.interpretTest.Interpretation;

public class MarkNode extends TernaryNode{
    public MarkNode(Node left, Node middle, Node right) {
        super(left, middle, right);
    }

    @Override
    public Object evaluateNode(Interpretation interpretation) {
        return null;
    }
}
