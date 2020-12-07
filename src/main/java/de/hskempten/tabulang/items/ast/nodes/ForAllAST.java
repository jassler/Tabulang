package de.hskempten.tabulang.items.ast.nodes;

import de.hskempten.tabulang.items.ast.interfaces.PredAST;
import de.hskempten.tabulang.items.ast.interfaces.TermAST;

public class ForAllAST implements PredAST {
    IdentifierAST identifier;
    TermAST term;
    PredAST pred;

    public ForAllAST(IdentifierAST identifier, TermAST term, PredAST pred) {
        this.setIdentifier(identifier);
        this.setTerm(term);
        this.setPred(pred);
    }

    public IdentifierAST getIdentifier() {
        return identifier;
    }

    public void setIdentifier(IdentifierAST identifier) {
        this.identifier = identifier;
    }

    public TermAST getTerm() {
        return term;
    }

    public void setTerm(TermAST term) {
        this.term = term;
    }

    public PredAST getPred() {
        return pred;
    }

    public void setPred(PredAST pred) {
        this.pred = pred;
    }

    public void print(int offset) {
        String gOffset = " ".repeat(offset);
        System.out.println(gOffset + this.getClass().getSimpleName() + " Identifier: " + identifier.getString());
        this.getTerm().print(offset + this.getClass().getSimpleName().length() + 1);
        this.getPred().print(offset + this.getClass().getSimpleName().length() + 1);
    }
}
