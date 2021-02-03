package de.hskempten.tabulang.astNodes.tupleOperations;

import de.hskempten.tabulang.interpreter.Interpretation;
import de.hskempten.tabulang.astNodes.term.TermNode;
import de.hskempten.tabulang.datatypes.Tuple;
import de.hskempten.tabulang.tokenizer.TextPosition;

import java.util.ArrayList;

public class TupleNode extends TermNode {
    private ArrayList<TermNode> tList;

    public TupleNode(ArrayList<TermNode> tList, TextPosition textPosition) {
        super(textPosition);
        this.tList = tList;
    }

    public TupleNode(TextPosition textPosition) {
        super(textPosition);
    }

    public TupleNode(TermNode oneTerm, TextPosition textPosition) {
        super(textPosition);
        this.tList = new ArrayList<>();
        this.tList.add(oneTerm);
    }

    public ArrayList<TermNode> getTuple() {
        return tList;
    }

    public void setTuple(ArrayList<TermNode> tList) {
        this.tList = tList;
    }

    /**
     * Evaluates every node in the specified array and creates a new Tuple with those values.
     *
     * @return new Tuple with evaluated values.
     */
    @Override
    public Object evaluateNode(Interpretation interpretation) {
        ArrayList<Object> tupleElements = new ArrayList<>();
        Object o;
        for (TermNode termNode : tList) {
            o = termNode.evaluateNode(interpretation);
            tupleElements.add(o);
        }
        return new Tuple(tupleElements);
    }

    @Override
    public String toString() {
        return tList.toString();
    }
}
