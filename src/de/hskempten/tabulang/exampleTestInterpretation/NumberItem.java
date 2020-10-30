package de.hskempten.tabulang.exampleTestInterpretation;

import java.math.BigInteger;

public class NumberItem {
    private BigInteger myNumber;

    public NumberItem(BigInteger myNumber) {
        this.setMyNumber(myNumber);
    }

    public BigInteger getMyNumber() {
        return myNumber;
    }

    public void setMyNumber(BigInteger myNumber) {
        this.myNumber = myNumber;
    }

    public BigInteger eval(Interpretation i){
        System.out.println("Gerade evaluiert: " + this.getClass().getSimpleName() + ". Interpretation: " + myNumber);
        return myNumber;
    }
}
