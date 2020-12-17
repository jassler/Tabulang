package de.hskempten.tabulang.items.ast.nodes;

import de.hskempten.tabulang.items.ast.interfaces.OrdinalAST;

import java.math.BigInteger;

public class NumberAST implements OrdinalAST {
    private BigInteger numerator;
    private BigInteger denominator;

    public NumberAST(BigInteger numerator, BigInteger denominator) {
        this.setNumerator(numerator);
        this.setDenominator(denominator);
    }

    public NumberAST(float floatValue) {
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

    public NumberAST add(NumberAST other) {
        BigInteger kgv = kgv(denominator, other.getDenominator());
        BigInteger newNumerator = (numerator.multiply((kgv.divide(denominator)))).add(other.numerator.multiply((kgv.divide(other.getDenominator()))));
        BigInteger newDenominator = kgv;

        BigInteger divider = euclid(newNumerator, newDenominator);
        newNumerator = newNumerator.divide(divider);
        newDenominator = newDenominator.divide(divider);

        return new NumberAST(newNumerator, newDenominator);
    }

    public NumberAST subtract(NumberAST other) {
        BigInteger kgv = kgv(denominator, other.getDenominator());
        BigInteger newNumerator = (numerator.multiply((kgv.divide(denominator)))).subtract(other.numerator.multiply((kgv.divide(other.getDenominator()))));
        BigInteger newDenominator = kgv;

        BigInteger divider = euclid(newNumerator, newDenominator);
        newNumerator = newNumerator.divide(divider);
        newDenominator = newDenominator.divide(divider);

        return new NumberAST(newNumerator, newDenominator);
    }

    public NumberAST multiply(NumberAST other) {
        BigInteger newNumerator = numerator.multiply(other.numerator);
        BigInteger newDenominator = denominator.multiply(other.getDenominator());

        BigInteger divider = euclid(newNumerator, newDenominator);
        newNumerator = newNumerator.divide(divider);
        newDenominator = newDenominator.divide(divider);

        return new NumberAST(newNumerator, newDenominator);
    }

    public NumberAST divide(NumberAST other) {

        BigInteger newNumerator = other.numerator.compareTo(BigInteger.ZERO) < 0 ? other.getDenominator().negate() : other.getDenominator();
        BigInteger newDenominator = other.numerator.abs();

        return multiply(
                new NumberAST(other.numerator.compareTo(BigInteger.ZERO) < 0 ? other.getDenominator().negate() : other.getDenominator(),
                        other.numerator.abs()
                ));
    }

    public void print(int offset) {
        String gOffset = " ".repeat(offset);
        System.out.println(gOffset + "Number: " + this.getFloatValue());
    }
}
