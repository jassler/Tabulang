package de.hskempten.tabulang.astNodes.tableOperations;

import de.hskempten.tabulang.astNodes.term.BinaryTermNode;
import de.hskempten.tabulang.astNodes.Node;
import de.hskempten.tabulang.datatypes.Table;
import de.hskempten.tabulang.Interpretation;
import de.hskempten.tabulang.tokenizer.TextPosition;

public class UniteNode extends BinaryTermNode {
    public UniteNode(Node leftNode, Node rightNode, TextPosition textPosition) {
        super(leftNode, rightNode, textPosition);
    }

    @Override
    public Object evaluateNode(Interpretation interpretation) {
        Table<?> leftTable = verifyAndReturnTable(getLeftNode(), interpretation);
        Table rightTable = verifyAndReturnTable(getRightNode(), interpretation);
        return leftTable.union(rightTable);
    }

    @Override
    public String toString() {
        return getLeftNode() + " unite " + getRightNode();
    }
}