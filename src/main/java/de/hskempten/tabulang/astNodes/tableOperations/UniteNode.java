package de.hskempten.tabulang.astNodes.tableOperations;

import de.hskempten.tabulang.interpreter.Interpretation;
import de.hskempten.tabulang.astNodes.Node;
import de.hskempten.tabulang.astNodes.term.BinaryTermNode;
import de.hskempten.tabulang.datatypes.Table;
import de.hskempten.tabulang.tokenizer.TextPosition;

public class UniteNode extends BinaryTermNode {
    public UniteNode(Node leftNode, Node rightNode, TextPosition textPosition) {
        super(leftNode, rightNode, textPosition);
    }

    /**
     * Creates a new Table with rows of both specified Tables.
     * See {@link Table#union(Table)}
     *
     * @return new Table that contains the rows of both Tables.
     */
    @Override
    public Object evaluateNode(Interpretation interpretation) {
        Table<?> leftTable = getLeftNode().verifyAndReturnTable(interpretation);
        Table rightTable = getRightNode().verifyAndReturnTable(interpretation);
        return leftTable.union(rightTable);
    }

    @Override
    public String toString() {
        return getLeftNode() + " unite " + getRightNode();
    }
}
