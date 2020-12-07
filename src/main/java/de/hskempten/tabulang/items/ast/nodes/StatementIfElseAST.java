package de.hskempten.tabulang.items.ast.nodes;

import de.hskempten.tabulang.items.ast.interfaces.PredAST;
import de.hskempten.tabulang.items.ast.interfaces.StatementAST;

public class StatementIfElseAST implements StatementAST {
    private PredAST pred;
    private StatementAST ifStatement;
    private StatementAST elseStatement;

    public StatementIfElseAST(PredAST pred, StatementAST ifStatement, StatementAST elseStatement) {
        this.setPred(pred);
        this.setIfStatement(ifStatement);
        this.setElseStatement(elseStatement);
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

    public StatementAST getElseStatement() {
        return elseStatement;
    }

    public void setElseStatement(StatementAST elseStatement) {
        this.elseStatement = elseStatement;
    }

    public void print(int offset) {
        String gOffset = " ".repeat(offset);
        System.out.println(gOffset + this.getClass().getSimpleName());
        this.getPred().print((gOffset + " ".repeat((this.getClass().getSimpleName()).length())).length());
        this.getIfStatement().print((gOffset + " ".repeat((this.getClass().getSimpleName()).length())).length());
        this.getElseStatement().print((gOffset + " ".repeat((this.getClass().getSimpleName()).length())).length());
    }
}
