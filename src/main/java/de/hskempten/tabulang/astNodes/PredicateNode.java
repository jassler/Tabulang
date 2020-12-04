package de.hskempten.tabulang.astNodes;

public abstract class PredicateNode extends BinaryNode {
    public PredicateNode(Node leftNode, Node rightNode) {
        super(leftNode, rightNode);
    }
}
