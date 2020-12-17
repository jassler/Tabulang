package de.hskempten.tabulang.astNodes;


import de.hskempten.tabulang.datatypes.Identifier;
import de.hskempten.tabulang.interpretTest.Interpretation;

public class IdentifierNode extends Node{
    private Identifier identifier;

    public IdentifierNode(Identifier identifier) {
        this.identifier = identifier;
    }

    public Identifier getIdentifier() {
        return identifier;
    }

    public void setIdentifier(Identifier identifier) {
        this.identifier = identifier;
    }

    @Override
    public Object evaluateNode(Interpretation interpretation) {
            return identifier;
    }

    @Override
    public String toString() {
        return "IdentifierNode{" +
                "identifier='" + identifier + '\'' +
                "} ";
    }
}
