package de.hskempten.tabulang.items.ast.nodes;

import de.hskempten.tabulang.items.ast.ASTStatementSorter;
import de.hskempten.tabulang.items.ast.interfaces.StatementAST;
import de.hskempten.tabulang.items.ast.interfaces.TermAST;

import java.util.ArrayList;

public class FunDefStatementsAST implements TermAST {
    private ArrayList<IdentifierAST> identifiers;
    private ArrayList<StatementAST> statements;

    public FunDefStatementsAST(ArrayList<IdentifierAST> identifiers, ArrayList<StatementAST> statements) {
        this.setIdentifiers(identifiers);
        this.setStatements(statements);
    }

    public ArrayList<IdentifierAST> getIdentifiers() {
        return identifiers;
    }

    public void setIdentifiers(ArrayList<IdentifierAST> identifiers) {
        this.identifiers = identifiers;
    }

    public ArrayList<StatementAST> getStatements() {
        return statements;
    }

    public void setStatements(ArrayList<StatementAST> statements) {
        this.statements = ASTStatementSorter.sortStatements(statements);
    }

    public void print(int offset) {
        String gOffset = " ".repeat(offset);
        System.out.println(gOffset + this.getClass().getSimpleName());
        for (int i = 0; i < this.getIdentifiers().size(); i++) {
            this.getIdentifiers().get(i).print((gOffset + " ".repeat((this.getClass().getSimpleName()).length())).length());
        }
        System.out.println(" ".repeat(offset + this.getClass().getSimpleName().length() + 1) + "->");
        for (int i = 0; i < this.getStatements().size(); i++) {
            this.getStatements().get(i).print((gOffset + " ".repeat((this.getClass().getSimpleName()).length())).length());
        }
    }
}
