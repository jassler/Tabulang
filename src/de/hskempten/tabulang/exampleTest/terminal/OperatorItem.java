package de.hskempten.tabulang.exampleTest.terminal;

import de.hskempten.tabulang.exampleTest.Interpretation;

public class OperatorItem implements TerminalItem {
    //+ - * / div mod ^
    private String myString;

    public OperatorItem(String myString) {
        this.setMyString(myString);
    }

    public String getMyString() {
        return myString;
    }

    public void setMyString(String myString) {
        this.myString = myString;
    }

    @Override
    public void addToStack(Interpretation i) {
        i.getOperatorStack().push(myString);
        System.out.println("Schreibe in OperatorStack: " + myString);
    }
}
