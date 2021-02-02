package de.hskempten.tabulang.astNodes;

import de.hskempten.tabulang.Interpretation;

public abstract class RootNode extends Node {
    public abstract Object executeProgram(Interpretation interpretation);
}
