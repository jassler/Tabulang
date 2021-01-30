package de.hskempten.tabulang.astNodes;


import de.hskempten.tabulang.datatypes.Identifier;
import de.hskempten.tabulang.datatypes.InternalNumber;
import de.hskempten.tabulang.datatypes.Table;
import de.hskempten.tabulang.datatypes.Tuple;
import de.hskempten.tabulang.datatypes.exceptions.IllegalNumberOperandArgumentException;
import de.hskempten.tabulang.datatypes.exceptions.IllegalTableOperandArgumentException;
import de.hskempten.tabulang.datatypes.exceptions.VariableNotDeclaredException;
import de.hskempten.tabulang.datatypes.exceptions.VariableNotInitializedException;
import de.hskempten.tabulang.interpretTest.Interpretation;
import de.hskempten.tabulang.tokenizer.TextPosition;

public abstract class Node {
    private TextPosition textPosition;

    public Node(TextPosition textPosition) {
        this.setTextPosition(textPosition);
    }

    public Node() {
    }

    public TextPosition getTextPosition() {
        return textPosition;
    }

    public void setTextPosition(TextPosition textPosition) {
        this.textPosition = textPosition;
    }

    public abstract Object evaluateNode(Interpretation interpretation);

    public Table verifyAndReturnTable(Node node, Interpretation interpretation) {
        Object o = node.evaluateNode(interpretation);
        o = ifTupleTransform(o);
        if (o instanceof Table<?> table) {
            return table;
        } else {
            throw new IllegalTableOperandArgumentException(node.getTextPosition(), o.getClass().getSimpleName(), node.getTextPosition().getContent(), o + " (" + o.getClass() + ") is not a table.");
        }
    }

    public Object ifTupleTransform(Object o) {
        if (o instanceof Tuple<?> tuple) {
            o = tuple.transformIntoTableIfPossible();
        }
        return o;
    }

    public InternalNumber verifyAndReturnNumber(Node node, Interpretation interpretation){
        Object o = node.evaluateNode(interpretation);
        if (!(o instanceof InternalNumber internalNumber)) {
            throw new IllegalNumberOperandArgumentException(getTextPosition(), o.getClass().getSimpleName(), node.getTextPosition().getContent());
        }
        return internalNumber;
    }

}
