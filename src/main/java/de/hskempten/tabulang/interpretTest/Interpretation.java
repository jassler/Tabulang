package de.hskempten.tabulang.interpretTest;

import de.hskempten.tabulang.datatypes.Identifier;

import java.util.HashMap;

public class Interpretation {

    private Interpretation parent;

    private HashMap<String, Object> environment;

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

    public Interpretation findIdentifierTest(String identifier){
        if(environment.containsKey(identifier)){
            return this;
        } else if(parent == null){
            return null;
        } else {
            return parent.findIdentifierTest(identifier);
        }
    }
}
