package de.hskempten.tabulang.datatypes;

import de.hskempten.tabulang.astNodes.term.IdentifierNode;
import de.hskempten.tabulang.astNodes.term.FunctionCallNode;
import de.hskempten.tabulang.standardLibrary.InternalFunction;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Node for function calls that are directly implemented in Java, most likely in the standardLibrary package.
 * 
 * When the {@link FunctionCallNode} interprets this function,
 * it calls {@link InternalFunction#compute(Object...)}.
 */
public class InternalLibraryFunction extends InternalObject {

    private ArrayList<IdentifierNode> parameters;
    private de.hskempten.tabulang.standardLibrary.InternalFunction f;

    public InternalLibraryFunction(ArrayList<IdentifierNode> parameters, de.hskempten.tabulang.standardLibrary.InternalFunction f) {
        super(null);
        this.parameters = parameters;
        this.f = f;
    }

    public InternalLibraryFunction(de.hskempten.tabulang.standardLibrary.InternalFunction f, String... parameters) {
        super(null);
        this.parameters = Stream.of(parameters).map(IdentifierNode::new).collect(Collectors.toCollection(ArrayList::new));
        this.f = f;
    }

    public ArrayList<IdentifierNode> getParameters() {
        return parameters;
    }

    public InternalFunction getFunction() {
        return f;
    }

    @Override
    public String toString() {
        return "InternalLibraryFunction{" +
                "parameters=" + parameters +
                ", f=" + f +
                '}';
    }

    public String formattedString(String functionName) {
        return functionName + "("
                + parameters.stream().map(IdentifierNode::getIdentifier).collect(Collectors.joining(", "))
                + ")";
    }
}
