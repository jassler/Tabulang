package de.hskempten.tabulang.astNodes;


import de.hskempten.tabulang.interpretTest.Interpretation;

import java.math.BigDecimal;

public class NumberNode extends Node{
    private BigDecimal number;

    public NumberNode(BigDecimal number) {
        super();
        this.number = number;
    }

    public Number getNumber() {
        return number;
    }

    public void setNumber(BigDecimal number) {
        this.number = number;
    }

    @Override
    public BigDecimal evaluateNode(Interpretation interpretation) {
        return number;
    }

    @Override
    public String toString() {
        return "NumberNode{" +
                "number=" + number +
                "} ";
    }
}
