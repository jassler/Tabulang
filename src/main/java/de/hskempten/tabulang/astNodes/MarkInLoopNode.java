package de.hskempten.tabulang.astNodes;

import de.hskempten.tabulang.astNodes.Helper.MarkHelper;
import de.hskempten.tabulang.datatypes.Tuple;
import de.hskempten.tabulang.datatypes.exceptions.IllegalOperandArgumentException;
import de.hskempten.tabulang.datatypes.exceptions.VariableNotDeclaredException;
import de.hskempten.tabulang.interpretTest.Interpretation;
import de.hskempten.tabulang.tokenizer.TextPosition;

public class MarkInLoopNode extends MarkStatementNode {

    public MarkInLoopNode(Node middle, Node right, TextPosition textPosition) {
        super(null, middle, right, textPosition);
    }

    @Override
    public Object evaluateNode(Interpretation interpretation) {
        if (!interpretation.getEnvironment().containsKey("mapValue" + interpretation.getNestingLevel())) {
            throw new VariableNotDeclaredException("mapValue" + interpretation.getNestingLevel());
        }
        Object date = interpretation.getEnvironment().get("mapValue" + interpretation.getNestingLevel());
        try {
            if (date instanceof Tuple tuple) {
                MarkHelper.prepareTupleMark(tuple, interpretation, getMiddleNode(), getRightNode(), getTextPosition());
            } else {
                MarkHelper.setMark(date, interpretation, getMiddleNode(), getRightNode(), getTextPosition());
            }
            return null;
        } catch (IllegalOperandArgumentException illegalOperandArgumentException) {
            illegalOperandArgumentException.printStackTrace();
        }
        return null;
    }
}
