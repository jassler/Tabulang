package de.hskempten.tabulang.astNodes.PlaceholderNodes;

import de.hskempten.tabulang.astNodes.IdentifierNode;
import de.hskempten.tabulang.astNodes.StatementNode;
import de.hskempten.tabulang.interpretTest.Interpretation;
import de.hskempten.tabulang.items.ast.ASTStatementSorter;
import de.hskempten.tabulang.items.ast.interfaces.StatementAST;
import de.hskempten.tabulang.items.ast.nodes.IdentifierAST;

import java.util.ArrayList;

public class ProceduralFBodyNodeTest extends StatementNode {
    private IdentifierNode identifier;
    private ArrayList<IdentifierNode> identifierList;
    private ArrayList<StatementNode> statements;

    public ProceduralFBodyNodeTest(IdentifierNode identifier, ArrayList<IdentifierNode> identifierList, ArrayList<StatementNode> statements) {
        this.setIdentifier(identifier);
        this.setIdentifierList(identifierList);
        this.setStatements(statements);
    }

    public IdentifierNode getIdentifier() {
        return identifier;
    }

    public void setIdentifier(IdentifierNode identifier) {
        this.identifier = identifier;
    }

    public ArrayList<IdentifierNode> getIdentifierList() {
        return identifierList;
    }

    public void setIdentifierList(ArrayList<IdentifierNode> identifierList) {
        this.identifierList = identifierList;
    }

    public ArrayList<StatementNode> getStatements() {
        return statements;
    }

    public void setStatements(ArrayList<StatementNode> statements) {
        this.statements = ASTStatementSorter.sortStatements(statements);
    }

    @Override
    public Object evaluateNode(Interpretation interpretation) {
        return null;
    }
}
