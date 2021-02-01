package de.hskempten.tabulang.astNodes.Helper;

import de.hskempten.tabulang.astNodes.IdentifierNode;
import de.hskempten.tabulang.astNodes.Node;
import de.hskempten.tabulang.astNodes.TermNode;
import de.hskempten.tabulang.datatypes.InternalFunction;
import de.hskempten.tabulang.datatypes.InternalLibraryFunction;
import de.hskempten.tabulang.interpretTest.Interpretation;

import java.util.ArrayList;
import java.util.HashMap;

public class FunctionCallHelper {

    public static Object callFunction(IdentifierNode node, Interpretation interpretation, ArrayList<TermNode> parameters) {
        Object identifier = node.evaluateNode(interpretation);

        if (identifier instanceof InternalLibraryFunction f) {
            if (f.getParameters().size() != parameters.size()) {
                throw new IllegalArgumentException("Expected " + f.getParameters().size() + " parameter(s) but got " + parameters.size() + "\n"
                        + f.formattedString(node.getIdentifier()));
            }

            Object[] objectParameters = new Object[parameters.size()];
            for (int i = 0; i < objectParameters.length; i++) {
                objectParameters[i] = parameters.get(i).evaluateNode(interpretation);
            }

            var libFunc = f.getFunction();
            return libFunc.compute(objectParameters);

        } else if (identifier instanceof InternalFunction f) {
            Interpretation nestedInterpretation = new Interpretation(interpretation, new HashMap<>());
            if (f.getParameters().size() != parameters.size())
                throw new IllegalArgumentException("Expected " + f.getParameters().size() + " parameter(s) but got " + parameters.size() + "\n"
                        + f.formattedString(node.getIdentifier()));

            for (int i = 0; i < f.getParameters().size(); i++) {
                nestedInterpretation.getEnvironment().put(f.getParameters().get(i).getIdentifier(), parameters.get(i).evaluateNode(interpretation));
            }

            for (Object statement : f.getStatements()) {
                if (nestedInterpretation.getEnvironment().containsKey("return")) {
                    break;
                }
                ((Node) statement).evaluateNode(nestedInterpretation);
            }
            if (nestedInterpretation.getEnvironment().containsKey("return")) {
                return nestedInterpretation.getEnvironment().get("return");
            }
            return null;
        } else
            throw new IllegalArgumentException("Identifier " + identifier + "does not refer to a function.");
    }
}
