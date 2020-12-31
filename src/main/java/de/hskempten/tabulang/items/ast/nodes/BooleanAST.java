package de.hskempten.tabulang.items.ast.nodes;

import de.hskempten.tabulang.items.ast.interfaces.PredAST;

public class BooleanAST implements PredAST {
    private Boolean bool;

    public BooleanAST(Boolean bool) {
        this.bool = bool;
    }

    public Boolean isBool() {
        return bool;
    }

    public void setBool(Boolean bool) {
        this.bool = bool;
    }
}
