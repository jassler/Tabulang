package de.hskempten.tabulang.items;

import java.math.BigInteger;

public class NumberItem implements LanguageItem {
    private BigInteger myNumber;
    private LanguageItemType itemType = LanguageItemType.ORDINAL_NUMBER;

    public NumberItem(BigInteger myNumber) {
        this.setMyNumber(myNumber);
    }

    public BigInteger getMyNumber() {
        return myNumber;
    }

    public void setMyNumber(BigInteger myNumber) {
        this.myNumber = myNumber;
    }

    @Override
    public LanguageItemType getLanguageItemType() {
        return itemType;
    }
}
