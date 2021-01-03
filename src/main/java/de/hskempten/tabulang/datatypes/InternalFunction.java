package de.hskempten.tabulang.datatypes;

import de.hskempten.tabulang.astNodes.IdentifierNode;
import de.hskempten.tabulang.astNodes.Node;

import java.util.ArrayList;

public class InternalFunction extends InternalObject {
    private ArrayList<IdentifierNode> parameters;
    private ArrayList<Node> statements;

    public InternalFunction(ArrayList<IdentifierNode> parameters, ArrayList<Node> statements) {
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

    public ArrayList<Node> getStatements() {
        return statements;
    }

    public void setStatements(ArrayList<Node> statements) {
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
