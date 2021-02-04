package de.hskempten.tabulang.astNodes.statement.group;

import de.hskempten.tabulang.astNodes.term.IdentifierNode;
import de.hskempten.tabulang.astNodes.term.FunctionCallNode;
import de.hskempten.tabulang.astNodes.term.TermNode;
import de.hskempten.tabulang.interpreter.Interpretation;
import de.hskempten.tabulang.tokenizer.TextPosition;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;

public class HidingGroupFunctionCallNode extends GroupNode {
    private FunctionCallNode funCall;
    private LinkedHashMap<Object, LinkedList<Object>> variableValueInLoopX = new LinkedHashMap<>();

    public HidingGroupFunctionCallNode(TermNode term, FunctionCallNode funCall, TextPosition textPosition) {
        super(term, textPosition);
        this.setFunCall(funCall);
    }

    public void setFunCall(FunctionCallNode funCall) {
        this.funCall = funCall;
    }

    /**
     * Groups loop iterations according to the 'group' keyword condition before calculating their respective function value.
     *
     * @return list of function call values for each group without their mapValue values (e.g. (fx, fy,...)).
     */
    @Override
    public Object evaluateNode(Interpretation interpretation) {
        Object groupTerm = getTerm().evaluateNode(interpretation);
        buildMapValueMap(interpretation, groupTerm);
        buildFunctionParametersMap(interpretation, groupTerm, funCall, variableValueInLoopX);

        if (isLastIteration()) {
            Iterator iterator = variableValueInLoopX.values().iterator();
            for (Object ignored : getMapValueInLoopX().entrySet()) {
                for (TermNode parameter : funCall.getParameters()) {
                    Object value = iterator.next();
                    interpretation.getEnvironment().put(((IdentifierNode) parameter).getIdentifier(), value);
                }
                getResultList().add(funCall.evaluateNode(interpretation));
            }
        }
        setLoopCounter(getLoopCounter() + 1);
        return getResultList();
    }

    @Override
    public String toString() {
        return "hiding group " + getTerm();
    }
}
