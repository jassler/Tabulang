package de.hskempten.tabulang.items.ast.nodes;

import de.hskempten.tabulang.items.ast.interfaces.PredAST;
import de.hskempten.tabulang.items.ast.interfaces.StatementAST;
import de.hskempten.tabulang.items.ast.interfaces.TermAST;

public class MarkIfAST implements StatementAST {
    private TermAST markTerm;
    private TermAST asTerm;
    private PredAST ifPred;

    public MarkIfAST(TermAST markTerm, TermAST asTerm, PredAST ifPred) {
        this.setMarkTerm(markTerm);
        this.setAsTerm(asTerm);
        this.setIfPred(ifPred);
    }

    public TermAST getMarkTerm() {
        return markTerm;
    }

    public void setMarkTerm(TermAST markTerm) {
        this.markTerm = markTerm;
    }

    public TermAST getAsTerm() {
        return asTerm;
    }

    public void setAsTerm(TermAST asTerm) {
        this.asTerm = asTerm;
    }

    public PredAST getIfPred() {
        return ifPred;
    }

    public void setIfPred(PredAST ifPred) {
        this.ifPred = ifPred;
    }
}
