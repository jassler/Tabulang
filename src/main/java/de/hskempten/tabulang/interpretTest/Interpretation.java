package de.hskempten.tabulang.interpretTest;

import de.hskempten.tabulang.Main;
import de.hskempten.tabulang.datatypes.Identifier;
import de.hskempten.tabulang.datatypes.exceptions.IllegalOperandArgumentException;
import de.hskempten.tabulang.tokenizer.TextPosition;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;

public class Interpretation {

    private Interpretation parent;
    private HashMap<String, Object> environment;
    private int nestingLevel = 1;

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

    public int getNestingLevel() {
        return nestingLevel;
    }

    public void setNestingLevel(int nestingLevel) {
        this.nestingLevel = nestingLevel;
    }


    public Interpretation findIdentifier(String identifier) {
        if (environment.containsKey(identifier)) {
            return this;
        } else if (parent == null) {
            return null;
        } else {
            return parent.findIdentifier(identifier);
        }
    }

    public Interpretation deepCopy() {
        Interpretation deepCopy = new Interpretation(new HashMap<>(this.getEnvironment()));
        if (this.parent != null) {
            deepCopyParents(deepCopy, this.parent);
        }
        return deepCopy;
    }

    public void deepCopyParents(Interpretation current, Interpretation parent) {
        Interpretation deepCopyParent = new Interpretation(new HashMap<>(parent.getEnvironment()));
        current.setParent(deepCopyParent);
        if (parent.getParent() != null) {
            deepCopyParents(deepCopyParent, parent.getParent());
        }
    }

    public Interpretation putValue(String key, Object value) {
        environment.put(key, value);
        return this;
    }

    public Object getValue(String key) {
        return environment.get(key);
    }

    public void exitProgram(Exception e) {
        System.err.println(e.getMessage());
        if(Main.isDebug())
            e.printStackTrace();
        //System.exit(1);
    }

}
