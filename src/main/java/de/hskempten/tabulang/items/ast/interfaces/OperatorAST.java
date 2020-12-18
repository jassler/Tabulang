package de.hskempten.tabulang.items.ast.interfaces;

public interface OperatorAST extends TermAST {

    public TermAST getLeft();

    public void setLeft(TermAST left);

    public TermAST getRight();

    public void setRight(TermAST right);

    default void print(int offset) {
            String gOffset = " ".repeat(offset);
            System.out.println(gOffset + this.getClass().getSimpleName());
            this.getLeft().print(offset + this.getClass().getSimpleName().length() + 1);
            this.getRight().print(offset + this.getClass().getSimpleName().length() + 1);
    }
}
