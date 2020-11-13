package de.hskempten.tabulang.exampleTest.terminal;

import de.hskempten.tabulang.exampleTest.Interpretation;

public class IdentifierItem implements TerminalItem {
    private String myString; //[a-zA-Z][0-9a-zA-Z]*

    public IdentifierItem(String myString) {
        this.myString = myString;
    }

    public String getMyString() {
        return myString;
    }

    public void setMyString(String myString) {
        this.myString = myString;
    }

    @Override
    public void addToStack(Interpretation i) {
        i.getOperandStack().push(myString);
        i.getTypeStack().push("Identifier");
        System.out.println("Schreibe in OperandStack: " + myString);
    }
}
