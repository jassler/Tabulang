package de.hskempten.tabulang.items;

public class Assignment implements AssignmentAST {
    private IdentifierItem myIdentifier;
    //':='
    private TermItem myTerm;
    //';'


    public Assignment(IdentifierItem identifier, TermItem term) {
        this.myIdentifier = identifier;
        TermParser.instance.parseTerm(term);
        this.setTerm(term);
    }

    public IdentifierItem getIdentifier() {
        return myIdentifier;
    }

    public void setIdentifier(IdentifierItem identifier) {
        this.myIdentifier = identifier;
    }

    public TermItem getTerm() {
        return myTerm;
    }

    public void setTerm(TermItem term) {
        this.myTerm = term;
    }
}
