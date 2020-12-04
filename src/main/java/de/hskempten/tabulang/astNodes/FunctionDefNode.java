package de.hskempten.tabulang.astNodes;

import de.hskempten.tabulang.interpretTest.Interpretation;

import java.util.ArrayList;

public class FunctionDefNode extends Node{
    ArrayList<String> identifiers;
    ArrayList<Node> nodes;

    public FunctionDefNode(ArrayList<String> identifiers, ArrayList<Node> nodes) {
        super(NodeType.NODE);
        this.identifiers = identifiers;
        this.nodes = nodes;
    }

    public ArrayList<String> getIdentifiers() {
        return identifiers;
    }

    public void setIdentifiers(ArrayList<String> identifiers) {
        this.identifiers = identifiers;
    }

    public ArrayList<Node> getNodes() {
        return nodes;
    }

    public void setNodes(ArrayList<Node> nodes) {
        this.nodes = nodes;
    }

    @Override
    public Object evaluateNode(Interpretation interpretation) {
        return null;
    }
}
