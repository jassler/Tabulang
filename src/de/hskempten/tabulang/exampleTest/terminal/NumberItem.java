package de.hskempten.tabulang.exampleTest.terminal;

import de.hskempten.tabulang.exampleTest.Interpretation;

public class NumberItem implements TerminalItem {
    private Number myNumber;

    public NumberItem(Number myNumber) {
        this.setMyNumber(myNumber);
    }

    public Number getMyNumber() {
        return myNumber;
    }

    public void setMyNumber(Number myNumber) {
        this.myNumber = myNumber;
    }

    @Override
    public void addToStack(Interpretation i) {
        i.getOperandStack().push(myNumber.toString());
        i.getTypeStack().push("Number");
        System.out.println("Schreibe in OperandStack: " + myNumber);
    }
}
