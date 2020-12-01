package de.hskempten.tabulang.items;

import de.hskempten.tabulang.interpretTest.Interpretation;

public interface ProceduralFAST extends VarDefAST{

    default VarDefType getType() {
        return VarDefType.PROCEDURALF;
    }

    default Object evaluate(Interpretation i) throws Exception {
        // TODO not yet implemented
        throw new Exception("not yet implemented");
    }
}
