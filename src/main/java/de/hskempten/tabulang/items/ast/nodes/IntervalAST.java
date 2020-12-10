package de.hskempten.tabulang.items.ast.nodes;

import de.hskempten.tabulang.items.ast.interfaces.TermAST;

public class IntervalAST implements TermAST {
    private TermAST firstTerm;
    private TermAST secondTerm;

    public IntervalAST(TermAST firstTerm, TermAST secondTerm) {
        this.setFirstTerm(firstTerm);
        this.setSecondTerm(secondTerm);
    }

    public TermAST getFirstTerm() {
        return firstTerm;
    }

    public void setFirstTerm(TermAST firstTerm) {
        this.firstTerm = firstTerm;
    }

    public TermAST getSecondTerm() {
        return secondTerm;
    }

    public void setSecondTerm(TermAST secondTerm) {
        this.secondTerm = secondTerm;
    }

    public void print(int offset) {
        String gOffset = " ".repeat(offset);
        System.out.println(gOffset + this.getClass().getSimpleName());
        this.getFirstTerm().print(offset + this.getClass().getSimpleName().length() + 1);
        System.out.println(" ".repeat(offset + this.getClass().getSimpleName().length() + 1) + "...");
        this.getSecondTerm().print(offset + this.getClass().getSimpleName().length() + 1);
    }
}
