package de.hskempten.tabulang.astNodes.statement;

import de.hskempten.tabulang.interpreter.Interpretation;
import de.hskempten.tabulang.astNodes.helper.FunctionCallHelper;
import de.hskempten.tabulang.astNodes.term.IdentifierNode;
import de.hskempten.tabulang.astNodes.term.TermNode;
import de.hskempten.tabulang.tokenizer.TextPosition;

import java.util.ArrayList;

public class FunctionCallStatementNode extends StatementNode {
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

    /**
     * @see FunctionCallHelper#callFunction(IdentifierNode, Interpretation, ArrayList, TextPosition)
     */
    @Override
    public Object evaluateNode(Interpretation interpretation) {
        return FunctionCallHelper.callFunction(node, interpretation, parameters, getTextPosition());
    }

    @Override
    public String toString() {
        return node + "(" + parameters + ")";
    }
}
