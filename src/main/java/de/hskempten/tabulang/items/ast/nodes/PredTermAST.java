package de.hskempten.tabulang.items.ast.nodes;

import de.hskempten.tabulang.items.ast.interfaces.PredAST;
import de.hskempten.tabulang.items.ast.interfaces.TermAST;

public class PredTermAST implements PredAST {
    private TermAST term;

    public PredTermAST(TermAST term) {
        this.setTerm(term);
    }

    public TermAST getTerm() {
        return term;
    }

    public void setTerm(TermAST term) {
        this.term = term;
    }

    public void print(int offset) {
        String gOffset = " ".repeat(offset);
        System.out.println(gOffset + this.getClass().getSimpleName());
        this.getTerm().print((gOffset + " ".repeat((this.getClass().getSimpleName()).length())).length());
    }
}
