package de.hskempten.tabulang.items.ast.nodes;

import de.hskempten.tabulang.items.ast.interfaces.StatementAST;
import de.hskempten.tabulang.items.ast.interfaces.TermAST;

public class MarkAST implements StatementAST {
    private TermAST markTerm;
    private TermAST asTerm;

    public MarkAST(TermAST markTerm, TermAST asTerm) {
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
}
