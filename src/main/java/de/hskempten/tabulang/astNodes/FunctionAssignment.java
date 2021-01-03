package de.hskempten.tabulang.astNodes;

import de.hskempten.tabulang.datatypes.InternalFunction;
import de.hskempten.tabulang.interpretTest.Interpretation;
import de.hskempten.tabulang.items.ast.ASTStatementSorter;

import java.util.ArrayList;

//function f(x1,x2) {s1,....,si}
public class FunctionAssignment extends StatementNode {
    private IdentifierNode identifier;
    private ArrayList<IdentifierNode> identifierList;
    private ArrayList<Node> statements;

    public FunctionAssignment(IdentifierNode identifier, ArrayList<IdentifierNode> identifierList, ArrayList<Node> statements) {
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

    public ArrayList<Node> getStatements() {
        return statements;
    }

    public void setStatements(ArrayList<Node> statements) {
        this.statements = ASTStatementSorter.sortStatements(statements);
    }

    @Override
    public Object evaluateNode(Interpretation interpretation) {
        String o = identifier.getIdentifier();
        InternalFunction newFunc = new InternalFunction(identifierList, statements);
        interpretation.getEnvironment().put(o, newFunc);
        return newFunc;
    }
}
