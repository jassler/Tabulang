package de.hskempten.tabulang.astNodes;

import de.hskempten.tabulang.astNodes.FunctionCallNode;
import de.hskempten.tabulang.astNodes.GroupNode;
import de.hskempten.tabulang.astNodes.IdentifierNode;
import de.hskempten.tabulang.astNodes.TermNode;
import de.hskempten.tabulang.interpretTest.Interpretation;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;

public class HidingGroupFunctionCallNode extends GroupNode {
    private boolean hiding;
    private boolean area;
    private FunctionCallNode funCall;
    private LinkedHashMap<Object, LinkedList<Object>> variableValueInLoopX = new LinkedHashMap<>();

    public HidingGroupFunctionCallNode(boolean hiding, boolean area, TermNode term, FunctionCallNode funCall) {
        super(term);
        this.setHiding(hiding);
        this.setArea(area);
        this.setFunCall(funCall);
    }


    public boolean isHiding() {
        return hiding;
    }

    public void setHiding(boolean hiding) {
        this.hiding = hiding;
    }

    public boolean isArea() {
        return area;
    }

    public void setArea(boolean area) {
        this.area = area;
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

        if(isLastIteration()){
            System.out.println();
            Iterator iterator = variableValueInLoopX.values().iterator();
            for(Object ignored : getMapValueInLoopX().entrySet()) {
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
}
