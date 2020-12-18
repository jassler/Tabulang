package de.hskempten.tabulang.items.ast.nodes;

import de.hskempten.tabulang.items.ast.interfaces.OperatorAST;
import de.hskempten.tabulang.items.ast.interfaces.TermAST;

public class AddAST implements OperatorAST {
    private TermAST left;
    private TermAST right;

    public AddAST(TermAST left, TermAST right) {
        this.setLeft(left);
        this.setRight(right);
    }

    public TermAST getLeft() {
        return left;
    }

    public void setLeft(TermAST left) {
        this.left = left;
    }

    public TermAST getRight() {
        return right;
    }

    public void setRight(TermAST right) {
        this.right = right;
    }

}
