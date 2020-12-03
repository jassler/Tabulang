package de.hskempten.tabulang.items.ast.interfaces;

public interface StatementAST {
    TermAST getTerm();

    default void print(int offset) {
        String gOffset = " ".repeat(offset);
        System.out.println(gOffset + this.getClass().getSimpleName());
        this.getTerm().print(offset + this.getClass().getSimpleName().length() + 1);
    }
}
