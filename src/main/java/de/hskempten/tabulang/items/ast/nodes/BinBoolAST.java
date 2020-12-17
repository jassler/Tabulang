package de.hskempten.tabulang.items.ast.nodes;

import de.hskempten.tabulang.items.ast.interfaces.PredAST;

public class BinBoolAST implements PredAST {
    private BinBool binBool;
    private PredAST left;
    private PredAST right;

    public BinBoolAST(PredAST left, BinBool binBool, PredAST right) {
        this.setLeft(left);
        this.setBinBool(binBool);
        this.setRight(right);
    }

    public BinBool getBinBool() {
        return binBool;
    }

    public void setBinBool(BinBool binBool) {
        this.binBool = binBool;
    }

    public PredAST getLeft() {
        return left;
    }

    public void setLeft(PredAST left) {
        this.left = left;
    }

    public PredAST getRight() {
        return right;
    }

    public void setRight(PredAST right) {
        this.right = right;
    }

    public enum BinBool {
        AND, OR, XOR, IFF, IMPL
    }

    public void print(int offset) {
        String gOffset = " ".repeat(offset);
        System.out.println(gOffset + this.getClass().getSimpleName() + " " + this.getBinBool());
        this.getLeft().print(offset + this.getClass().getSimpleName().length() + 1);
        this.getRight().print(offset + this.getClass().getSimpleName().length() + 1);
    }
}
