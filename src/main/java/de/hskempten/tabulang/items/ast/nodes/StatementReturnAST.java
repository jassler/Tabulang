package de.hskempten.tabulang.items.ast.nodes;

import de.hskempten.tabulang.items.ast.interfaces.StatementAST;
import de.hskempten.tabulang.items.ast.interfaces.TermAST;

public class StatementReturnAST implements StatementAST {
    private TermAST term;

    public StatementReturnAST(TermAST term) {
        this.setTerm(term);
    }

    public void setTerm(TermAST term) {
        this.term = term;
    }

    public TermAST getTerm() {
        return term;
    }

    public void print(int offset) {
        String gOffset = " ".repeat(offset);
        System.out.println(gOffset + this.getClass().getSimpleName());
        this.getTerm().print((gOffset + " ".repeat((this.getClass().getSimpleName()).length()) + " ").length());
    }
}
