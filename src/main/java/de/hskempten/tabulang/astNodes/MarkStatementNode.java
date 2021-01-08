package de.hskempten.tabulang.astNodes;

import de.hskempten.tabulang.datatypes.InternalNumber;
import de.hskempten.tabulang.datatypes.InternalObject;
import de.hskempten.tabulang.datatypes.InternalString;
import de.hskempten.tabulang.datatypes.Tuple;
import de.hskempten.tabulang.datatypes.exceptions.IllegalOperandArgumentException;
import de.hskempten.tabulang.datatypes.exceptions.IllegalStringOperandArgumentException;
import de.hskempten.tabulang.interpretTest.Interpretation;
import de.hskempten.tabulang.tokenizer.TextPosition;

public class MarkStatementNode extends TernaryStatementNode {
    public MarkStatementNode(Node left, Node middle, Node right, TextPosition textPosition) {
        super(left, middle, right, textPosition);
    }

    @Override
    public Object evaluateNode(Interpretation interpretation) {
        Object date = getLeftNode().evaluateNode(interpretation);

        if (date instanceof Tuple<?> tuple) {
            prepareTupleMark(tuple, interpretation);
        } else {
            setMark(date, interpretation);
        }
        return null;
    }

    public void setMark(Object date, Interpretation interpretation) {
        Interpretation nestedInterpretation = interpretation.deepCopy();
        Object annotationKey = getMiddleNode().evaluateNode(interpretation);
        if (!(annotationKey instanceof InternalString keyString)) {
            throw new IllegalStringOperandArgumentException(getTextPosition(), annotationKey.getClass().getSimpleName(), getMiddleNode().getTextPosition().getContent());
        }
        Object annotationValue = getRightNode().evaluateNode(nestedInterpretation);
        if (annotationValue == null) {
            ((InternalObject) date).getStyle().getAnnotations().put(keyString.getString(), null);
        } else if (annotationValue instanceof InternalString || annotationValue instanceof InternalNumber) {
            ((InternalObject) date).getStyle().getAnnotations().put(keyString.getString(), annotationValue.toString());
        } else {
            throw new IllegalOperandArgumentException(getTextPosition(), annotationValue.getClass().getSimpleName(), getRightNode().getTextPosition());
        }
    }

    public void prepareTupleMark(Tuple<?> date, Interpretation interpretation) {
        Interpretation nestedInterpretation = interpretation.deepCopy();
        for (int j = 0; j < date.size(); j++) {
            Object element = date.getElements().get(j);
            Object name = date.getNames().get(j);
            nestedInterpretation.getEnvironment().put(name.toString(), element);
        }
        setMark(date, nestedInterpretation);
    }
}
