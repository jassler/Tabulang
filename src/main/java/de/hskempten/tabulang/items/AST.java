package de.hskempten.tabulang.items;

import de.hskempten.tabulang.interpretTest.Interpretation;

public interface AST {
    public Object evaluate(Interpretation i) throws Exception;
}
