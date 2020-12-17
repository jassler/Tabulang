package de.hskempten.tabulang.items.ast.nodes;

import de.hskempten.tabulang.items.ast.interfaces.TermAST;

public class CountAST implements TermAST {
    private Dir direction;
    private TermAST term;

    public CountAST(Dir direction, TermAST term) {
        this.setDirection(direction);
        this.setTerm(term);
    }

    public Dir getDirection() {
        return direction;
    }

    public void setDirection(Dir direction) {
        this.direction = direction;
    }

    public TermAST getTerm() {
        return term;
    }

    public void setTerm(TermAST term) {
        this.term = term;
    }

    public enum Dir {
        HORIZONTAL, VERTICAL, NONE
    }

    public void print(int offset) {
        String gOffset = " ".repeat(offset);
        System.out.println(gOffset + this.getClass().getSimpleName() + " " + this.getDirection());
        this.getTerm().print((gOffset + " ".repeat((this.getClass().getSimpleName()).length()) + " ").length());
    }
}
