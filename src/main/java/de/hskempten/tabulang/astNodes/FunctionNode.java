package de.hskempten.tabulang.astNodes;

import de.hskempten.tabulang.datatypes.Function;
import de.hskempten.tabulang.interpretTest.Interpretation;

import java.util.ArrayList;

public class FunctionNode extends Node{
    private ArrayList<Object> parameters;
    private ArrayList<Object> statements;

    public FunctionNode(ArrayList<Object> parameters, ArrayList<Object> statements) {
        super(NodeType.FUNCTION);
        this.parameters = parameters;
        this.statements = statements;
    }

    @Override
    public Object evaluateNode(Interpretation interpretation) {
        return new Function(parameters, statements);
    }

    @Override
    public String toString() {
        return "FunctionNode{" +
                "parameters=" + parameters +
                ", statements=" + statements +
                "} ";
    }
}

