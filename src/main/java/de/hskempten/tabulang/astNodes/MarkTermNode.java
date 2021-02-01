package de.hskempten.tabulang.astNodes;

import de.hskempten.tabulang.astNodes.Helper.MarkHelper;
import de.hskempten.tabulang.datatypes.Tuple;
import de.hskempten.tabulang.interpretTest.Interpretation;
import de.hskempten.tabulang.tokenizer.TextPosition;

public class MarkTermNode extends TernaryTermNode {
    public MarkTermNode(Node left, Node middle, Node right, TextPosition textPosition) {
        super(left, middle, right, textPosition);
    }

    @Override
    public Object evaluateNode(Interpretation interpretation) {
        Object date = getLeftNode().evaluateNode(interpretation);
        if (date instanceof Tuple<?> tuple) {
            MarkHelper.prepareTupleMark(tuple, interpretation, getMiddleNode(), getRightNode(), getTextPosition());
        } else {
            MarkHelper.setMark(date, interpretation, getMiddleNode(), getRightNode(), getTextPosition());
        }
        return null;
    }
}
