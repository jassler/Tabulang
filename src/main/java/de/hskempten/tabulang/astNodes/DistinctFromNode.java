package de.hskempten.tabulang.astNodes;

import de.hskempten.tabulang.datatypes.Table;
import de.hskempten.tabulang.datatypes.exceptions.IllegalOperandArgumentException;
import de.hskempten.tabulang.datatypes.exceptions.TupleCannotBeTransformedException;
import de.hskempten.tabulang.interpretTest.Interpretation;

import java.util.ArrayList;

public class DistinctFromNode extends TermNode{
    private TermNode node;
    private IdentifierNode[] names;

    public DistinctFromNode(TermNode node, ArrayList<IdentifierNode> names) {
        this.node = node;
        this.names = (IdentifierNode[]) names.toArray();
    }

    public Node getNode() {
        return node;
    }

    public void setNode(TermNode node) {
        this.node = node;
    }

    public IdentifierNode[] getNames() {
        return names;
    }

    public void setNames(IdentifierNode[] names) {
        this.names = names;
    }

    @Override
    public Object evaluateNode(Interpretation interpretation) {
        Object object = node.evaluateNode(interpretation);
        try {
            object = ifTupleTransform(object);
        } catch (TupleCannotBeTransformedException transformedException){
            throw new IllegalOperandArgumentException("Got " + object + " (" + object.getClass() + ") on the right side of the 'distinct [....] from' keyword." +
                    "Allowed operand on the right side: Table.");
        }
        if (object instanceof Table) {
            ArrayList<String> columnNames = new ArrayList<>();
            for (TermNode t : names) {
                columnNames.add((String) t.evaluateNode(interpretation));
            }
            return ((Table) object).projection((String[]) columnNames.toArray());
        } else {
            throw new IllegalArgumentException("Expected Table but got " + object.getClass().getSimpleName());
        }
    }
}
