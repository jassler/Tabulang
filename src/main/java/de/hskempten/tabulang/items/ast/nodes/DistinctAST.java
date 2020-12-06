package de.hskempten.tabulang.items.ast.nodes;

import de.hskempten.tabulang.items.ast.interfaces.TermAST;

import java.util.ArrayList;

public class DistinctAST implements TermAST {
    ArrayList<String> identifiers;
    TermAST term;

    public DistinctAST(ArrayList<String> identifiers, TermAST term) {
        this.setIdentifiers(identifiers);
        this.setTerm(term);
    }

    public ArrayList<String> getIdentifiers() {
        return identifiers;
    }

    public void setIdentifiers(ArrayList<String> identifiers) {
        this.identifiers = identifiers;
    }

    public TermAST getTerm() {
        return term;
    }

    public void setTerm(TermAST term) {
        this.term = term;
    }
}
