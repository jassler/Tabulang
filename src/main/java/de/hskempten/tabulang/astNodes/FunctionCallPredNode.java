package de.hskempten.tabulang.astNodes;

import de.hskempten.tabulang.astNodes.Helper.FunctionCallHelper;
import de.hskempten.tabulang.interpretTest.Interpretation;
import de.hskempten.tabulang.tokenizer.TextPosition;

import java.util.ArrayList;

public class FunctionCallPredNode extends PredicateNode {
    private IdentifierNode node;
    private ArrayList<TermNode> parameters;

    public FunctionCallPredNode(IdentifierNode node, ArrayList<TermNode> parameters, TextPosition textPosition) {
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
        return FunctionCallHelper.callFunction(node, interpretation, parameters);
    }

    @Override
    public String toString() {
        return node + "(" + parameters + ")";
    }
}
