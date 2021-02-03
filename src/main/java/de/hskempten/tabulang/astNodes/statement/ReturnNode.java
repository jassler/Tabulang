package de.hskempten.tabulang.astNodes.statement;

import de.hskempten.tabulang.interpreter.Interpretation;
import de.hskempten.tabulang.astNodes.Node;
import de.hskempten.tabulang.tokenizer.TextPosition;

public class ReturnNode extends StatementNode {
    private Node node;

    public ReturnNode(Node node, TextPosition textPosition) {
        super(textPosition);
        this.node = node;
    }

    public Node getNode() {
        return node;
    }

    public void setNode(Node node) {
        this.node = node;
    }

    /**
     * Assigns specified value to the key 'return' in the interpretation.
     *
     * @return value that got assigned to 'return'.
     */
    @Override
    public Object evaluateNode(Interpretation interpretation) {
        Object o = node.evaluateNode(interpretation);
        interpretation.getEnvironment().put("return", o);
        return o;
    }

    @Override
    public String toString() {
        return "return " + node;
    }
}
