package de.hskempten.tabulang.astNodes;


import de.hskempten.tabulang.interpretTest.Interpretation;

public class NullNode extends TermNode{
    public NullNode() {
    }

    @Override
    public Object evaluateNode(Interpretation interpretation) {
        return null;
    }
}
