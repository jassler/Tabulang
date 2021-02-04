package de.hskempten.tabulang.astNodes.term.arithmetic;


import de.hskempten.tabulang.interpreter.Interpretation;
import de.hskempten.tabulang.astNodes.term.TermNode;
import de.hskempten.tabulang.datatypes.InternalNumber;
import de.hskempten.tabulang.tokenizer.TextPosition;

public class PowerNode extends BinaryArithmeticNode {
    public PowerNode(TermNode leftNode, TermNode rightNode, TextPosition textPosition) {
        super(leftNode, rightNode, textPosition);
    }

    /**
     * Raises the value of the first evaluated node to the power of the value of the second evaluated node.
     *
     * @return the result of the first value raised to the power of the second value.
     */
    @Override
    public Object evaluateNode(Interpretation interpretation) {
        InternalNumber leftNumber = getLeftNode().verifyAndReturnNumber(interpretation);
        InternalNumber rightNumber = getRightNode().verifyAndReturnNumber(interpretation);
        return leftNumber.pow(rightNumber);
    }

    @Override
    public String toString() {
        return getLeftNode() + " ^ " + getRightNode();
    }
}
