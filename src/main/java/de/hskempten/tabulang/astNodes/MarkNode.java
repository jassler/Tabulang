package de.hskempten.tabulang.astNodes;

import de.hskempten.tabulang.interpretTest.Interpretation;

public class MarkNode extends TernaryTermNode{
    public MarkNode(Node left, Node middle, Node right) {
        super(left, middle, right);
    }

    //TODO Placeholder; remove once parser uses 3 parameters
    public MarkNode(Node left, Node right){
        super(null, left, right);
    }

    @Override
    public Object evaluateNode(Interpretation interpretation) {
        return "MarkNode evaluate has to be implemented";
    }
}
