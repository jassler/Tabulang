package de.hskempten.tabulang.astNodes;


import de.hskempten.tabulang.interpretTest.Interpretation;

import java.math.BigDecimal;
import java.math.BigInteger;

public class NumberNode extends TermNode{
    private BigInteger numerator;
    private BigInteger denominator;

    public NumberNode(BigInteger numerator, BigInteger denominator) {
        this.setNumerator(numerator);
        this.setDenominator(denominator);
    }

    public NumberNode(float floatValue) {
        this.setFloatValue(floatValue);
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

    public float getFloatValue() {
        return numerator.floatValue() / denominator.floatValue();
    }

    private void setFloatValue(float value) {
        String v = Float.toString(value);

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


    private BigInteger kgv(BigInteger a, BigInteger b) {
        if (a.equals(BigInteger.ZERO) || b.equals(BigInteger.ZERO)) return BigInteger.ONE;
        return a.multiply(b).divide(euclid(a, b));
    }

    public NumberNode add(NumberNode other) {
        BigInteger kgv = kgv(denominator, other.getDenominator());
        BigInteger newNumerator = (numerator.multiply((kgv.divide(denominator)))).add(other.numerator.multiply((kgv.divide(other.getDenominator()))));
        BigInteger newDenominator = kgv;

        BigInteger divider = euclid(newNumerator, newDenominator);
        newNumerator = newNumerator.divide(divider);
        newDenominator = newDenominator.divide(divider);

        return new NumberNode(newNumerator, newDenominator);
    }

    public NumberNode subtract(NumberNode other) {
        BigInteger kgv = kgv(denominator, other.getDenominator());
        BigInteger newNumerator = (numerator.multiply((kgv.divide(denominator)))).subtract(other.numerator.multiply((kgv.divide(other.getDenominator()))));
        BigInteger newDenominator = kgv;

        BigInteger divider = euclid(newNumerator, newDenominator);
        newNumerator = newNumerator.divide(divider);
        newDenominator = newDenominator.divide(divider);

        return new NumberNode(newNumerator, newDenominator);
    }

    public NumberNode multiply(NumberNode other) {
        BigInteger newNumerator = numerator.multiply(other.numerator);
        BigInteger newDenominator = denominator.multiply(other.getDenominator());

        BigInteger divider = euclid(newNumerator, newDenominator);
        newNumerator = newNumerator.divide(divider);
        newDenominator = newDenominator.divide(divider);

        return new NumberNode(newNumerator, newDenominator);
    }

    public NumberNode divide(NumberNode other) {

        BigInteger newNumerator = other.numerator.compareTo(BigInteger.ZERO) < 0 ? other.getDenominator().negate() : other.getDenominator();
        BigInteger newDenominator = other.numerator.abs();

        return multiply(
                new NumberNode(other.numerator.compareTo(BigInteger.ZERO) < 0 ? other.getDenominator().negate() : other.getDenominator(),
                        other.numerator.abs()
                ));
    }


    @Override
    public Float evaluateNode(Interpretation interpretation) {
        return getFloatValue();
    }

    @Override
    public String toString() {
        return "NumberNode{" +
                "numerator=" + numerator +
                ", denominator=" + denominator +
                "} " + super.toString();
    }
}
