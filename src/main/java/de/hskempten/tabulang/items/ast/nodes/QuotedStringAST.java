package de.hskempten.tabulang.items.ast.nodes;

import de.hskempten.tabulang.items.ast.interfaces.OrdinalAST;

public class QuotedStringAST implements OrdinalAST {
    private String string;

    public QuotedStringAST(String string) {
        this.string = string;
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }

    public void print(int offset) {
        String gOffset = " ".repeat(offset);
        System.out.println(gOffset + "QuotedString: " + string);
    }
}
