package de.hskempten.tabulang.astNodes.statement.mark;

import de.hskempten.tabulang.interpreter.Interpretation;
import de.hskempten.tabulang.astNodes.Node;
import de.hskempten.tabulang.astNodes.helper.MarkHelper;
import de.hskempten.tabulang.astNodes.statement.TernaryStatementNode;
import de.hskempten.tabulang.datatypes.Tuple;
import de.hskempten.tabulang.tokenizer.TextPosition;

public class MarkStatementNode extends TernaryStatementNode {
    public MarkStatementNode(Node left, Node middle, Node right, TextPosition textPosition) {
        super(left, middle, right, textPosition);
    }

    /**
     * Marks the specified date.
     *
     * @see MarkHelper for more details.
     */
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
