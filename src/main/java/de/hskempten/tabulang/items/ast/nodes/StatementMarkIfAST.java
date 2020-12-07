package de.hskempten.tabulang.items.ast.nodes;

import de.hskempten.tabulang.items.ast.interfaces.PredAST;
import de.hskempten.tabulang.items.ast.interfaces.TermAST;

public class StatementMarkIfAST implements TermAST {
    private TermAST markTerm;
    private TermAST asTerm;
    private PredAST pred;

    public StatementMarkIfAST(TermAST markTerm, TermAST asTerm, PredAST pred) {
        this.setMarkTerm(markTerm);
        this.setAsTerm(asTerm);
        this.setPred(pred);
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

    public PredAST getPred() {
        return pred;
    }

    public void setPred(PredAST pred) {
        this.pred = pred;
    }

    public void print(int offset) {
        String gOffset = " ".repeat(offset);
        System.out.println(gOffset + this.getClass().getSimpleName());
        this.getMarkTerm().print((gOffset + " ".repeat((this.getClass().getSimpleName()).length()) + " ").length());
        this.getAsTerm().print((gOffset + " ".repeat((this.getClass().getSimpleName()).length()) + " ").length());
        this.getPred().print((gOffset + " ".repeat((this.getClass().getSimpleName()).length()) + " ").length());
    }
}
