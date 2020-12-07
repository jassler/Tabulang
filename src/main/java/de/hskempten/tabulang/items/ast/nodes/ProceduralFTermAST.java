package de.hskempten.tabulang.items.ast.nodes;

import de.hskempten.tabulang.items.ast.interfaces.StatementAST;
import de.hskempten.tabulang.items.ast.interfaces.TermAST;

import java.util.ArrayList;

public class ProceduralFTermAST implements StatementAST {
    private IdentifierAST identifier;
    private ArrayList<IdentifierAST> identifierList;
    private TermAST term;

    public ProceduralFTermAST(IdentifierAST identifier, ArrayList<IdentifierAST> identifierList, TermAST term) {
        this.setIdentifier(identifier);
        this.setIdentifierList(identifierList);
        this.setTerm(term);
    }

    public IdentifierAST getIdentifier() {
        return identifier;
    }

    public void setIdentifier(IdentifierAST identifier) {
        this.identifier = identifier;
    }

    public ArrayList<IdentifierAST> getIdentifierList() {
        return identifierList;
    }

    public void setIdentifierList(ArrayList<IdentifierAST> identifierList) {
        this.identifierList = identifierList;
    }

    public void setTerm(TermAST term) {
        this.term = term;
    }

    public TermAST getTerm() {
        return term;
    }

    public void print(int offset) {
        String gOffset = " ".repeat(offset);
        System.out.println(gOffset + this.getClass().getSimpleName() + " Identifier: " + identifier.getString());
        System.out.println(gOffset + " ".repeat(this.getClass().getSimpleName().length()) + " IdentifierList: ");
        for (int i = 0; i < this.getIdentifierList().size(); i++) {
            this.getIdentifierList().get(i).print((gOffset + " ".repeat((this.getClass().getSimpleName() + " IdentifierList: ").length())).length());
        }
        System.out.println(gOffset + " ".repeat(this.getClass().getSimpleName().length()) + " Term: ");
        this.getTerm().print((gOffset + " ".repeat((this.getClass().getSimpleName() + " Term: ").length())).length());
    }
}
