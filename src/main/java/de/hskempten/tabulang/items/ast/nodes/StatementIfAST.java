package de.hskempten.tabulang.items.ast.nodes;

import de.hskempten.tabulang.items.ast.interfaces.PredAST;
import de.hskempten.tabulang.items.ast.interfaces.StatementAST;

public class StatementIfAST implements StatementAST {
    private PredAST pred;
    private StatementAST ifStatement;

    public StatementIfAST(PredAST pred, StatementAST ifStatement) {
        this.setPred(pred);
        this.setIfStatement(ifStatement);
    }

    public PredAST getPred() {
        return pred;
    }

    public void setPred(PredAST pred) {
        this.pred = pred;
    }

    public StatementAST getIfStatement() {
        return ifStatement;
    }

    public void setIfStatement(StatementAST ifStatement) {
        this.ifStatement = ifStatement;
    }

    public void print(int offset) {
        String gOffset = " ".repeat(offset);
        System.out.println(gOffset + this.getClass().getSimpleName());
        this.getPred().print((gOffset + " ".repeat((this.getClass().getSimpleName()).length())).length());
        this.getIfStatement().print((gOffset + " ".repeat((this.getClass().getSimpleName()).length())).length());
    }
}
