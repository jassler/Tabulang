package de.hskempten.tabulang.items.ast.nodes;

import de.hskempten.tabulang.items.ast.interfaces.TermAST;

import java.util.ArrayList;

public class FunDefTermAST implements TermAST {
    private ArrayList<IdentifierAST> identifiers;
    private TermAST term;

    public FunDefTermAST(ArrayList<IdentifierAST> identifiers, TermAST term) {
        this.setIdentifiers(identifiers);
        this.setTerm(term);
    }

    public ArrayList<IdentifierAST> getIdentifiers() {
        return identifiers;
    }

    public void setIdentifiers(ArrayList<IdentifierAST> identifiers) {
        this.identifiers = identifiers;
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
        for (int i = 0; i < this.getIdentifiers().size(); i++) {
            this.getIdentifiers().get(i).print((gOffset + " ".repeat((this.getClass().getSimpleName()).length())).length());
        }
        System.out.println(" ".repeat(offset + this.getClass().getSimpleName().length() + 1) + "->");
        this.getTerm().print((gOffset + " ".repeat((this.getClass().getSimpleName()).length())).length());
    }
}
