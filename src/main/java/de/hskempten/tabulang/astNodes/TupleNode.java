package de.hskempten.tabulang.astNodes;

import de.hskempten.tabulang.datatypes.Tuple;
import de.hskempten.tabulang.interpretTest.Interpretation;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

public class TupleNode extends TermNode{
    private ArrayList<TermNode> tList;

    public TupleNode(ArrayList tList) {
        this.tList = tList;
    }

    public TupleNode() {
    }

    public TupleNode(TermNode oneTerm) {
        this.tList = new ArrayList<TermNode>();
        this.tList.add(oneTerm);
    }
    public ArrayList getTuple() {
        return tList;
    }

    public void setTuple(ArrayList tList) {
        this.tList= tList;
    }

    @Override
    public Object evaluateNode(Interpretation interpretation) {
        ArrayList<Object> tupleElements = new ArrayList<>();
        for(int j = 0; j < tList.size(); j++){
            tupleElements.add(tList.get(j).evaluateNode(interpretation));
        }
        Tuple tuple = new Tuple(tupleElements);
        return tuple;
    }

    @Override
    public String toString() {
        return "TupleNode{" +
                "tList=" + tList +
                "} " + super.toString();
    }
}
