package de.hskempten.tabulang.items;

import de.hskempten.tabulang.tokenizer.TextPosition;

import java.math.BigInteger;

public class NumberItem implements LanguageItem {
    private BigInteger numerator;
    private BigInteger denominator;

    private LanguageItemType itemType = LanguageItemType.ORDINAL_NUMBER;
    private TextPosition myTextPosition;

    public NumberItem(String myNumber) {
        this.setValueFromString(myNumber);
    }

    private void setValueFromString(String v) {

        if (v.contains(".")) {
            Boolean isNegative = v.charAt(0) == '-';
            String[] parts = v.split("\\.");
            BigInteger whole = new BigInteger(parts[0]);
            BigInteger decimals = new BigInteger(parts[1]);
            BigInteger numberOfDecimals = BigInteger.valueOf((long) Math.pow(10, parts[1].length()));
            BigInteger divider = euclid(decimals, numberOfDecimals);
            denominator = numberOfDecimals.divide(divider);
            numerator = decimals.divide(divider).add(whole.abs().multiply(denominator));
            if (isNegative) {
                numerator = numerator.negate();
            }
        } else {
            numerator = new BigInteger(v);
            denominator = BigInteger.valueOf(1);
        }

    }

    private BigInteger euclid(BigInteger a, BigInteger b) {
        if (b.equals(BigInteger.ZERO)) return a.abs();
        else return euclid(b, a.divideAndRemainder(b)[1]);
    }

    public BigInteger getNumerator() {
        return numerator;
    }

    public void setNumerator(BigInteger numerator) {
        this.numerator = numerator;
    }

    public BigInteger getDenominator() {
        return denominator;
    }

    public void setDenominator(BigInteger denominator) {
        this.denominator = denominator;
    }

    @Override
    public TextPosition getTextPosition() {
        return myTextPosition;
    }

    @Override
    public void setTextPosition(TextPosition textPosition) {
        this.myTextPosition = textPosition;
    }

    @Override
    public LanguageItemType getLanguageItemType() {
        return itemType;
    }
}
