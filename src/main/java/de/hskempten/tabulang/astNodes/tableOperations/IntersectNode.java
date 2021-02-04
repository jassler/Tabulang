package de.hskempten.tabulang.astNodes.tableOperations;

import de.hskempten.tabulang.interpreter.Interpretation;
import de.hskempten.tabulang.astNodes.Node;
import de.hskempten.tabulang.astNodes.term.BinaryTermNode;
import de.hskempten.tabulang.datatypes.Table;
import de.hskempten.tabulang.tokenizer.TextPosition;

public class IntersectNode extends BinaryTermNode {
    public IntersectNode(Node leftNode, Node rightNode, TextPosition textPosition) {
        super(leftNode, rightNode, textPosition);
    }

    /**
     * Creates a new Table with rows that are part of both specified Tables.
     * See {@link Table#intersection(Table)}
     *
     * @return new Table that contains the rows that are in both Tables.
     */
    @Override
    public Object evaluateNode(Interpretation interpretation) {
        Table<?> leftTable = getLeftNode().verifyAndReturnTable(interpretation);
        Table rightTable = getRightNode().verifyAndReturnTable(interpretation);
        return leftTable.intersection(rightTable);
    }

    @Override
    public String toString() {
        return getLeftNode() + " intersect " + getRightNode();
    }
}
