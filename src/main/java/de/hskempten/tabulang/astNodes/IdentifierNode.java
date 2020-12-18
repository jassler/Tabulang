package de.hskempten.tabulang.astNodes;


import de.hskempten.tabulang.datatypes.Identifier;
import de.hskempten.tabulang.interpretTest.Interpretation;

public class IdentifierNode extends TermNode{
    private String identifier;

    public IdentifierNode(String identifier) {
        this.identifier = identifier;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    @Override
    public Object evaluateNode(Interpretation interpretation) {
            return new Identifier(identifier);
    }

    @Override
    public String toString() {
        return "IdentifierNode{" +
                "identifier='" + identifier + '\'' +
                "} ";
    }
}
