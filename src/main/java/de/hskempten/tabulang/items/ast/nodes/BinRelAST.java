package de.hskempten.tabulang.items.ast.nodes;

import de.hskempten.tabulang.items.ast.interfaces.PredAST;
import de.hskempten.tabulang.items.ast.interfaces.TermAST;

public class BinRelAST implements PredAST {
    private BinRelSym binRelSym;
    private TermAST leftTerm;
    private TermAST rightTerm;

    public BinRelAST(TermAST leftTerm, BinRelSym binRelSym, TermAST rightTerm) {
        this.setLeftTerm(leftTerm);
        this.setBinRelSym(binRelSym);
        this.setRightTerm(rightTerm);
    }

    public BinRelSym getBinRelSym() {
        return binRelSym;
    }

    public void setBinRelSym(BinRelSym binRelSym) {
        this.binRelSym = binRelSym;
    }

    public TermAST getLeftTerm() {
        return leftTerm;
    }

    public void setLeftTerm(TermAST leftTerm) {
        this.leftTerm = leftTerm;
    }

    public TermAST getRightTerm() {
        return rightTerm;
    }

    public void setRightTerm(TermAST rightTerm) {
        this.rightTerm = rightTerm;
    }

    public enum BinRelSym {
        EQUAL, LOWER_THAN, GREATER_THAN, LOWER_EQUAL_THAN, GREATER_EQUAL_THAN, NOT_EQUAL
    }

    public void print(int offset) {
        String gOffset = " ".repeat(offset);
        System.out.println(gOffset + this.getClass().getSimpleName() + " " + this.getBinRelSym());
        this.getLeftTerm().print(offset + this.getClass().getSimpleName().length() + 1);
        this.getRightTerm().print(offset + this.getClass().getSimpleName().length() + 1);
    }
}
