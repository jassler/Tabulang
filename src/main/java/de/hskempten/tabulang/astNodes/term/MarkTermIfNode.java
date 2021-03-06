package de.hskempten.tabulang.astNodes.term;

import de.hskempten.tabulang.interpreter.Interpretation;
import de.hskempten.tabulang.astNodes.Node;
import de.hskempten.tabulang.astNodes.helper.MarkHelper;
import de.hskempten.tabulang.datatypes.InternalBoolean;
import de.hskempten.tabulang.datatypes.Tuple;
import de.hskempten.tabulang.tokenizer.TextPosition;

public class MarkTermIfNode extends MarkTermNode {
    private Node pred;

    public MarkTermIfNode(Node left, Node middle, Node right, Node pred, TextPosition textPosition) {
        super(left, middle, right, textPosition);
        this.pred = pred;

    }

    public Node getPred() {
        return pred;
    }

    public void setPred(Node pred) {
        this.pred = pred;
    }

    /**
     * Marks the specified value if a condition is met.
     *
     * @see MarkHelper for more details.
     */
    @Override
    public Object evaluateNode(Interpretation interpretation) {
        Object date = getLeftNode().evaluateNode(interpretation);
        if (date instanceof Tuple<?> tuple) {
            Interpretation nestedInterpretation = interpretation.deepCopy();
            for (int j = 0; j < tuple.size(); j++) {
                Object element = tuple.getElements().get(j);
                Object name = tuple.getNames().get(j);
                nestedInterpretation.getEnvironment().put(name.toString(), element);
            }
            Object predicate = pred.evaluateNode(nestedInterpretation);
            if (predicate instanceof InternalBoolean internalBoolean) {
                if (internalBoolean.getBoolean()) {
                    MarkHelper.prepareTupleMark(tuple, interpretation, getMiddleNode(), getRightNode(), getTextPosition());
                }
            }
        } else {
            Object predicate = pred.evaluateNode(interpretation);
            if (predicate instanceof InternalBoolean internalBoolean) {
                if (internalBoolean.getBoolean()) {
                    MarkHelper.setMark(date, interpretation, getMiddleNode(), getRightNode(), getTextPosition());
                }
            }
        }
        return null;
    }
}
