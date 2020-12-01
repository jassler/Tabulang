package de.hskempten.tabulang.items;

import de.hskempten.tabulang.interpretTest.Interpretation;

public interface VarDefAST extends AST, LanguageItem {
    public VarDefType getType();

    @Override
    default Object evaluate(Interpretation i) throws Exception {
        return null;
    }
}
enum VarDefType{
    ASSIGNMENT, PROCEDURALF
}
