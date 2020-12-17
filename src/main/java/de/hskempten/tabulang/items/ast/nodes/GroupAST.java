package de.hskempten.tabulang.items.ast.nodes;

import de.hskempten.tabulang.items.ast.interfaces.StatementAST;
import de.hskempten.tabulang.items.ast.interfaces.TermAST;

public class GroupAST implements StatementAST {
    private boolean hiding;
    private boolean area;
    private TermAST term;

    public GroupAST(boolean hiding, boolean area, TermAST term) {
        this.setHiding(hiding);
        this.setArea(area);
        this.setTerm(term);
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
}
