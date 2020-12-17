package de.hskempten.tabulang.items.ast.interfaces;

public interface PredAST extends AST{

    default void print(int offset) {
        String gOffset = " ".repeat(offset);
        System.out.println(gOffset + this.getClass().getSimpleName() + " (print not yet implemented)");
    }
}
