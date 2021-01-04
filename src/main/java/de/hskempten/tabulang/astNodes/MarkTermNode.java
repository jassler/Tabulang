package de.hskempten.tabulang.astNodes;

import de.hskempten.tabulang.datatypes.InternalNumber;
import de.hskempten.tabulang.datatypes.InternalObject;
import de.hskempten.tabulang.datatypes.InternalString;
import de.hskempten.tabulang.datatypes.Tuple;
import de.hskempten.tabulang.datatypes.exceptions.IllegalOperandArgumentException;
import de.hskempten.tabulang.interpretTest.Interpretation;

public class MarkTermNode extends TernaryTermNode {
    public MarkTermNode(Node left, Node middle, Node right) {
        super(left, middle, right);
    }

    @Override
    public Object evaluateNode(Interpretation interpretation) {
        Object date = getLeftNode().evaluateNode(interpretation);
        try {
            if (date instanceof Tuple) {
                markTupleObject((Tuple) date, interpretation);
            } else {
                markNonTupleObject(date, interpretation);
            }
            return null;
        } catch (IllegalOperandArgumentException illegalOperandArgumentException) {
            illegalOperandArgumentException.printStackTrace();
        }
        return null;
    }

    public void markNonTupleObject(Object date, Interpretation interpretation) {
        Object annotationKey = getMiddleNode().evaluateNode(interpretation);
        Object annotationValue = getRightNode().evaluateNode(interpretation);
        setMark(date, annotationKey, annotationValue);
    }

    public void markTupleObject(Tuple date, Interpretation interpretation) {
        Interpretation nestedInterpretation1 = interpretation.deepCopy();
        Interpretation nestedInterpretation2 = interpretation.deepCopy();
        for (int j = 0; j < ((Tuple<?>) date).size(); j++) {
            Object element = ((Tuple<?>) date).getElements().get(j);
            Object name = ((Tuple<?>) date).getNames().get(j);
            nestedInterpretation1.getEnvironment().put(name.toString(), element);
            nestedInterpretation2.getEnvironment().put(name.toString(), element);
        }
        Object annotationKey = getMiddleNode().evaluateNode(nestedInterpretation1);
        Object annotationValue = getRightNode().evaluateNode(nestedInterpretation2);
        setMark(date, annotationKey, annotationValue);
    }

    public void setMark(Object date, Object annotationKey, Object annotationValue) {
        if (annotationKey instanceof InternalString) {
            if (annotationValue == null) {
                ((InternalObject) date).getStyle().getAnnotations().put(((InternalString) annotationKey).getString(), null);
            } else if (annotationValue instanceof InternalString || annotationValue instanceof InternalNumber) {
                ((InternalObject) date).getStyle().getAnnotations().put(((InternalString) annotationKey).getString(), annotationValue.toString());
            } else {
                throw new IllegalOperandArgumentException("'" + date + " (" + date.getClass() + ") mark "
                        + annotationKey + " (" + annotationKey.getClass() + ") as "
                        + annotationValue + " (" + annotationValue.getClass() + ") can not be executed."
                        + "Allowed Operands: '[Tuple] mark [String] as [String/Number/null]'.");
            }
        } else {
            throw new IllegalOperandArgumentException("'" + date + " (" + date.getClass() + ") mark "
                    + annotationKey + " (" + annotationKey.getClass() + ") as "
                    + annotationValue + " (" + annotationValue.getClass() + ") can not be executed."
                    + "Allowed Operands: '[Tuple] mark [String] as [String/Number/null]'.");
        }
    }
}
