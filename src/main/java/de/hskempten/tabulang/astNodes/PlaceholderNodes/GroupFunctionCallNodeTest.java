package de.hskempten.tabulang.astNodes.PlaceholderNodes;

import de.hskempten.tabulang.astNodes.FunctionCallNode;
import de.hskempten.tabulang.astNodes.IdentifierNode;
import de.hskempten.tabulang.astNodes.StatementNode;
import de.hskempten.tabulang.astNodes.TermNode;
import de.hskempten.tabulang.datatypes.InternalFunction;
import de.hskempten.tabulang.datatypes.exceptions.VariableNotDeclaredException;
import de.hskempten.tabulang.interpretTest.Interpretation;
import de.hskempten.tabulang.items.ast.interfaces.TermAST;
import de.hskempten.tabulang.items.ast.nodes.FunCallAST;

import java.util.*;

public class GroupFunctionCallNodeTest extends StatementNode {
    private boolean hiding;
    private boolean area;
    private TermNode term;
    private FunctionCallNode funCall;
    private LinkedHashMap<Object, LinkedList<Object>> groupMap = new LinkedHashMap<>();
    private LinkedHashMap<Object, LinkedList<Object>> variableValueInLoopX = new LinkedHashMap<>();
    private LinkedHashMap<Object, LinkedList<Object>> mapValueInLoopX = new LinkedHashMap<>();
    private LinkedList<Object> resultList = new LinkedList<>();

    private int loopCounter = 0;
    private int nestingLevel = 1;
    private boolean lastIteration = true;

    public GroupFunctionCallNodeTest(boolean hiding, boolean area, TermNode term, FunctionCallNode funCall) {
        this.setHiding(hiding);
        this.setArea(area);
        this.setTerm(term);
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

    public TermNode getTerm() {
        return term;
    }

    public void setTerm(TermNode term) {
        this.term = term;
    }

    public FunctionCallNode getFunCall() {
        return funCall;
    }

    public void setFunCall(FunctionCallNode funCall) {
        this.funCall = funCall;
    }

    public LinkedHashMap<Object, LinkedList<Object>> getGroupMap() {
        return groupMap;
    }

    public void setGroupMap(LinkedHashMap<Object, LinkedList<Object>> groupMap) {
        this.groupMap = groupMap;
    }

    public LinkedHashMap<Object, LinkedList<Object>> getVariableValueInLoopX() {
        return variableValueInLoopX;
    }

    public void setVariableValueInLoopX(LinkedHashMap<Object, LinkedList<Object>> variableValueInLoopX) {
        this.variableValueInLoopX = variableValueInLoopX;
    }

    public int getLoopCounter() {
        return loopCounter;
    }

    public void setLoopCounter(int loopCounter) {
        this.loopCounter = loopCounter;
    }

    public LinkedHashMap<Object, LinkedList<Object>> getMapValueInLoopX() {
        return mapValueInLoopX;
    }

    public void setMapValueInLoopX(LinkedHashMap<Object, LinkedList<Object>> mapValueInLoopX) {
        this.mapValueInLoopX = mapValueInLoopX;
    }

    public boolean isLastIteration() {
        return lastIteration;
    }

    public void setLastIteration(boolean lastIteration) {
        this.lastIteration = lastIteration;
    }

    public LinkedList<Object> getResultList() {
        return resultList;
    }

    public void setResultList(LinkedList<Object> resultList) {
        this.resultList = resultList;
    }

    public int getNestingLevel() {
        return nestingLevel;
    }

    public void setNestingLevel(int nestingLevel) {
        this.nestingLevel = nestingLevel;
    }

    @Override
    public Object evaluateNode(Interpretation interpretation) {
        Object groupTermObject = term.evaluateNode(interpretation);

        if(groupMap.containsKey(groupTermObject)){
            LinkedList indices = groupMap.get(groupTermObject);
            indices.add(loopCounter);
        } else {
            LinkedList indices = new LinkedList();
            indices.add(loopCounter);
            groupMap.put(groupTermObject, indices);
        }

        int numberVariable = 0;
        for(TermNode parameter : funCall.getParameters()){
            numberVariable++;
            if(variableValueInLoopX.containsKey(groupTermObject + "/x" + numberVariable)){
                LinkedList variableValues = variableValueInLoopX.get(groupTermObject + "/x" + numberVariable);
                variableValues.add(parameter.evaluateNode(interpretation));
            } else {
                LinkedList variableValues = new LinkedList();
                variableValues.add(parameter.evaluateNode(interpretation));
                variableValueInLoopX.put(groupTermObject + "/x" + numberVariable, variableValues);
            }
        }

        if(mapValueInLoopX.containsKey(groupTermObject + "/mV")){
            LinkedList indices = mapValueInLoopX.get(groupTermObject + "/mV");
            indices.add(interpretation.getEnvironment().get("mapValue"+nestingLevel));
        } else {
            LinkedList indices = new LinkedList();
            indices.add(interpretation.getEnvironment().get("mapValue"+nestingLevel));
            mapValueInLoopX.put(groupTermObject + "/mV", indices);
        }

        System.out.println();
        if(lastIteration) {
            Iterator iter = groupMap.entrySet().iterator();
            System.out.print("GroupList: Durchlauf " + loopCounter + " \n");
            while (iter.hasNext()) {
                Map.Entry pair = (Map.Entry) iter.next();
                System.out.println("Key: " + pair.getKey() + " Value: " + pair.getValue());
            }

            System.out.println();
            Iterator itera = variableValueInLoopX.entrySet().iterator();
            System.out.print("VariableList: Durchlauf " + loopCounter + " \n");
            while (itera.hasNext()) {
                Map.Entry pair = (Map.Entry) itera.next();
                System.out.println("Key: " + pair.getKey() + " Value: " + pair.getValue());
            }

            System.out.println();
            Iterator iterat = mapValueInLoopX.entrySet().iterator();
            System.out.print("MapValue: Durchlauf " + loopCounter + " \n");
            while (iterat.hasNext()) {
                Map.Entry pair = (Map.Entry) iterat.next();
                System.out.println("Key: " + pair.getKey() + " Value: " + pair.getValue());
            }
        }
        loopCounter++;

        if(lastIteration){
            System.out.println();
            //System.out.println("Beginne nun Funktionsaufrufe");
            for(Object group : groupMap.entrySet()) {
                for (TermNode parameter : funCall.getParameters()) {
                    Object key = variableValueInLoopX.keySet().iterator().next();
                    Object value = variableValueInLoopX.get(key);
                    variableValueInLoopX.remove(key);
                    interpretation.getEnvironment().put(((IdentifierNode) parameter).getIdentifier(), value);
                    //System.out.println("Eingesetzter Parameter: " + interpretation.getEnvironment().get(((IdentifierNode) parameter).getIdentifier()));
                }
                resultList.add(funCall.evaluateNode(interpretation));
                Object key = mapValueInLoopX.keySet().iterator().next();
                Object value = mapValueInLoopX.get(key);
                mapValueInLoopX.remove(key);
                resultList.addAll((LinkedList) value);
            }
            /*System.out.println("ResultList:");
            for(Object o : resultList){
                System.out.print(o + ", ");
            }*/
        }
        return resultList;
    }
}
