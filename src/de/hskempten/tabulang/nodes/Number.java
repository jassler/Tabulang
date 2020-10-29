package de.hskempten.tabulang.nodes;

import de.hskempten.tabulang.Interpreter;
import de.hskempten.tabulang.TokenType;
import de.hskempten.tabulang.tokenizer.Lexer;
import de.hskempten.tabulang.tokenizer.ParseTimeException;
import de.hskempten.tabulang.tokenizer.Token;

import java.math.BigInteger;

public class Number extends Node {

    private final int STD_NUM_DECIMALS = 25;
    private BigInteger numerator;
    private BigInteger denominator;

    public Number(Lexer lexer) throws ParseTimeException {
        super(lexer.lookahead());

        Token t = lexer.getNextTokenAndExpect(TokenType.NUMBER);
        setValueFromString(t.getContent());
    }

    public Number(String value, Token t) {
        super(t);

        setValueFromString(value);
    }

    private Number(BigInteger _numerator, BigInteger _denominator, Token t) {
        super(t);
        numerator = _numerator;
        denominator = _denominator;
    }

    private boolean setValueFromString(String v) {

        if (v.contains(".")) {
            String[] parts = v.split("\\.");
            BigInteger whole = new BigInteger(parts[0]);
            BigInteger decimals = new BigInteger(parts[1]);
            BigInteger numberOfDecimals = BigInteger.valueOf((long) Math.pow(10, parts[1].length()));
            BigInteger divider = euclid(decimals, numberOfDecimals);
            denominator = numberOfDecimals.divide(divider);
            numerator = decimals.divide(divider).add(whole.multiply(denominator));
        } else {
            numerator = new BigInteger(v);
            denominator = BigInteger.valueOf(1);
        }

        return true;
    }

    private BigInteger euclid(BigInteger a, BigInteger b) {
        if (b.equals(BigInteger.ZERO)) return a;
        else return euclid(b, a.divideAndRemainder(b)[1]);
    }


    private BigInteger kgv(BigInteger a, BigInteger b) {
        if (a.equals(BigInteger.ZERO) || b.equals(BigInteger.ZERO)) return BigInteger.ONE;
        return a.multiply(b).divide(euclid(a, b));
    }


    public String getValueString() {
        return getValueString(STD_NUM_DECIMALS);
    }

    public String getValueString(int k) {
        BigInteger[] divAndRemainder = numerator.divideAndRemainder(denominator);
        String whole = divAndRemainder[0].toString();
        if (k < 1) {
            return whole;
        }
        String rest = String.valueOf(divAndRemainder[1].multiply(BigInteger.TEN.pow(k)).divide(denominator));
        String rrr = rest.substring(0, Math.min(rest.length(), k));
        if (rrr.equals("0.0") || rrr.equals("0")) {
            return whole + ".0";
        }
        int z = rrr.length() - 1;
        while (rrr.charAt(z) == '0') {
            z--;
        }
        return whole + "." + rrr.substring(0, z + 1);
    }


    public float getValue() {
        return Float.parseFloat(getValueString());
    }

    public Number add(Number other) {
        BigInteger kgv = kgv(denominator, other.denominator);
        BigInteger newNumerator = (numerator.multiply((kgv.divide(denominator)))).add(other.numerator.multiply((kgv.divide(other.denominator))));
        BigInteger newDenominator = kgv;

        BigInteger divider = euclid(newNumerator, newDenominator);
        newNumerator = newNumerator.divide(divider);
        newDenominator = newDenominator.divide(divider);

        return new Number(newNumerator, newDenominator, getToken());
    }

    public Number subtract(Number other) {
        BigInteger kgv = kgv(denominator, other.denominator);
        BigInteger newNumerator = (numerator.multiply((kgv.divide(denominator)))).subtract(other.numerator.multiply((kgv.divide(other.denominator))));
        BigInteger newDenominator = kgv;

        BigInteger divider = euclid(newNumerator, newDenominator);
        newNumerator = newNumerator.divide(divider);
        newDenominator = newDenominator.divide(divider);

        return new Number(newNumerator, newDenominator, getToken());
    }

    public Number multiply(Number other) {
        BigInteger newNumerator = numerator.multiply(other.numerator);
        BigInteger newDenominator = denominator.multiply(other.denominator);

        BigInteger divider = euclid(newNumerator, newDenominator);
        newNumerator = newNumerator.divide(divider);
        newDenominator = newDenominator.divide(divider);

        return new Number(newNumerator, newDenominator, getToken());
    }

    public Number divide(Number other) {
        return multiply(new Number(other.denominator, other.numerator, other.getToken()));
    }

    public Number evaluate(Interpreter i) {
        return this;
    }

    @Override
    public String toString() {
        return "Number{" +
                "value=" + getValueString(5) +
                '}';
    }
}
