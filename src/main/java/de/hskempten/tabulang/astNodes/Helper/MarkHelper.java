package de.hskempten.tabulang.astNodes.Helper;

import de.hskempten.tabulang.astNodes.Node;
import de.hskempten.tabulang.datatypes.InternalNumber;
import de.hskempten.tabulang.datatypes.InternalObject;
import de.hskempten.tabulang.datatypes.InternalString;
import de.hskempten.tabulang.datatypes.Tuple;
import de.hskempten.tabulang.datatypes.exceptions.IllegalOperandArgumentException;
import de.hskempten.tabulang.datatypes.exceptions.IllegalStringOperandArgumentException;
import de.hskempten.tabulang.interpretTest.Interpretation;
import de.hskempten.tabulang.tokenizer.TextPosition;

public class MarkHelper {

    public static void setMark(Object date, Interpretation interpretation, Node middleNode, Node rightNode, TextPosition textPosition) {
        Interpretation nestedInterpretation = interpretation.deepCopy();
        Object annotationKey = middleNode.evaluateNode(interpretation);
        if (!(annotationKey instanceof InternalString keyString)) {
            throw new IllegalStringOperandArgumentException(textPosition, annotationKey.getClass().getSimpleName(), middleNode.getTextPosition().getContent());
        }
        Object annotationValue = rightNode.evaluateNode(nestedInterpretation);
        if (annotationValue == null) {
            ((InternalObject) date).getStyle().getAnnotations().put(keyString.getString(), null);
        } else if (annotationValue instanceof InternalString || annotationValue instanceof InternalNumber) {
            ((InternalObject) date).getStyle().getAnnotations().put(keyString.getString(), annotationValue.toString());
        } else {
            throw new IllegalOperandArgumentException(textPosition, annotationValue.getClass().getSimpleName(), rightNode.getTextPosition());
        }
    }

    public static void prepareTupleMark(Tuple<?> date, Interpretation interpretation, Node middleNode, Node rightNode, TextPosition textPosition) {
        Interpretation nestedInterpretation = interpretation.deepCopy();
        for (int j = 0; j < date.size(); j++) {
            Object element = date.getElements().get(j);
            Object name = date.getNames().get(j);
            nestedInterpretation.getEnvironment().put(name.toString(), element);
        }
        setMark(date, nestedInterpretation, middleNode, rightNode, textPosition);
    }
}
