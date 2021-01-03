package de.hskempten.tabulang.astNodes.PlaceholderNodes;

import de.hskempten.tabulang.astNodes.*;
import de.hskempten.tabulang.datatypes.InternalFunction;
import de.hskempten.tabulang.datatypes.exceptions.VariableNotDeclaredException;
import de.hskempten.tabulang.interpretTest.Interpretation;
import de.hskempten.tabulang.items.ast.interfaces.TermAST;
import de.hskempten.tabulang.items.ast.nodes.FunCallAST;

import java.util.*;

public class GroupBeforeFunctionCallNode extends GroupNode {
    private boolean hiding;
    private boolean area;
    private FunctionCallNode funCall;
    private LinkedHashMap<Object, LinkedList<Object>> variableValueInLoopX = new LinkedHashMap<>();

    public GroupBeforeFunctionCallNode(boolean hiding, boolean area, TermNode term, FunctionCallNode funCall) {
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
            for(Map.Entry<Object, LinkedList<Object>> group : getMapValueInLoopX().entrySet()) {
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
}
