package de.hskempten.tabulang.astNodes;

import de.hskempten.tabulang.datatypes.Identifier;
import de.hskempten.tabulang.interpretTest.Interpretation;

public class SetNode extends Node{
    private Node node;
    private int mapVal;

    public SetNode(Node node, int mapVal) {
        this.node = node;
        this.mapVal = mapVal;
    }

    public Node getNode() {
        return node;
    }

    public void setNode(Node node) {
        this.node = node;
    }

    public int getMapVal() {
        return mapVal;
    }

    public void setMapVal(int mapVal) {
        this.mapVal = mapVal;
    }

    @Override
    public Object evaluateNode(Interpretation interpretation) {
        Object o = getNode().evaluateNode(interpretation);
        if(o instanceof Identifier){
            o = ((Identifier) o).getIdentifierName();
        }
        interpretation.getEnvironment().put("mapValue" + mapVal, o);
        return o;
    }
}
