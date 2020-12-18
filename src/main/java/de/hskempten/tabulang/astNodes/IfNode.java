package de.hskempten.tabulang.astNodes;

import de.hskempten.tabulang.interpretTest.Interpretation;

import java.util.HashMap;

public class IfNode extends BinaryStatementNode{
    public IfNode(Node leftNode, Node rightNode) {
        super(leftNode, rightNode);
    }

    @Override
    public Object evaluateNode(Interpretation interpretation) {
        Object left = getLeftNode().evaluateNode(interpretation);
        if(left instanceof Boolean){
            if((Boolean) left) {
                HashMap<String, Object> nestedHashmap = new HashMap<>(interpretation.getEnvironment());
                Interpretation nestedInterpretation = new Interpretation(interpretation, nestedHashmap);
                return getRightNode().evaluateNode(nestedInterpretation);
            } else {
                return false;
            }
        } else {
            throw new IllegalArgumentException("Expected Boolean but got: " + left.getClass().getSimpleName());
        }
    }
}
