package de.hskempten.tabulang.astNodes.statement.group;

import de.hskempten.tabulang.interpreter.Interpretation;
import de.hskempten.tabulang.astNodes.term.FunctionCallNode;
import de.hskempten.tabulang.astNodes.term.IdentifierNode;
import de.hskempten.tabulang.astNodes.term.TermNode;
import de.hskempten.tabulang.tokenizer.TextPosition;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

public class GroupBeforeFunctionCallNode extends GroupNode {
    private FunctionCallNode funCall;
    private LinkedHashMap<Object, LinkedList<Object>> variableValueInLoopX = new LinkedHashMap<>();

    public GroupBeforeFunctionCallNode(TermNode term, FunctionCallNode funCall, TextPosition textPosition) {
        super(term, textPosition);
        this.setFunCall(funCall);
    }

    public void setFunCall(FunctionCallNode funCall) {
        this.funCall = funCall;
    }

    /**
     * Groups loop iterations according to the 'group' keyword condition before calculating their respective function value.
     *
     * @return list of mapValue values of a group preceded by their function call value for each group (e.g. (fx, x1, x2, fy, y1, y2,...)).
     */
    @Override
    public Object evaluateNode(Interpretation interpretation) {
        Object groupTerm = getTerm().evaluateNode(interpretation);
        buildMapValueMap(interpretation, groupTerm);
        buildFunctionParametersMap(interpretation, groupTerm, funCall, variableValueInLoopX);

        if (isLastIteration()) {
            Iterator iterator = variableValueInLoopX.values().iterator();
            for (Map.Entry<Object, LinkedList<Object>> group : getMapValueInLoopX().entrySet()) {
                for (TermNode parameter : funCall.getParameters()) {
                    Object value = iterator.next();
                    interpretation.getEnvironment().put(((IdentifierNode) parameter).getIdentifier(), value);
                }
                getResultList().add(funCall.evaluateNode(interpretation));
                getResultList().addAll(group.getValue());
            }
        }
        setLoopCounter(getLoopCounter() + 1);
        return getResultList();
    }

    @Override
    public String toString() {
        return "group before " + getTerm() + " using " + funCall;
    }
}
