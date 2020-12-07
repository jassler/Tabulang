package de.hskempten.tabulang.items.ast.nodes;

import de.hskempten.tabulang.items.ast.interfaces.StatementAST;
import de.hskempten.tabulang.items.ast.interfaces.TermAST;

public class AssignmentAST implements StatementAST {
    private IdentifierAST identifier;
    private TermAST term;

    public AssignmentAST(IdentifierAST identifier, TermAST term) {
        this.setIdentifier(identifier);
        this.setTerm(term);
    }

    public IdentifierAST getIdentifier() {
        return identifier;
    }

    private void setIdentifier(IdentifierAST identifier) {
        this.identifier = identifier;
    }

    public TermAST getTerm() {
        return term;
    }

    public void setTerm(TermAST term) {
        this.term = term;
    }

    public void print(int offset) {
        String gOffset = " ".repeat(offset);
        System.out.println(gOffset + this.getClass().getSimpleName() + " Identifier: " + identifier.getString());
        this.getTerm().print(offset + this.getClass().getSimpleName().length() + 1);
    }
}
