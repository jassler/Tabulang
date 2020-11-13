package de.hskempten.tabulang.exampleTest;

import java.util.HashMap;
import java.util.Stack;

public class Interpretation {

    private HashMap<String, Object> environment;

    private Stack<String> operandStack = new Stack<String>();

    private Stack<Number> numberStack = new Stack<Number>();

    private Stack<String> typeStack = new Stack<String>();

    private Stack<String> operatorStack = new Stack<String>();

    public Stack<String> getOperandStack() {
        return operandStack;
    }

    public void setOperandStack(Stack<String> operandStack) {
        this.operandStack = operandStack;
    }


    public Interpretation() {
        this.setEnvironment(new HashMap<>());
    }

    public HashMap<String, Object> getEnvironment() {
        return environment;
    }

    public void setEnvironment(HashMap<String, Object> environment) {
        this.environment = environment;
    }


    public Stack<String> getTypeStack() {
        return typeStack;
    }

    public void setTypeStack(Stack<String> typeStack) {
        this.typeStack = typeStack;
    }

    public Stack<String> getOperatorStack() {
        return operatorStack;
    }

    public void setOperatorStack(Stack<String> operatorStack) {
        this.operatorStack = operatorStack;
    }

    public Stack<Number> getNumberStack() {
        return numberStack;
    }

    public void setNumberStack(Stack<Number> numberStack) {
        this.numberStack = numberStack;
    }
}
