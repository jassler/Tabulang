package de.hskempten.tabulang.astNodes;

import de.hskempten.tabulang.datatypes.Table;
import de.hskempten.tabulang.interpretTest.Interpretation;

import java.util.ArrayList;
import java.util.HashMap;

public class FilterNode<E> extends BinaryNode{
    public FilterNode(Node leftNode, Node rightNode) {
        super(leftNode, rightNode);
    }

    @Override
    public Object evaluateNode(Interpretation interpretation) {
        Object o = getLeftNode().evaluateNode(interpretation);
        //TODO gute tests finden

        ArrayList<ArrayList<E>> newRows = new ArrayList<>();
        ArrayList<String> colNames = new ArrayList<String>(((Table) o).getColNames());
        for(Object tuple : ((Table) o).getTuples()){
            HashMap<String, Object> nestedHashmap = new HashMap<>(interpretation.getEnvironment());
            Interpretation nestedInterpretation = new Interpretation(nestedHashmap);
            for(int j = 0; j < ((ArrayList) tuple).size(); j++){
                Object element = ((ArrayList) tuple).get(j);
                Object name = colNames.get(j);
                nestedInterpretation.getEnvironment().put(name.toString(), element);
            }
            Boolean result = (Boolean) getRightNode().evaluateNode(nestedInterpretation);
            if(result){
                newRows.add((ArrayList<E>) tuple);
            }
        };
        return new Table<>(colNames, newRows);
    }
}
