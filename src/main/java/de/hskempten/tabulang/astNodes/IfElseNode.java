package de.hskempten.tabulang.astNodes;

import de.hskempten.tabulang.interpretTest.Interpretation;

import java.util.HashMap;

public class IfElseNode extends TernaryStatementNode{
    public IfElseNode(Node left, Node middle, Node right) {
        super(left, middle, right);
    }

    @Override
    public Object evaluateNode(Interpretation interpretation) {
        Object left = getLeft().evaluateNode(interpretation);
        HashMap<String, Object> nestedHashmap = new HashMap<>(interpretation.getEnvironment());
        Interpretation nestedInterpretation = new Interpretation(interpretation, nestedHashmap);
        if (left instanceof Boolean) {
            if ((Boolean) left) {
                return getMiddle().evaluateNode(nestedInterpretation);
            } else {
                return getRight().evaluateNode(nestedInterpretation);
            }
        } else {
            throw new IllegalArgumentException("Expected Boolean but got: " + left.getClass().getSimpleName());
        }
    }
}
