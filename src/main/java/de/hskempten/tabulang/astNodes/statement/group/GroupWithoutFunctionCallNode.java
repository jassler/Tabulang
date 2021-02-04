package de.hskempten.tabulang.astNodes.statement.group;

import de.hskempten.tabulang.interpreter.Interpretation;
import de.hskempten.tabulang.astNodes.term.TermNode;
import de.hskempten.tabulang.tokenizer.TextPosition;

import java.util.LinkedList;
import java.util.Map;

public class GroupWithoutFunctionCallNode extends GroupNode {

    public GroupWithoutFunctionCallNode(TermNode term, TextPosition textPosition) {
        super(term, textPosition);
    }

    /**
     * Groups loop iterations according to the 'group' keyword condition.
     *
     * @return list of mapValue values of a group (e.g. (x1, x2, y1, y2,...)).
     */
    @Override
    public Object evaluateNode(Interpretation interpretation) {
        Object groupTerm = getTerm().evaluateNode(interpretation);
        buildMapValueMap(interpretation, groupTerm);

        if (isLastIteration()) {
            for (Map.Entry<Object, LinkedList<Object>> group : getMapValueInLoopX().entrySet()) {
                getResultList().addAll(group.getValue());
            }
        }
        setLoopCounter(getLoopCounter() + 1);
        return getResultList();
    }

    @Override
    public String toString() {
        return "group " + getTerm();
    }
}
