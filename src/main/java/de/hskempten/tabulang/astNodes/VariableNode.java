package de.hskempten.tabulang.astNodes;


import de.hskempten.tabulang.interpretTest.Interpretation;

public class VariableNode extends Node{
    private String identifier;

    public VariableNode(String identifier) {
        super(NodeType.VARIABLE);
        this.identifier = identifier;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    @Override
    public Object evaluateNode(Interpretation i) {
        if(i.getEnvironment().containsKey(identifier)){
            return i.getEnvironment().get(identifier);
        } else {
            return identifier;
        }
    }
}
