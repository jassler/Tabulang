package de.hskempten.tabulang.astNodes;

import de.hskempten.tabulang.datatypes.InternalBoolean;
import de.hskempten.tabulang.datatypes.exceptions.IllegalBooleanOperandArgumentException;
import de.hskempten.tabulang.interpretTest.Interpretation;
import de.hskempten.tabulang.tokenizer.TextPosition;

public abstract class BinaryPredicateNode extends PredicateNode {
    private Node leftNode;
    private Node rightNode;

    public BinaryPredicateNode(Node leftNode, Node rightNode, TextPosition textPosition) {
        super(textPosition);
        this.leftNode = leftNode;
        this.rightNode = rightNode;
    }

    public Node getLeftNode() {
        return leftNode;
    }

    public void setLeftNode(Node leftNode) {
        this.leftNode = leftNode;
    }

    public Node getRightNode() {
        return rightNode;
    }

    public void setRightNode(Node rightNode) {
        this.rightNode = rightNode;
    }

    public InternalBoolean insertVariableAndEvaluate(Object object, String variableName, Interpretation interpretation){
        interpretation.getEnvironment().put(variableName, object);
        Object result = getRightNode().evaluateNode(interpretation);
        interpretation.getEnvironment().remove(variableName);
        if (!(result instanceof InternalBoolean booleanResult)) {
            throw new IllegalBooleanOperandArgumentException(getTextPosition(), result.getClass().getSimpleName(), getRightNode().getTextPosition().getContent());
        }
        return new InternalBoolean(booleanResult.getaBoolean());
    }
}
