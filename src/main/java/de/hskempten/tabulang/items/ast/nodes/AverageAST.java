package de.hskempten.tabulang.items.ast.nodes;

import de.hskempten.tabulang.items.ast.interfaces.TermAST;

public class AverageAST implements TermAST {
    String identifier;
    TermAST term;

    public AverageAST(String identifier, TermAST term) {
        this.setIdentifier(identifier);
        this.setTerm(term);
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public TermAST getTerm() {
        return term;
    }

    public void setTerm(TermAST term) {
        this.term = term;
    }
}
