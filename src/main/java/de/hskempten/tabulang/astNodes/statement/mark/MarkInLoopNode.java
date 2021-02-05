package de.hskempten.tabulang.astNodes.statement.mark;

import de.hskempten.tabulang.datatypes.exceptions.InterpreterException;
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
     * @throws InterpreterException if no set-Statement got executed before the mark-Statement got executed.
     * @see MarkHelper for more details.
     */
    @Override
    public Object evaluateNode(Interpretation interpretation) {
        if (!interpretation.getEnvironment().containsKey("mapValue" + interpretation.getNestingLevel())) {
            throw new InterpreterException("Missing set-Statement before mark can be executed. No valid date to mark.");
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
