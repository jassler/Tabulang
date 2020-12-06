package de.hskempten.tabulang.items.ast.nodes;

import de.hskempten.tabulang.items.ast.interfaces.TermAST;

import java.util.ArrayList;

public class FunCallAST implements TermAST {
    String identifier;
    ArrayList<TermAST> terms;

    public FunCallAST(String identifier, ArrayList<TermAST> terms) {
        this.setIdentifier(identifier);
        this.setTerms(terms);
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public ArrayList<TermAST> getTerms() {
        return terms;
    }

    public void setTerms(ArrayList<TermAST> terms) {
        this.terms = terms;
    }

    public void print(int offset) {
        String gOffset = " ".repeat(offset);
        System.out.println(gOffset + this.getClass().getSimpleName() + " Identifier: " + identifier);
        System.out.println(gOffset + " ".repeat(this.getClass().getSimpleName().length()) + " Terms: ");
        for (int i = 0; i < this.getTerms().size(); i++) {
            this.getTerms().get(i).print((gOffset + " ".repeat((this.getClass().getSimpleName() + " Terms: ").length())).length());
        }
    }
}
