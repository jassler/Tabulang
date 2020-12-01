package de.hskempten.tabulang.items;

import de.hskempten.tabulang.interpretTest.Interpretation;

public interface AssignmentAST extends VarDefAST {

    default VarDefType getType() {
        return VarDefType.ASSIGNMENT;
    }

    default Object evaluate(Interpretation i) throws Exception {
        // TODO not yet implemented
        throw new Exception("not yet implemented");
    }
}
