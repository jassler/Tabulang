package de.hskempten.tabulang.items.ast.nodes;

import de.hskempten.tabulang.items.ast.interfaces.OrdinalAST;

public class NullAST implements OrdinalAST {

    public NullAST() {
    }

    public void print(int offset) {
        String gOffset = " ".repeat(offset);
        System.out.println(gOffset + "null");
    }
}
