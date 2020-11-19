package de.hskempten.tabulang.interpretTest;

import java.util.HashMap;
import java.util.Stack;

public class Interpretation {

    private HashMap<String, Object> environment;




    public Interpretation() {
        this.setEnvironment(new HashMap<>());
    }

    public HashMap<String, Object> getEnvironment() {
        return environment;
    }

    public void setEnvironment(HashMap<String, Object> environment) {
        this.environment = environment;
    }

}
