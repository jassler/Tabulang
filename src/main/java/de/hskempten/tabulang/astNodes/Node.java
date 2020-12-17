package de.hskempten.tabulang.astNodes;


import de.hskempten.tabulang.interpretTest.Interpretation;

public abstract class Node {
    public abstract Object evaluateNode(Interpretation interpretation);

}
