package de.hskempten.tabulang.astNodes;

import de.hskempten.tabulang.datatypes.Identifier;
import de.hskempten.tabulang.datatypes.InternalNumber;
import de.hskempten.tabulang.datatypes.Tuple;
import de.hskempten.tabulang.datatypes.exceptions.VariableNotDeclaredException;
import de.hskempten.tabulang.interpretTest.Interpretation;
import de.hskempten.tabulang.tokenizer.TextPosition;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

public class TupleNode extends TermNode {
    private ArrayList<TermNode> tList;

    public TupleNode(ArrayList tList, TextPosition textPosition) {
        super(textPosition);
        this.tList = tList;
    }

    public TupleNode(TextPosition textPosition) {
        super(textPosition);
    }

    public TupleNode(TermNode oneTerm, TextPosition textPosition) {
        super(textPosition);
        this.tList = new ArrayList<TermNode>();
        this.tList.add(oneTerm);
    }

    public ArrayList getTuple() {
        return tList;
    }

    public void setTuple(ArrayList tList) {
        this.tList = tList;
    }

    @Override
    public Object evaluateNode(Interpretation interpretation) {
        ArrayList<Object> tupleElements = new ArrayList<>();
        Object o;
        for (int j = 0; j < tList.size(); j++) {
            o = tList.get(j).evaluateNode(interpretation);
            tupleElements.add(o);
        }
        Tuple tuple = new Tuple(tupleElements);
        return tuple;
    }

    @Override
    public String toString() {
        return tList.toString();
    }
}
