package de.hskempten.tabulang.astNodes;

import de.hskempten.tabulang.datatypes.Tuple;
import de.hskempten.tabulang.datatypes.exceptions.IllegalOperandArgumentException;
import de.hskempten.tabulang.interpretTest.Interpretation;

import java.util.HashMap;

public class MarkIfNode extends MarkNode{
    private Node pred;

    public MarkIfNode(Node left, Node middle, Node right, Node pred) {
        super(left, middle, right);
        this.pred = pred;

    }

    //TODO placeholder; remove once parser uses 4 parameters
    public MarkIfNode(Node middle, Node right, Node pred) {
        super(null, middle, right);
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
        Object date = getLeft().evaluateNode(interpretation);
        try {
            if (date instanceof Tuple) {
                Interpretation nestedInterpretation = interpretation.deepCopy();
                for (int j = 0; j < ((Tuple<?>) date).size(); j++) {
                    Object element = ((Tuple<?>) date).getElements().get(j);
                    Object name = ((Tuple<?>) date).getNames().get(j);
                    nestedInterpretation.getEnvironment().put(name.toString(), element);
                }
                Object predicate = pred.evaluateNode(nestedInterpretation);
                if (predicate instanceof Boolean) {
                    if ((Boolean) predicate) {
                        markTupleObject((Tuple) date, interpretation);
                        return null;
                    }
                }
            } else {
                Object predicate = pred.evaluateNode(interpretation);
                if (predicate instanceof Boolean) {
                    if ((Boolean) predicate) {
                        markNonTupleObject(date, interpretation);
                        return null;
                    }
                }
            }
        } catch (IllegalOperandArgumentException illegalOperandArgumentException){
            illegalOperandArgumentException.printStackTrace();
        }
        return null;
    }
}
