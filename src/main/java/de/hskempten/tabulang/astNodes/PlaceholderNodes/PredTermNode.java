package de.hskempten.tabulang.astNodes.PlaceholderNodes;

import de.hskempten.tabulang.astNodes.PredicateNode;
import de.hskempten.tabulang.astNodes.TermNode;
import de.hskempten.tabulang.interpretTest.Interpretation;
import de.hskempten.tabulang.items.ast.interfaces.TermAST;

//zB if(5) oder if('hallo')
//TODO
public class PredTermNode extends PredicateNode {
    private TermNode term;

    public PredTermNode(TermNode term) {
        this.setTerm(term);
    }

    public TermNode getTerm() {
        return term;
    }

    public void setTerm(TermNode term) {
        this.term = term;
    }

    @Override
    public Object evaluateNode(Interpretation interpretation) {
        throw new IllegalArgumentException("PredTerm");

    }
}
