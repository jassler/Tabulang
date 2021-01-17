package de.hskempten.tabulang.astNodes;

import de.hskempten.tabulang.datatypes.InternalBoolean;
import de.hskempten.tabulang.datatypes.Tuple;
import de.hskempten.tabulang.datatypes.exceptions.IllegalOperandArgumentException;
import de.hskempten.tabulang.interpretTest.Interpretation;
import de.hskempten.tabulang.tokenizer.TextPosition;

public class MarkIfTermNode extends MarkTermNode {
    private Node pred;

    public MarkIfTermNode(Node left, Node middle, Node right, Node pred, TextPosition textPosition) {
        super(left, middle, right, textPosition);
        this.pred = pred;

    }

    public Node getPred() {
        return pred;
    }

    public void setPred(Node pred) {
        this.pred = pred;
    }

    @Override
    public Object evaluateNode(Interpretation interpretation) {
        Object date = getLeftNode().evaluateNode(interpretation);
        try {
            if (date instanceof Tuple<?> tuple) {
                Interpretation nestedInterpretation = interpretation.deepCopy();
                for (int j = 0; j < tuple.size(); j++) {
                    Object element = tuple.getElements().get(j);
                    Object name = tuple.getNames().get(j);
                    nestedInterpretation.getEnvironment().put(name.toString(), element);
                }
                Object predicate = pred.evaluateNode(nestedInterpretation);
                if (predicate instanceof InternalBoolean internalBoolean) {
                    if (internalBoolean.getaBoolean()) {
                        prepareTupleMark(tuple, interpretation);
                    }
                }
            } else {
                Object predicate = pred.evaluateNode(interpretation);
                if (predicate instanceof InternalBoolean internalBoolean) {
                    if (internalBoolean.getaBoolean()) {
                        setMark(date, interpretation);
                    }
                }
            }
        } catch (IllegalOperandArgumentException illegalOperandArgumentException) {
            illegalOperandArgumentException.printStackTrace();
        }
        return date;
    }
}
