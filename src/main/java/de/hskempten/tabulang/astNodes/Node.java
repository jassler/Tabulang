package de.hskempten.tabulang.astNodes;


import de.hskempten.tabulang.datatypes.Identifier;
import de.hskempten.tabulang.datatypes.Table;
import de.hskempten.tabulang.datatypes.Tuple;
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

    public Table checkIfTable(Object o) {
        o = ifTupleTransform(o);
        if (o instanceof Table) {
            return (Table) o;
        } else {
            throw new IllegalArgumentException(o + " (" + o.getClass() + ") is not a table.");
        }
    }

    public Object ifTupleTransform(Object o) {
        if (o instanceof Tuple) {
            o = ((Tuple<?>) o).transformIntoTableIfPossible();
        }
        return o;
    }

}
