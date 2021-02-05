package de.hskempten.tabulang.astNodes.statement.mark;

import de.hskempten.tabulang.datatypes.exceptions.InterpreterException;
import de.hskempten.tabulang.interpreter.Interpretation;
import de.hskempten.tabulang.astNodes.Node;
import de.hskempten.tabulang.astNodes.helper.MarkHelper;
import de.hskempten.tabulang.datatypes.InternalBoolean;
import de.hskempten.tabulang.datatypes.Tuple;
import de.hskempten.tabulang.datatypes.exceptions.IllegalBooleanArgumentException;
import de.hskempten.tabulang.datatypes.exceptions.VariableNotDeclaredException;
import de.hskempten.tabulang.tokenizer.TextPosition;

public class MarkInLoopIfNode extends MarkStatementNode {
    private Node pred;

    public MarkInLoopIfNode(Node middle, Node right, Node pred, TextPosition textPosition) {
        super(null, middle, right, textPosition);
        this.pred = pred;
    }

    public Node getPred() {
        return pred;
    }

    public void setPred(Node pred) {
        this.pred = pred;
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
            Interpretation nestedInterpretation = interpretation.deepCopy();
            for (int j = 0; j < ((Tuple<?>) date).size(); j++) {
                Object element = ((Tuple<?>) date).getElements().get(j);
                Object name = ((Tuple<?>) date).getNames().get(j);
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
            if (!(predicate instanceof InternalBoolean booleanResult)) {
                throw new IllegalBooleanArgumentException(getTextPosition(), predicate.getClass().getSimpleName(), pred.getTextPosition().getContent());
            }
            if (booleanResult.getBoolean()) {
                MarkHelper.setMark(date, interpretation, getMiddleNode(), getRightNode(), getTextPosition());
            }
        }
        return null;
    }
}
