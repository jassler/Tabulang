package de.hskempten.tabulang.items.ast.nodes;

import de.hskempten.tabulang.items.ast.interfaces.StatementAST;
import de.hskempten.tabulang.items.ast.interfaces.TermAST;

public class GroupFunCallAST implements StatementAST {
    private boolean hiding;
    private boolean area;
    private TermAST term;
    private FunCallAST funCall;

    public GroupFunCallAST(boolean hiding, boolean area, TermAST term, FunCallAST funCall) {
        this.setHiding(hiding);
        this.setArea(area);
        this.setTerm(term);
        this.setFunCall(funCall);
    }

    public boolean isHiding() {
        return hiding;
    }

    public void setHiding(boolean hiding) {
        this.hiding = hiding;
    }

    public boolean isArea() {
        return area;
    }

    public void setArea(boolean area) {
        this.area = area;
    }

    @Override
    public TermAST getTerm() {
        return term;
    }

    public void setTerm(TermAST term) {
        this.term = term;
    }

    public FunCallAST getFunCall() {
        return funCall;
    }

    public void setFunCall(FunCallAST funCall) {
        this.funCall = funCall;
    }
}
