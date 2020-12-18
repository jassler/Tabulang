package de.hskempten.tabulang.astNodes;

import de.hskempten.tabulang.datatypes.InternalFunction;
import de.hskempten.tabulang.interpretTest.Interpretation;

import java.util.ArrayList;

public class FunctionDeclarationNode extends TermNode{
    private ArrayList<IdentifierNode> parameters;
    private ArrayList<StatementNode> statements;

    public FunctionDeclarationNode(ArrayList<IdentifierNode> parameters, ArrayList<StatementNode> statements) {
        this.parameters = parameters;
        this.statements = statements;
    }

    @Override
    public Object evaluateNode(Interpretation interpretation) {
        return new InternalFunction(parameters, statements);
    }

    @Override
    public String toString() {
        return "FunctionDeclarationNode{" +
                "parameters=" + parameters +
                ", statements=" + statements +
                "} ";
    }
}

