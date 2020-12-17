package de.hskempten.tabulang.datatypes;

import java.util.ArrayList;

public class InternalFunction {
    private ArrayList<Object> parameters;
    private ArrayList<Object> statements;

    public InternalFunction(ArrayList<Object> parameters, ArrayList<Object> statements) {
        this.parameters = parameters;
        this.statements = statements;
    }

    public ArrayList<Object> getParameters() {
        return parameters;
    }

    public void setParameters(ArrayList<Object> parameters) {
        this.parameters = parameters;
    }

    public ArrayList<Object> getStatements() {
        return statements;
    }

    public void setStatements(ArrayList<Object> statements) {
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
