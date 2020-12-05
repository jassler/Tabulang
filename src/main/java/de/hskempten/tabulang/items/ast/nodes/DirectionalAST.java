package de.hskempten.tabulang.items.ast.nodes;

import de.hskempten.tabulang.items.ast.interfaces.TermAST;

public class DirectionalAST implements TermAST {
    Dir direction;
    TermAST term;

    public DirectionalAST(Dir direction, TermAST term) {
        this.direction = direction;
        this.term = term;
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
        HORIZONTAL, VERTICAL
    }

    public void print(int offset) {
        String gOffset = " ".repeat(offset);
        System.out.println(gOffset + this.getClass().getSimpleName() + " " + this.getDirection());
        this.getTerm().print(offset + this.getClass().getSimpleName().length() + 1);
    }
}
