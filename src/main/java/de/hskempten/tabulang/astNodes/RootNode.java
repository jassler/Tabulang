package de.hskempten.tabulang.astNodes;

import de.hskempten.tabulang.interpretTest.Interpretation;

import java.util.List;

public abstract class RootNode extends Node{
    public abstract Object executeProgram(Interpretation interpretation);
}
