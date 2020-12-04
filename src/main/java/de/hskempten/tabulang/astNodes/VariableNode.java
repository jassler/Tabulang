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
    public Object evaluateNode(Interpretation interpretation) {
        if(interpretation.getEnvironment().containsKey(identifier)){
            return interpretation.getEnvironment().get(identifier);
        } else {
            //TODO evtl unnötig
            interpretation.getEnvironment().put(identifier, null);
            return identifier;
        }
    }

    @Override
    public String toString() {
        return "VariableNode{" +
                "identifier='" + identifier + '\'' +
                "} " + super.toString();
    }
}
