package de.hskempten.tabulang.datatypes;

import de.hskempten.tabulang.astNodes.IdentifierNode;
import de.hskempten.tabulang.astNodes.StatementNode;
import de.hskempten.tabulang.standardBibliothek.FunctionInterface;
import de.hskempten.tabulang.standardBibliothek.InternalFunction;

import java.util.ArrayList;

/**
 * Node for function calls that are directly implemented in Java, most likely in the standardLibrary package.
 * 
 * When the {@link de.hskempten.tabulang.astNodes.FunctionCallNode} interprets this function,
 * it calls {@link FunctionInterface#execute(Object...)}.
 */
public class InternalLibraryFunction extends InternalObject {

    private ArrayList<IdentifierNode> parameters;
    private de.hskempten.tabulang.standardBibliothek.FunctionInterface f;

    public InternalLibraryFunction(ArrayList<IdentifierNode> parameters, de.hskempten.tabulang.standardBibliothek.FunctionInterface f) {
        super(null);
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
