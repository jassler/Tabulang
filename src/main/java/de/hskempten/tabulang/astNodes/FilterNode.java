package de.hskempten.tabulang.astNodes;

import de.hskempten.tabulang.datatypes.Table;
import de.hskempten.tabulang.datatypes.exceptions.IllegalOperandArgumentException;
import de.hskempten.tabulang.datatypes.exceptions.TupleCannotBeTransformedException;
import de.hskempten.tabulang.interpretTest.Interpretation;

import java.util.ArrayList;
import java.util.HashMap;

public class FilterNode<E> extends BinaryTermNode{
    public FilterNode(Node leftNode, Node rightNode) {
        super(leftNode, rightNode);
    }

    @Override
    public Object evaluateNode(Interpretation interpretation) {
        Object object = getLeftNode().evaluateNode(interpretation);
        try {
            object = ifTupleTransform(object);
        } catch (TupleCannotBeTransformedException transformedException){
            throw new IllegalOperandArgumentException("Got " + object + " (" + object.getClass() + ") on the left side of the 'filter' keyword." +
                    "Allowed operand on the left side: Table.");
        }
        //TODO gute tests finden
        if (object instanceof Table) {
            ArrayList<ArrayList<E>> newRows = new ArrayList<>();
            ArrayList<String> colNames = new ArrayList<String>(((Table) object).getColNames().getNames());
            for (Object tuple : ((Table) object).getRows()) {
                HashMap<String, Object> nestedHashmap = new HashMap<>(interpretation.getEnvironment());
                Interpretation nestedInterpretation = new Interpretation(nestedHashmap);
                for (int j = 0; j < ((ArrayList) tuple).size(); j++) {
                    Object element = ((ArrayList) tuple).get(j);
                    Object name = colNames.get(j);
                    nestedInterpretation.getEnvironment().put(name.toString(), element);
                }
                Object result = getRightNode().evaluateNode(nestedInterpretation);
                if (result instanceof Boolean) {
                    if ((Boolean) result) {
                        newRows.add((ArrayList<E>) tuple);
                    }
                } else {
                    throw new IllegalArgumentException("Expected Boolean but got " + result.getClass().getSimpleName());
                }
            }
            return new Table<>(colNames, newRows);
        } else {
            throw new IllegalArgumentException("Expected Table but got " + object.getClass().getSimpleName());
        }
    }
}
