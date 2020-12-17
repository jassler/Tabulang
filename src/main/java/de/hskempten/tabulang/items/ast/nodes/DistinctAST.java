package de.hskempten.tabulang.items.ast.nodes;

import de.hskempten.tabulang.items.ast.interfaces.TermAST;

import java.util.ArrayList;

public class DistinctAST implements TermAST {
    private ArrayList<IdentifierAST> identifiers;
    private TermAST term;

    public DistinctAST(ArrayList<IdentifierAST> identifiers, TermAST term) {
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
}
