package de.hskempten.tabulang.items.ast.nodes;

import de.hskempten.tabulang.items.NumberItem;
import de.hskempten.tabulang.items.ast.interfaces.OrdinalAST;

public class NumberAST implements OrdinalAST {
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
