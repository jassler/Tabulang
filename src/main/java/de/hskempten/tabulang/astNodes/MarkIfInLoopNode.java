package de.hskempten.tabulang.astNodes;

import de.hskempten.tabulang.datatypes.InternalBoolean;
import de.hskempten.tabulang.datatypes.Tuple;
import de.hskempten.tabulang.datatypes.exceptions.IllegalOperandArgumentException;
import de.hskempten.tabulang.datatypes.exceptions.VariableNotDeclaredException;
import de.hskempten.tabulang.interpretTest.Interpretation;

public class MarkIfInLoopNode extends MarkStatementNode {
    private Node pred;

    public MarkIfInLoopNode(Node middle, Node right, Node pred) {
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
        if (!interpretation.getEnvironment().containsKey("mapValue" + interpretation.getNestingLevel())) {
            throw new VariableNotDeclaredException("mapValue" + interpretation.getNestingLevel());
        }
        Object date = interpretation.getEnvironment().get("mapValue" + interpretation.getNestingLevel());
        try {
            if (date instanceof Tuple) {
                Interpretation nestedInterpretation = interpretation.deepCopy();
                for (int j = 0; j < ((Tuple<?>) date).size(); j++) {
                    Object element = ((Tuple<?>) date).getElements().get(j);
                    Object name = ((Tuple<?>) date).getNames().get(j);
                    nestedInterpretation.getEnvironment().put(name.toString(), element);
                }
                Object predicate = pred.evaluateNode(nestedInterpretation);
                if (predicate instanceof InternalBoolean internalBoolean) {
                    if (internalBoolean.getaBoolean()) {
                        markTupleObject((Tuple) date, interpretation);
                        return null;
                    }
                }
            } else {
                Object predicate = pred.evaluateNode(interpretation);
                if (predicate instanceof InternalBoolean internalBoolean) {
                    if (internalBoolean.getaBoolean()) {
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
