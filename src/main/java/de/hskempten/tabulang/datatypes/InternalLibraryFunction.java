package de.hskempten.tabulang.datatypes;

import de.hskempten.tabulang.astNodes.IdentifierNode;
import de.hskempten.tabulang.astNodes.StatementNode;
import de.hskempten.tabulang.standardBibliothek.FunctionInterface;
import de.hskempten.tabulang.standardBibliothek.InternalFunction;

import java.util.ArrayList;

public class InternalLibraryFunction {

    private ArrayList<IdentifierNode> parameters;
    private de.hskempten.tabulang.standardBibliothek.FunctionInterface f;

    public InternalLibraryFunction(ArrayList<IdentifierNode> parameters, de.hskempten.tabulang.standardBibliothek.FunctionInterface f) {
        this.parameters = parameters;
        this.f = f;
    }

    public ArrayList<IdentifierNode> getParameters() {
        return parameters;
    }

    public FunctionInterface getFunction() {
        return f;
    }

    @Override
    public String toString() {
        return "InternalLibraryFunction{" +
                "parameters=" + parameters +
                ", f=" + f +
                '}';
    }
}
