package de.hskempten.tabulang.astNodes;

import de.hskempten.tabulang.interpretTest.Interpretation;
import de.hskempten.tabulang.tokenizer.TextPosition;

import java.util.ArrayList;
import java.util.HashMap;

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
        for (int i = 0; i < parameters.size(); i++) {
            nestedInterpretation.getEnvironment().put(parameters.get(i).getIdentifier(), parameters.get(i).evaluateNode(interpretation));
        }

        for (Object statement : statements) {
            if (nestedInterpretation.getEnvironment().containsKey("return")) {
                break;
            }
            ((Node) statement).evaluateNode(nestedInterpretation);
        }

        if (nestedInterpretation.getEnvironment().containsKey("return")) {
            return nestedInterpretation.getEnvironment().get("return");
        }
        return null;
    }

    @Override
    public String toString() {
        return "(" + parameters + ") " +
                "-> {" + statements + "}";
    }
}

