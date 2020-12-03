package de.hskempten.tabulang.items.ast.nodes;

import de.hskempten.tabulang.items.ast.interfaces.StatementAST;
import de.hskempten.tabulang.items.ast.interfaces.TermAST;

public class AssignmentAST implements StatementAST {
    private String identifier;
    private TermAST term;

    public AssignmentAST(String identifier, TermAST term) {
        this.setIdentifier(identifier);
        this.setTerm(term);
    }

    private String getIdentifier() {
        return identifier;
    }

    private void setIdentifier(String identifier) {
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
        System.out.println(gOffset + this.getClass().getSimpleName() + " Identifier: " + identifier);
        this.getTerm().print(offset + this.getClass().getSimpleName().length() + 1);
    }
}
