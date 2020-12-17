package de.hskempten.tabulang.items.ast.nodes;

import de.hskempten.tabulang.items.ast.interfaces.TermAST;

public class StatementMarkAST implements TermAST {
    private TermAST markTerm;
    private TermAST asTerm;

    public StatementMarkAST(TermAST markTerm, TermAST asTerm) {
        this.setMarkTerm(markTerm);
        this.setAsTerm(asTerm);
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

    public void print(int offset) {
        String gOffset = " ".repeat(offset);
        System.out.println(gOffset + this.getClass().getSimpleName());
        this.getMarkTerm().print((gOffset + " ".repeat((this.getClass().getSimpleName()).length()) + " ").length());
        this.getAsTerm().print((gOffset + " ".repeat((this.getClass().getSimpleName()).length()) + " ").length());
    }
}
