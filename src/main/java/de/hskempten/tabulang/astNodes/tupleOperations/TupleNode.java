package de.hskempten.tabulang.astNodes.tupleOperations;

import de.hskempten.tabulang.astNodes.term.TermNode;
import de.hskempten.tabulang.datatypes.Tuple;
import de.hskempten.tabulang.Interpretation;
import de.hskempten.tabulang.tokenizer.TextPosition;

import java.util.ArrayList;

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
        for (TermNode termNode : tList) {
            o = termNode.evaluateNode(interpretation);
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
