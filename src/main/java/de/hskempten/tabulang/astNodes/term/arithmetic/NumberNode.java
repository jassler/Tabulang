package de.hskempten.tabulang.astNodes.term.arithmetic;


import de.hskempten.tabulang.astNodes.term.TermNode;
import de.hskempten.tabulang.datatypes.InternalNumber;
import de.hskempten.tabulang.Interpretation;
import de.hskempten.tabulang.tokenizer.TextPosition;

import java.math.BigInteger;

public class NumberNode extends TermNode {
    private BigInteger numerator;
    private BigInteger denominator;

    public NumberNode(BigInteger numerator, BigInteger denominator, TextPosition textPosition) {
        super(textPosition);
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

    public Object getValue() {
        if (getFloatValue() % 1 == 0) {
            return (int) getFloatValue();
        } else {
            return getFloatValue();
        }
    }

    @Override
    public Object evaluateNode(Interpretation interpretation) {
        return new InternalNumber(numerator, denominator);
    }

    @Override
    public String toString() {
        return getValue().toString();
    }
}
