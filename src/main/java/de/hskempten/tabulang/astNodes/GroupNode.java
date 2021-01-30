package de.hskempten.tabulang.astNodes;

import de.hskempten.tabulang.interpretTest.Interpretation;
import de.hskempten.tabulang.tokenizer.TextPosition;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

public abstract class GroupNode extends StatementNode {
    private TermNode term;


    private LinkedHashMap<Object, LinkedList<Object>> groupMap = new LinkedHashMap<>();
    private LinkedHashMap<Object, LinkedList<Object>> mapValueInLoopX = new LinkedHashMap<>();
    private LinkedList<Object> resultList = new LinkedList<>();

    private int loopCounter = 0;
    private int nestingLevel = 1;
    private boolean lastIteration = true;

    public GroupNode(TermNode term, TextPosition textPosition) {
        super(textPosition);
        this.term = term;
    }

    public TermNode getTerm() {
        return term;
    }

    public void setTerm(TermNode term) {
        this.term = term;
    }


    public LinkedHashMap<Object, LinkedList<Object>> getGroupMap() {
        return groupMap;
    }

    public void setGroupMap(LinkedHashMap<Object, LinkedList<Object>> groupMap) {
        this.groupMap = groupMap;
    }

    public LinkedHashMap<Object, LinkedList<Object>> getMapValueInLoopX() {
        return mapValueInLoopX;
    }

    public void setMapValueInLoopX(LinkedHashMap<Object, LinkedList<Object>> mapValueInLoopX) {
        this.mapValueInLoopX = mapValueInLoopX;
    }

    public LinkedList<Object> getResultList() {
        return resultList;
    }

    public void setResultList(LinkedList<Object> resultList) {
        this.resultList = resultList;
    }

    public int getLoopCounter() {
        return loopCounter;
    }

    public void setLoopCounter(int loopCounter) {
        this.loopCounter = loopCounter;
    }

    public int getNestingLevel() {
        return nestingLevel;
    }

    public void setNestingLevel(int nestingLevel) {
        this.nestingLevel = nestingLevel;
    }

    public boolean isLastIteration() {
        return lastIteration;
    }

    public void setLastIteration(boolean lastIteration) {
        this.lastIteration = lastIteration;
    }

    public void buildMapValueMap(Interpretation interpretation, Object groupTerm) {
        if (getMapValueInLoopX().containsKey(groupTerm + "/mV")) {
            LinkedList indices = getMapValueInLoopX().get(groupTerm + "/mV");
            indices.add(interpretation.getEnvironment().get("mapValue" + getNestingLevel()));
        } else {
            LinkedList indices = new LinkedList();
            indices.add(interpretation.getEnvironment().get("mapValue" + getNestingLevel()));
            getMapValueInLoopX().put(groupTerm + "/mV", indices);
        }
    }

    public void buildFunctionParametersMap(Interpretation interpretation, Object groupTerm, FunctionCallNode funCall, LinkedHashMap<Object, LinkedList<Object>> variableValueInLoopX) {
        int numberVariable = 0;
        for (TermNode parameter : funCall.getParameters()) {
            numberVariable++;
            if (variableValueInLoopX.containsKey(groupTerm + "/x" + numberVariable)) {
                LinkedList variableValues = variableValueInLoopX.get(groupTerm + "/x" + numberVariable);
                variableValues.add(parameter.evaluateNode(interpretation));
            } else {
                LinkedList variableValues = new LinkedList();
                variableValues.add(parameter.evaluateNode(interpretation));
                variableValueInLoopX.put(groupTerm + "/x" + numberVariable, variableValues);
            }
        }
    }
}
