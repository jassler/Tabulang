package de.hskempten.tabulang.interpretTest;

import de.hskempten.tabulang.datatypes.Identifier;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;

public class Interpretation {

    private Interpretation parent;
    private HashMap<String, Object> environment;
    private LinkedHashMap<Object, LinkedList<Object>> group = new LinkedHashMap<>();
    private LinkedHashMap<Object, LinkedList<Object>> variableValue = new LinkedHashMap<>();
    private int internalLoopCounter = 0;
    private int countdown = 0;


    public int getCountdown() {
        return countdown;
    }

    public void setCountdown(int countdown) {
        this.countdown = countdown;
    }

    public LinkedHashMap<Object, LinkedList<Object>> getVariableValue() {
        return variableValue;
    }

    public void setVariableValue(LinkedHashMap<Object, LinkedList<Object>> variableValue) {
        this.variableValue = variableValue;
    }

    public int getInternalLoopCounter() {
        return internalLoopCounter;
    }

    public void setInternalLoopCounter(int internalLoopCounter) {
        this.internalLoopCounter = internalLoopCounter;
    }

    public LinkedHashMap<Object, LinkedList<Object>> getGroup() {
        return group;
    }

    public void setGroup(LinkedHashMap<Object, LinkedList<Object>> group) {
        this.group = group;
    }

    public Interpretation() {
        this.parent = null;
        this.setEnvironment(new HashMap<>());
    }

    public Interpretation(HashMap<String, Object> environment) {
        this.parent = null;
        this.environment = environment;
    }

    public Interpretation(Interpretation parent, HashMap<String, Object> environment) {
        this.parent = parent;
        this.environment = environment;
    }

    public Interpretation getParent() {
        return parent;
    }

    public void setParent(Interpretation parent) {
        this.parent = parent;
    }

    public HashMap<String, Object> getEnvironment() {
        return environment;
    }

    public void setEnvironment(HashMap<String, Object> environment) {
        this.environment = environment;
    }

    //TODO remove if not needed
    public Interpretation findIdentifier(Identifier identifier){
        if(environment.containsKey(identifier.getIdentifierName())){
            return this;
        } else if(parent == null){
            return null;
        } else {
            return parent.findIdentifier(identifier);
        }
    }

    public Interpretation findIdentifierTest2(String identifier){
        if(parent == null){
            if(environment.containsKey(identifier)) {
                return this;
            } else {
                return null;
            }
        } else {
            return parent.findIdentifierTest2(identifier);
        }
    }

    public Interpretation findIdentifier(String identifier){
        if(environment.containsKey(identifier)){
            return this;
        } else if(parent == null){
            return null;
        } else {
            return parent.findIdentifier(identifier);
        }
    }
}
