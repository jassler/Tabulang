package de.hskempten.tabulang.astNodes;


import de.hskempten.tabulang.datatypes.Tuple;
import de.hskempten.tabulang.interpretTest.Interpretation;

public class TupleElementNode extends BinaryNode{


    public TupleElementNode(Node leftNode, Node rightNode) {
        super(leftNode, rightNode);
    }

    @Override
    public Object evaluateNode(Interpretation interpretation) {
        //Falls links etwas Anderes als ein Tuple stehen kann
        /*if(getLeftNode().getNodeType() == NodeType.TUPLE){
            return ((TupleNode) getLeftNode()).getTuple().get((String) getRightNode().evaluateNode(i));
        }
        return null;*/
        return ((Tuple) getLeftNode().evaluateNode(interpretation)).get((String) getRightNode().evaluateNode(interpretation));
    }

    @Override
    public String toString() {
        return "TupleElementNode{} " + super.toString();
    }
}
