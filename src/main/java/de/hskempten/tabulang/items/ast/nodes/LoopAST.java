package de.hskempten.tabulang.items.ast.nodes;

import de.hskempten.tabulang.items.ast.interfaces.StatementAST;
import de.hskempten.tabulang.items.ast.interfaces.TermAST;

import java.util.ArrayList;

public class LoopAST implements StatementAST, TermAST {
    String identifier;
    TermAST term;
    ArrayList<StatementAST> statements;

    public LoopAST(String identifier, TermAST term, ArrayList<StatementAST> statements) {
        this.setIdentifier(identifier);
        this.setTerm(term);
        this.setStatements(statements);
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    @Override
    public TermAST getTerm() {
        return term;
    }

    public void setTerm(TermAST term) {
        this.term = term;
    }

    public ArrayList<StatementAST> getStatements() {
        return statements;
    }

    public void setStatements(ArrayList<StatementAST> statements) {
        this.statements = statements;
    }

    public void print(int offset) {
        String gOffset = " ".repeat(offset);
        System.out.println(gOffset + this.getClass().getSimpleName() + " Identifier: " + identifier);
        System.out.println(gOffset + " ".repeat(this.getClass().getSimpleName().length()) + " Term: ");
        this.getTerm().print((gOffset + " ".repeat((this.getClass().getSimpleName() + " Term: ").length())).length());
        System.out.println(gOffset + " ".repeat(this.getClass().getSimpleName().length()) + " Statements: ");
        for (int i = 0; i < this.getStatements().size(); i++) {
            this.getStatements().get(i).print((gOffset + " ".repeat((this.getClass().getSimpleName() + " Statements: ").length())).length());
        }
    }
}
