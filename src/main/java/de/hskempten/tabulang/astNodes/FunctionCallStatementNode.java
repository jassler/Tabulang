package de.hskempten.tabulang.astNodes;

import de.hskempten.tabulang.datatypes.InternalFunction;
import de.hskempten.tabulang.datatypes.InternalLibraryFunction;
import de.hskempten.tabulang.interpretTest.Interpretation;
import de.hskempten.tabulang.tokenizer.TextPosition;

import java.util.ArrayList;
import java.util.HashMap;

public class FunctionCallStatementNode extends StatementNode{
    private IdentifierNode node;
    private ArrayList<TermNode> parameters;

    public FunctionCallStatementNode(IdentifierNode node, ArrayList<TermNode> parameters, TextPosition textPosition) {
        super(textPosition);
        this.node = node;
        this.parameters = parameters;
    }

    public IdentifierNode getNode() {
        return node;
    }

    public void setNode(IdentifierNode node) {
        this.node = node;
    }

    public ArrayList<TermNode> getParameters() {
        return parameters;
    }

    public void setParameters(ArrayList<TermNode> parameters) {
        this.parameters = parameters;
    }

    @Override
    public Object evaluateNode(Interpretation interpretation) {
        Object identifier = node.evaluateNode(interpretation);

        if(identifier instanceof InternalLibraryFunction f) {
            if (f.getParameters().size() != parameters.size()) {
                throw new IllegalArgumentException("Expected " + f.getParameters().size() + " parameter(s) but got " + parameters.size() + "\n"
                + f.formattedString(node.getIdentifier()));
            }

            Object[] objectParameters = new Object[parameters.size()];
            for(int i = 0; i < objectParameters.length; i++) {
                objectParameters[i] = parameters.get(i).evaluateNode(interpretation);
            }

            var libFunc = f.getFunction();
            return libFunc.compute(objectParameters);

        } else if(identifier instanceof InternalFunction f) {
            Interpretation nestedInterpretation = new Interpretation(interpretation, new HashMap<>());
            if(f.getParameters().size() != parameters.size())
                throw new IllegalArgumentException("Expected " + f.getParameters().size() + " parameter(s) but got " + parameters.size() + "\n"
                        + f.formattedString(node.getIdentifier()));

            for(int i = 0; i < f.getParameters().size(); i++){
                nestedInterpretation.getEnvironment().put(f.getParameters().get(i).getIdentifier(), parameters.get(i).evaluateNode(interpretation));
            }

            for(Object statement : f.getStatements()){
                if(nestedInterpretation.getEnvironment().containsKey("return")) {
                    break;
                }
                ((Node) statement).evaluateNode(nestedInterpretation);
            }
            /*System.out.println("Function Call Nested Interpretation: ");
            Iterator it = nestedInterpretation.getEnvironment().entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry)it.next();
                System.out.println("Key: " + pair.getKey() + " Value: " + pair.getValue());
            }
            System.out.println("");*/

            if(nestedInterpretation.getEnvironment().containsKey("return")){
                return nestedInterpretation.getEnvironment().get("return");
            }
            return null;
        } else
            throw new IllegalArgumentException("Identifier " + identifier + "does not refer to a function.");
    }

    @Override
    public String toString() {
        return node + "(" + parameters +")";
    }
}