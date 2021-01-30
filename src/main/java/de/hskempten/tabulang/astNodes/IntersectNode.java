package de.hskempten.tabulang.astNodes;

import de.hskempten.tabulang.datatypes.Table;
import de.hskempten.tabulang.interpretTest.Interpretation;
import de.hskempten.tabulang.tokenizer.TextPosition;

public class IntersectNode extends BinaryTermNode{
    public IntersectNode(Node leftNode, Node rightNode, TextPosition textPosition) {
        super(leftNode, rightNode, textPosition);
    }

    @Override
    public Object evaluateNode(Interpretation interpretation) {
        Table leftTable = verifyAndReturnTable(getLeftNode(), interpretation);
        Table rightTable = verifyAndReturnTable(getRightNode(), interpretation);
        return leftTable.intersection(rightTable);
    }

    @Override
    public String toString() {
        return getLeftNode() + " intersect " + getRightNode();
    }
}
