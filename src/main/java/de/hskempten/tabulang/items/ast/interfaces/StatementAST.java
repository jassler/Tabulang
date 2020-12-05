package de.hskempten.tabulang.items.ast.interfaces;

public interface StatementAST {
    default TermAST getTerm() throws Exception {
        throw new Exception(this.getClass().getSimpleName() + " has no term");
    }

    default void print(int offset) {
        String gOffset = " ".repeat(offset);
        System.out.println(gOffset + this.getClass().getSimpleName() + " (print not yet implemented)");
        //this.getTerm().print(offset + this.getClass().getSimpleName().length() + 1);
    }
}
