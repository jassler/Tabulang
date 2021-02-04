package de.hskempten.tabulang.astNodes.statement.mark;

import de.hskempten.tabulang.interpreter.Interpretation;
import de.hskempten.tabulang.astNodes.Node;
import de.hskempten.tabulang.astNodes.helper.MarkHelper;
import de.hskempten.tabulang.datatypes.Tuple;
import de.hskempten.tabulang.datatypes.exceptions.VariableNotDeclaredException;
import de.hskempten.tabulang.tokenizer.TextPosition;

public class MarkInLoopNode extends MarkStatementNode {

    public MarkInLoopNode(Node middle, Node right, TextPosition textPosition) {
        super(null, middle, right, textPosition);
    }

    /**
     * Marks the value currently assigned to the 'mapValue' in each iteration of a loop if a condition is met.
     * 'mapValue' is used because the 'mark' keyword is used on it in a loop.
     *
     * @see MarkHelper for more details.
     */
    @Override
    public Object evaluateNode(Interpretation interpretation) {
        if (!interpretation.getEnvironment().containsKey("mapValue" + interpretation.getNestingLevel())) {
            throw new VariableNotDeclaredException("mapValue" + interpretation.getNestingLevel());
        }
        Object date = interpretation.getEnvironment().get("mapValue" + interpretation.getNestingLevel());

        if (date instanceof Tuple<?> tuple) {
            MarkHelper.prepareTupleMark(tuple, interpretation, getMiddleNode(), getRightNode(), getTextPosition());
        } else {
            MarkHelper.setMark(date, interpretation, getMiddleNode(), getRightNode(), getTextPosition());
        }
        return null;
    }
}
