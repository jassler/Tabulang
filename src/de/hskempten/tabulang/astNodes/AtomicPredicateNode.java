package de.hskempten.tabulang.astNodes;

public abstract class AtomicPredicateNode extends BinaryNode {
    public AtomicPredicateNode(Node leftNode, Node rightNode) {
        super(leftNode, rightNode);
    }
}
