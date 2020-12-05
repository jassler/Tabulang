package de.hskempten.tabulang.items.ast.nodes;

import de.hskempten.tabulang.items.ast.interfaces.StatementAST;
import de.hskempten.tabulang.items.ast.interfaces.TermAST;

import java.util.ArrayList;

public class ProceduralFBodyAST implements StatementAST {
    String identifier;
    ArrayList<IdentifierAST> identifierList;
    ArrayList<StatementAST> statements;

    public ProceduralFBodyAST(String identifier, ArrayList<IdentifierAST> identifierList, ArrayList<StatementAST> statements) {
        this.setIdentifier(identifier);
        this.setIdentifierList(identifierList);
        this.setStatements(statements);
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public ArrayList<IdentifierAST> getIdentifierList() {
        return identifierList;
    }

    public void setIdentifierList(ArrayList<IdentifierAST> identifierList) {
        this.identifierList = identifierList;
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
        System.out.println(gOffset + " ".repeat(this.getClass().getSimpleName().length()) + " IdentifierList: ");
        for (int i = 0; i < this.getIdentifierList().size(); i++) {
            this.getIdentifierList().get(i).print((gOffset + " ".repeat((this.getClass().getSimpleName() + " IdentifierList: ").length())).length());
        }
        System.out.println(gOffset + " ".repeat(this.getClass().getSimpleName().length()) + " Statements: ");
        for (int i = 0; i < this.getStatements().size(); i++) {
            this.getStatements().get(i).print((gOffset + " ".repeat((this.getClass().getSimpleName() + " Statements: ").length())).length());
        }
    }
}
