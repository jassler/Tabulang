package de.hskempten.tabulang.items.ast.nodes;

import de.hskempten.tabulang.items.ast.interfaces.PredAST;

public class NotAST implements PredAST {
    private PredAST pred;

    public NotAST(PredAST pred) {
        this.setPred(pred);
    }

    public PredAST getPred() {
        return pred;
    }

    public void setPred(PredAST pred) {
        this.pred = pred;
    }

    public void print(int offset) {
        String gOffset = " ".repeat(offset);
        System.out.println(gOffset + this.getClass().getSimpleName());
        this.getPred().print(offset + this.getClass().getSimpleName().length() + 1);
    }
}
