package de.hskempten.tabulang.astNodes;

import de.hskempten.tabulang.datatypes.*;
import de.hskempten.tabulang.datatypes.exceptions.IllegalOperandArgumentException;
import de.hskempten.tabulang.interpretTest.Interpretation;

import java.util.ArrayList;
import java.util.HashMap;

public class MarkNode extends TernaryTermNode{
    public MarkNode(Node left, Node middle, Node right) {
        super(left, middle, right);
    }

    //TODO Placeholder; remove once parser uses 3 parameters
    public MarkNode(Node left, Node right){
        super(null, left, right);
    }

    @Override
    public Object evaluateNode(Interpretation interpretation) {
        Object date = getLeft().evaluateNode(interpretation);
        try {
            if (date instanceof Tuple) {
                markTupleObject((Tuple) date, interpretation);
            } else {
                markNonTupleObject(date, interpretation);
            }
            return null;
        } catch (IllegalOperandArgumentException illegalOperandArgumentException){
            illegalOperandArgumentException.printStackTrace();
        }
        return null;
    }

    public void markNonTupleObject(Object date, Interpretation interpretation){
        Object annotationKey = getMiddle().evaluateNode(interpretation);
        Object annotationValue = getRight().evaluateNode(interpretation);
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
        Object annotationKey = getMiddle().evaluateNode(nestedInterpretation1);
        Object annotationValue = getRight().evaluateNode(nestedInterpretation2);
        setMark(date, annotationKey, annotationValue);
    }

    public void setMark(Object date, Object annotationKey, Object annotationValue){
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
