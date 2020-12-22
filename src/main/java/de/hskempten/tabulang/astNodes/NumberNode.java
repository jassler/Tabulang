package de.hskempten.tabulang.astNodes;


import de.hskempten.tabulang.datatypes.InternalNumber;
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

    @Override
    public Object evaluateNode(Interpretation interpretation) {
        return new InternalNumber(numerator, denominator);
    }

    @Override
    public String toString() {
        return "NumberNode{" +
                "numerator=" + numerator +
                ", denominator=" + denominator +
                "} ";
    }
}
