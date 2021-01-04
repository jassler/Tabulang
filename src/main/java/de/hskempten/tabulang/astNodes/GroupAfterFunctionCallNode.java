package de.hskempten.tabulang.astNodes;

import de.hskempten.tabulang.interpretTest.Interpretation;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

public class GroupAfterFunctionCallNode extends GroupNode {
    private FunctionCallNode funCall;
    private LinkedHashMap<Object, LinkedList<Object>> variableValueInLoopX = new LinkedHashMap<>();

    public GroupAfterFunctionCallNode(TermNode term, FunctionCallNode funCall) {
        super(term);
        this.setFunCall(funCall);
    }


    public FunctionCallNode getFunCall() {
        return funCall;
    }

    public void setFunCall(FunctionCallNode funCall) {
        this.funCall = funCall;
    }

    public LinkedHashMap<Object, LinkedList<Object>> getVariableValueInLoopX() {
        return variableValueInLoopX;
    }

    public void setVariableValueInLoopX(LinkedHashMap<Object, LinkedList<Object>> variableValueInLoopX) {
        this.variableValueInLoopX = variableValueInLoopX;
    }

    @Override
    public Object evaluateNode(Interpretation interpretation) {
        Object groupTerm = getTerm().evaluateNode(interpretation);
        //TODO groupmap not necessary, but for test purposed: shows the loop indices per group
        //buildGroupMap(interpretation, groupTerm);
        buildMapValueMap(interpretation, groupTerm);
        buildFunctionParametersMap(interpretation, groupTerm, funCall, variableValueInLoopX);

        if (isLastIteration()) {
            System.out.println();
            Iterator iterator = variableValueInLoopX.values().iterator();
            for (Map.Entry<Object, LinkedList<Object>> group : getMapValueInLoopX().entrySet()) {
                for (TermNode parameter : funCall.getParameters()) {
                    Object value = iterator.next();
                    interpretation.getEnvironment().put(((IdentifierNode) parameter).getIdentifier(), value);
                }
                getResultList().addAll(group.getValue());
                getResultList().add(funCall.evaluateNode(interpretation));
            }
        }
        setLoopCounter(getLoopCounter() + 1);
        return getResultList();
    }

    @Override
    public String toString() {
        return "group after " + getTerm() + " using " + funCall;
    }
}
