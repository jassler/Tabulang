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

        return true;
    }

    private BigInteger euclid(BigInteger a, BigInteger b) {
        if (b.equals(BigInteger.ZERO)) return a.abs();
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
        String rest = String.valueOf(divAndRemainder[1].abs().multiply(BigInteger.TEN.pow(k)).divide(denominator));
        String rrr = rest.substring(0, Math.min(rest.length(), k));
        if (rrr.equals("0.0") || rrr.equals("0")) {
            return whole + ".0";
        }
        if(whole.equals("0") && numerator.compareTo(BigInteger.ZERO)<0){
            whole = "-" + whole;
        }
        rrr = padZeros(k, rrr);
        int z = rrr.length() - 1;
        while (rrr.charAt(z) == '0') {
            z--;
        }
        return whole + "." + rrr.substring(0, z + 1);
    }

    public static String padZeros(int num, String str) {
        StringBuilder padding = new StringBuilder();
        for (int i = 0; i < num - str.length(); i++) {
            padding.append("0");
        }
        return padding + str;
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

        BigInteger newNumerator = other.numerator.compareTo(BigInteger.ZERO) < 0 ? other.denominator.negate() : other.denominator;
        BigInteger newDenominator = other.numerator.abs();

        return multiply(
                new Number(other.numerator.compareTo(BigInteger.ZERO) < 0 ? other.denominator.negate() : other.denominator,
                        other.numerator.abs(),
                        other.getToken()
                ));
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
