package de.hskempten.tabulang.astNodes;


import de.hskempten.tabulang.datatypes.*;
import de.hskempten.tabulang.datatypes.exceptions.IllegalBooleanArgumentException;
import de.hskempten.tabulang.datatypes.exceptions.IllegalNumberArgumentException;
import de.hskempten.tabulang.datatypes.exceptions.IllegalStringArgumentException;
import de.hskempten.tabulang.datatypes.exceptions.IllegalTableArgumentException;
import de.hskempten.tabulang.interpreter.Interpretation;
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

    /**
     * Provides a method to check if a node evaluates to a Table object.
     *
     * @return evaluated Table object.
     * @throws IllegalTableArgumentException if node does not evaluate to a Table object.
     */
    public Table<?> verifyAndReturnTable(Interpretation interpretation) {
        Object evaluated = this.ifTupleTransform(interpretation);
        if (!(evaluated instanceof Table<?> table)) {
            throw new IllegalTableArgumentException(this.getTextPosition(), evaluated.getClass().getSimpleName(), this.getTextPosition().getContent(), evaluated + " (" + evaluated.getClass() + ") is not a table.");
        }
        return table;
    }

    /**
     * Provides a method to check if a node evaluates to a Tuple that can be interpreted as a Table.
     *
     * @return Table object if the evaluated object could be transformed into a Table, else returns the evaluated object.
     */
    public Object ifTupleTransform(Interpretation interpretation) {
        Object evaluated = this.evaluateNode(interpretation);
        if (evaluated instanceof Tuple<?> tuple) {
            evaluated = tuple.transformIntoTableIfPossible();
        }
        return evaluated;
    }

    /**
     * Provides a method to check if a node evaluates to an InternalNumber object.
     *
     * @return evaluated InternalNumber object.
     * @throws IllegalNumberArgumentException if node does not evaluate to an InternalNumber object.
     */
    public InternalNumber verifyAndReturnNumber(Interpretation interpretation) {
        Object evaluated = this.evaluateNode(interpretation);
        if (!(evaluated instanceof InternalNumber internalNumber)) {
            throw new IllegalNumberArgumentException(getTextPosition(), evaluated.getClass().getSimpleName(), this.getTextPosition().getContent());
        }
        return internalNumber;
    }

    /**
     * Provides a method to check if a node evaluates to an InternalBoolean object.
     *
     * @return evaluated InternalBoolean object.
     * @throws IllegalBooleanArgumentException if node does not evaluate to an InternalBoolean object.
     */
    public InternalBoolean verifyAndReturnBoolean(Interpretation interpretation) {
        Object evaluated = this.evaluateNode(interpretation);
        if (!(evaluated instanceof InternalBoolean internalBoolean)) {
            throw new IllegalBooleanArgumentException(getTextPosition(), evaluated.getClass().getSimpleName(), this.getTextPosition().getContent());
        }
        return internalBoolean;
    }

    /**
     * Provides a method to check if a node evaluates to an InternalString object.
     *
     * @return evaluated InternalString object.
     * @throws IllegalStringArgumentException if node does not evaluate to an InternalString object.
     */
    public InternalString verifyAndReturnString(Interpretation interpretation) {
        Object evaluated = this.evaluateNode(interpretation);
        if (!(evaluated instanceof InternalString internalString)) {
            throw new IllegalStringArgumentException(getTextPosition(), evaluated.getClass().getSimpleName(), this.getTextPosition().getContent());
        }
        return internalString;
    }

    /**
     * Provides a method to check if a node evaluates to a Table or Tuple object.
     *
     * @return evaluated Table or Tuple object.
     * @throws IllegalStringArgumentException if node does not evaluate to a Table or Tuple object.
     */
    public TupleOperation<?> verifyAndReturnTupleOperation(Interpretation interpretation) {
        Object evaluated = this.evaluateNode(interpretation);
        if (!(evaluated instanceof TupleOperation<?> tupleOperation)) {
            throw new IllegalStringArgumentException(getTextPosition(), evaluated.getClass().getSimpleName(), this.getTextPosition().getContent());
        }
        return tupleOperation;
    }
}
