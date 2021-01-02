package de.hskempten.tabulang.datatypes;

import java.math.BigInteger;
import java.util.Objects;

public class InternalNumber {
    private BigInteger numerator;
    private BigInteger denominator;

    public InternalNumber(BigInteger numerator, BigInteger denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
    }

    public InternalNumber(float value) {
        this.setFloatValue(value);
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

    public Object getValue(){
        if(getFloatValue() % 1 == 0){
            return (int) getFloatValue();
        } else {
            return getFloatValue();
        }
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

    public InternalNumber add(InternalNumber other) {
        BigInteger kgv = kgv(denominator, other.getDenominator());
        BigInteger newNumerator = (numerator.multiply((kgv.divide(denominator)))).add(other.numerator.multiply((kgv.divide(other.getDenominator()))));
        BigInteger newDenominator = kgv;

        BigInteger divider = euclid(newNumerator, newDenominator);
        newNumerator = newNumerator.divide(divider);
        newDenominator = newDenominator.divide(divider);

        return new InternalNumber(newNumerator, newDenominator);
    }

    public InternalNumber subtract(InternalNumber other) {
        BigInteger kgv = kgv(denominator, other.getDenominator());
        BigInteger newNumerator = (numerator.multiply((kgv.divide(denominator)))).subtract(other.numerator.multiply((kgv.divide(other.getDenominator()))));
        BigInteger newDenominator = kgv;

        BigInteger divider = euclid(newNumerator, newDenominator);
        newNumerator = newNumerator.divide(divider);
        newDenominator = newDenominator.divide(divider);

        return new InternalNumber(newNumerator, newDenominator);
    }

    public InternalNumber multiply(InternalNumber other) {
        BigInteger newNumerator = numerator.multiply(other.numerator);
        BigInteger newDenominator = denominator.multiply(other.getDenominator());

        BigInteger divider = euclid(newNumerator, newDenominator);
        newNumerator = newNumerator.divide(divider);
        newDenominator = newDenominator.divide(divider);

        return new InternalNumber(newNumerator, newDenominator);
    }

    public InternalNumber divide(InternalNumber other) {
        return multiply(
                new InternalNumber(other.numerator.compareTo(BigInteger.ZERO) < 0 ? other.getDenominator().negate() : other.getDenominator(),
                        other.numerator.abs()
                ));
    }

    public InternalNumber diff(InternalNumber other) {
        BigInteger kgv = kgv(denominator, other.getDenominator());
        BigInteger newNumerator = (numerator.multiply((kgv.divide(denominator)))).divideAndRemainder(other.numerator.multiply((kgv.divide(other.getDenominator()))))[0];
        BigInteger newDenominator = kgv;

        BigInteger divider = euclid(newNumerator, newDenominator);
        newNumerator = newNumerator.divide(divider);
        newDenominator = newDenominator.divide(divider);

        return new InternalNumber(newNumerator, newDenominator);
    }

    public InternalNumber mod(InternalNumber other) {
        BigInteger kgv = kgv(denominator, other.getDenominator());
        BigInteger newNumerator = (numerator.multiply((kgv.divide(denominator)))).divideAndRemainder(other.numerator.multiply((kgv.divide(other.getDenominator()))))[1];
        BigInteger newDenominator = kgv;

        BigInteger divider = euclid(newNumerator, newDenominator);
        newNumerator = newNumerator.divide(divider);
        newDenominator = newDenominator.divide(divider);

        return new InternalNumber(newNumerator, newDenominator);
    }

    public int compareTo(InternalNumber other) {
        if (denominator.equals(other.getDenominator())) {
            return numerator.compareTo(other.numerator);
        }
        BigInteger kgv = kgv(denominator, other.getDenominator());
        return (numerator.multiply(kgv.divide(denominator))).compareTo(other.getNumerator().multiply(kgv.divide(other.getDenominator())));
    }

    public boolean equals(InternalNumber other) {
        return this.compareTo(other) == 0;
    }

    @Override
    public String toString() {
            return "" + getValue() + "";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof InternalNumber)) return false;
        InternalNumber that = (InternalNumber) o;
        return numerator.equals(that.numerator) && denominator.equals(that.denominator);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numerator, denominator);
    }
}
