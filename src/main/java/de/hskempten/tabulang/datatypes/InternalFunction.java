package de.hskempten.tabulang.datatypes;

import de.hskempten.tabulang.astNodes.IdentifierNode;
import de.hskempten.tabulang.astNodes.StatementNode;

import java.util.ArrayList;

public class InternalFunction extends InternalObject {
    private ArrayList<IdentifierNode> parameters;
    private ArrayList<StatementNode> statements;

    public InternalFunction(ArrayList<IdentifierNode> parameters, ArrayList<StatementNode> statements) {
        super(null);
        this.parameters = parameters;
        this.statements = statements;
    }

    public ArrayList<IdentifierNode> getParameters() {
        return parameters;
    }

    public void setParameters(ArrayList<IdentifierNode> parameters) {
        this.parameters = parameters;
    }

    public ArrayList<StatementNode> getStatements() {
        return statements;
    }

    public void setStatements(ArrayList<StatementNode> statements) {
        this.statements = statements;
    }

    @Override
    public String toString() {
        return "InternalFunction{" +
                "parameters=" + parameters +
                ", statements=" + statements +
                '}';
    }
}
