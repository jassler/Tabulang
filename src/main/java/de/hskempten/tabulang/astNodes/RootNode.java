package de.hskempten.tabulang.astNodes;

import de.hskempten.tabulang.interpretTest.Interpretation;

public abstract class RootNode {
    public abstract void executeProgram(Interpretation interpretation);
}
