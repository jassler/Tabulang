package de.hskempten.tabulang.items.ast.nodes;

import de.hskempten.tabulang.items.ast.interfaces.StatementAST;
import de.hskempten.tabulang.items.ast.interfaces.TermAST;

public class StatementSetAST implements StatementAST {
    private TermAST term;
    private int nestingLevel;

    public StatementSetAST(TermAST term, int nestingLevel) {
        this.setTerm(term);
        this.setNestingLevel(nestingLevel);
    }

    @Override
    public TermAST getTerm() {
        return term;
    }

    public void setTerm(TermAST term) {
        this.term = term;
    }

    public int getNestingLevel() {
        return nestingLevel;
    }

    public void setNestingLevel(int nestingLevel) {
        this.nestingLevel = nestingLevel;
    }

    public void print(int offset) {
        String gOffset = " ".repeat(offset);
        System.out.println(gOffset + this.getClass().getSimpleName());
        this.getTerm().print((gOffset + " ".repeat((this.getClass().getSimpleName()).length())).length());
    }
}
