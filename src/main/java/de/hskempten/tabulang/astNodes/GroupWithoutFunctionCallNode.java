package de.hskempten.tabulang.astNodes;

import de.hskempten.tabulang.interpretTest.Interpretation;
import de.hskempten.tabulang.tokenizer.TextPosition;

import java.util.LinkedList;
import java.util.Map;

public class GroupWithoutFunctionCallNode extends GroupNode {

    public GroupWithoutFunctionCallNode(TermNode term, TextPosition textPosition) {
        super(term, textPosition);
    }


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
