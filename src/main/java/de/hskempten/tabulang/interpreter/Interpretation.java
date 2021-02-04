package de.hskempten.tabulang.interpreter;


import de.hskempten.tabulang.Main;

import java.util.HashMap;

public class Interpretation {

    private Interpretation parent;
    private HashMap<String, Object> environment;
    private int nestingLevel = 0;

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

    /**
     * Provides a method to search for an identifier in the current environment and its parents.
     * @param identifier the identifier to be found.
     * @return interpretation which contains the specified identifier, null otherwise.
     */
    public Interpretation findIdentifier(String identifier) {
        if (environment.containsKey(identifier)) {
            return this;
        } else if (parent == null) {
            return null;
        } else {
            return parent.findIdentifier(identifier);
        }
    }

    /**
     * Creates a deep copy of an interpretation.
     * @return the copied interpretation.
     */
    public Interpretation deepCopy() {
        Interpretation deepCopy = new Interpretation(new HashMap<>(this.getEnvironment()));
        if (this.parent != null) {
            deepCopyParents(deepCopy, this.parent);
        }
        return deepCopy;
    }

    /**
     * Creates a deep copy of the parents of an interpretation. Used in combination with {@link Interpretation#deepCopy()} to create deep copies.
     */
    public void deepCopyParents(Interpretation current, Interpretation parent) {
        Interpretation deepCopyParent = new Interpretation(new HashMap<>(parent.getEnvironment()));
        current.setParent(deepCopyParent);
        if (parent.getParent() != null) {
            deepCopyParents(deepCopyParent, parent.getParent());
        }
    }

    /**
     * Creates a new key-value pair in the environment.
     * @param key the identifier name.
     * @param value the value to be associated with the key.
     */
    public void putValue(String key, Object value) {
        environment.put(key, value);
    }

    /**
     * Returns the value of an identifier in the environment.
     * @param key the identifier name.
     * @return the associated value.
     */
    public Object getValue(String key) {
        return environment.get(key);
    }

    /**
     * Exits the program.
     * @param e the exception that occurred.
     */
    public void exitProgram(Exception e) {
        System.err.println(e.getMessage());
        if (Main.isDebug()) {
            e.printStackTrace();
        }
        System.exit(1);
    }

}
