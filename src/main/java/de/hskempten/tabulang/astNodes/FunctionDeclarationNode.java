package de.hskempten.tabulang.astNodes;

import de.hskempten.tabulang.interpretTest.Interpretation;
import de.hskempten.tabulang.tokenizer.TextPosition;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class FunctionDeclarationNode extends TermNode {
    private ArrayList<IdentifierNode> parameters;
    private ArrayList<StatementNode> statements;

    public FunctionDeclarationNode(ArrayList<IdentifierNode> parameters, ArrayList<StatementNode> statements, TextPosition textPosition) {
        super(textPosition);
        this.parameters = parameters;
        this.statements = statements;
    }

    @Override
    public Object evaluateNode(Interpretation interpretation) {
        Interpretation nestedInterpretation = new Interpretation(interpretation, new HashMap<>());
        //TODO test; dont think this for loop is necessary: the anonymous function uses the parameters directly
        //TODO no need for substitution
        for (int i = 0; i < parameters.size(); i++) {
            nestedInterpretation.getEnvironment().put(parameters.get(i).getIdentifier(), parameters.get(i).evaluateNode(interpretation));
        }

        for (Object statement : statements) {
            if (nestedInterpretation.getEnvironment().containsKey("return")) {
                break;
            }
            ((Node) statement).evaluateNode(nestedInterpretation);
        }
        System.out.println("Function Call Nested Interpretation: ");
        Iterator it = nestedInterpretation.getEnvironment().entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            System.out.println("Key: " + pair.getKey() + " Value: " + pair.getValue());
        }
        System.out.println("");

        if (nestedInterpretation.getEnvironment().containsKey("return")) {
            return nestedInterpretation.getEnvironment().get("return");
        }
        return null;
        //return new InternalFunction(parameters, statements);
    }

    @Override
    public String toString() {
        return "FunctionDeclarationNode{" +
                "parameters=" + parameters +
                ", statements=" + statements +
                "} ";
    }
}

