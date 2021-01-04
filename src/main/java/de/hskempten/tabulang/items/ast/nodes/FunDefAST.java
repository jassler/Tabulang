package de.hskempten.tabulang.items.ast.nodes;

import de.hskempten.tabulang.astNodes.IdentifierNode;
import de.hskempten.tabulang.astNodes.Node;
import de.hskempten.tabulang.items.ast.ASTStatementSorter;
import de.hskempten.tabulang.items.ast.interfaces.TermAST;

import java.util.ArrayList;

public class FunDefAST implements TermAST {
    private ArrayList<IdentifierNode> identifiers;
    private ArrayList<Node> statements;

    public FunDefAST(ArrayList<IdentifierNode> identifiers, ArrayList<Node> statements) {
        this.setIdentifiers(identifiers);
        this.setStatements(statements);
    }

    public ArrayList<IdentifierNode> getIdentifiers() {
        return identifiers;
    }

    public void setIdentifiers(ArrayList<IdentifierNode> identifiers) {
        this.identifiers = identifiers;
    }

    public ArrayList<Node> getStatements() {
        return statements;
    }

    public void setStatements(ArrayList<Node> statements) {
        this.statements = ASTStatementSorter.sortStatements(statements);
    }

    /*public void print(int offset) {
        String gOffset = " ".repeat(offset);
        System.out.println(gOffset + this.getClass().getSimpleName());
        for (int i = 0; i < this.getIdentifiers().size(); i++) {
            this.getIdentifiers().get(i).print((gOffset + " ".repeat((this.getClass().getSimpleName()).length())).length());
        }
        System.out.println(" ".repeat(offset + this.getClass().getSimpleName().length() + 1) + "->");
        for (int i = 0; i < this.getStatements().size(); i++) {
            this.getStatements().get(i).print((gOffset + " ".repeat((this.getClass().getSimpleName()).length())).length());
        }
    }*/
}
