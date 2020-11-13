package de.hskempten.tabulang.exampleTest.terminal;

import de.hskempten.tabulang.exampleTest.Interpretation;
import de.hskempten.tabulang.exampleTest.Interpretation;

public class QuotedStringItem implements TerminalItem{
    //any char except the quote char
    private String myString;

    public QuotedStringItem(String myString) {
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
        i.getOperandStack().push(myString);
        i.getTypeStack().push("String");
        System.out.println("Schreibe in OperandStack: " + myString);
    }
}
