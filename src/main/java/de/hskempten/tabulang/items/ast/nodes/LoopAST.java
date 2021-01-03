package de.hskempten.tabulang.items.ast.nodes;

import de.hskempten.tabulang.astNodes.IdentifierNode;
import de.hskempten.tabulang.astNodes.Node;
import de.hskempten.tabulang.astNodes.TermNode;
import de.hskempten.tabulang.items.ast.ASTStatementSorter;
import de.hskempten.tabulang.items.ast.interfaces.StatementAST;
import de.hskempten.tabulang.items.ast.interfaces.TermAST;

import java.util.ArrayList;

public class LoopAST implements StatementAST, TermAST {
    private IdentifierNode identifier;
    private TermNode term;
    private ArrayList<Node> statements;
    private int nestingLevel;

    public LoopAST(IdentifierNode identifier, TermNode term, ArrayList<Node> statements, int nestingLevel) {
        this.setIdentifier(identifier);
        this.setTerm(term);
        this.setStatements(statements);
        this.setNestingLevel(nestingLevel);
    }

    public IdentifierNode getIdentifier() {
        return identifier;
    }

    public void setIdentifier(IdentifierNode identifier) {
        this.identifier = identifier;
    }


    @Override
    public void print(int offset) {
        ;
    }

    public void setTerm(TermNode term) {
        this.term = term;
    }

    public ArrayList<Node> getStatements() {
        return statements;
    }

    public void setStatements(ArrayList<Node> statements) {
        this.statements = ASTStatementSorter.sortStatements(statements);
    }

    public int getNestingLevel() {
        return nestingLevel;
    }

    public void setNestingLevel(int nestingLevel) {
        this.nestingLevel = nestingLevel;
    }

    /*public void print(int offset) {
        String gOffset = " ".repeat(offset);
        System.out.println(gOffset + this.getClass().getSimpleName() + " Identifier: " + identifier.getString());
        System.out.println(gOffset + " ".repeat(this.getClass().getSimpleName().length()) + " Term: ");
        this.getTerm().print((gOffset + " ".repeat((this.getClass().getSimpleName() + " Term: ").length())).length());
        System.out.println(gOffset + " ".repeat(this.getClass().getSimpleName().length()) + " Statements: ");
        for (int i = 0; i < this.getStatements().size(); i++) {
            this.getStatements().get(i).print((gOffset + " ".repeat((this.getClass().getSimpleName() + " Statements: ").length())).length());
        }
    }*/
}
