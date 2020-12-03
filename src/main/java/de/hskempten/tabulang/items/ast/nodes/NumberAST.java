package de.hskempten.tabulang.items.ast.nodes;

import de.hskempten.tabulang.items.NumberItem;
import de.hskempten.tabulang.items.ast.interfaces.TermAST;

public class NumberAST implements TermAST {
    private NumberItem number;

    public NumberAST(NumberItem item) {
        this.setNumber(item);
    }

    public NumberItem getNumber() {
        return number;
    }

    public void setNumber(NumberItem number) {
        this.number = number;
    }

    public void print(int offset) {
        String gOffset = " ".repeat(offset);
        System.out.println(gOffset + "Number: " + number.getMyNumber());
    }
}
