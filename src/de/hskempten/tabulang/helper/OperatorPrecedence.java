package de.hskempten.tabulang.helper;

public class OperatorPrecedence {
    private static OperatorPrecedence instance;

    public OperatorPrecedence() {
    }

    static {
        instance = new OperatorPrecedence();
    }

    public static OperatorPrecedence getInstance() {
        return instance;
    }

    public int precedence(String operator){
        //TODO ":=" AssignmentItem evtl auch hinzufügen
        switch (operator){
            case "(":
                return 1;
            case ":=":
                return 2;
            case "+":
            case "-":
                return 3;
            case "div":
            case "mod":
            case "*":
            case "/":
                return 4;
            case "^":
                return 5;
            default: System.out.println("Falsches Zeichen");
            return 0;
        }
    }
}
