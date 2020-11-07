package de.hskempten.tabulang.items;

import java.math.BigInteger;

public class NumberItem implements LanguageItem {
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
}
