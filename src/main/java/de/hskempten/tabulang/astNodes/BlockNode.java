package de.hskempten.tabulang.astNodes;

import de.hskempten.tabulang.interpretTest.Interpretation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BlockNode extends Node{
    private ArrayList<Object> statements;

    public BlockNode(ArrayList<Object> statements) {
        this.statements = statements;
    }

    public ArrayList<Object> getStatements() {
        return statements;
    }

    public void setStatements(ArrayList<Object> statements) {
        this.statements = statements;
    }

    @Override
    public Object evaluateNode(Interpretation interpretation) {
        ArrayList<Object> resultList = new ArrayList<>();
        HashMap<String, Object> nestedHashmap = new HashMap<>();
        Interpretation nestedInterpretation = new Interpretation(interpretation, nestedHashmap);
        for(Object o : statements){
            resultList.add(((Node) o).evaluateNode(nestedInterpretation));
        }
        System.out.println("Nested Environment: ");
        for(Map.Entry entry : nestedInterpretation.getEnvironment().entrySet()){
            System.out.println("Key: " + entry.getKey() + " Value: " + entry.getValue());
        }
        return resultList;
    }
}
