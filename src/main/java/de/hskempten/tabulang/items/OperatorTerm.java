package de.hskempten.tabulang.items;

public class OperatorTerm implements AnyTermAST {
    private AnyTermAST left;
    private OperatorItem operator;
    private AnyTermAST right;

    public OperatorTerm(AnyTermAST left, OperatorItem operator, AnyTermAST right) {
        this.setLeft(left);
        this.setOperator(operator);
        this.setRight(right);
    }

    public AnyTermAST getLeft() {
        return left;
    }

    public void setLeft(AnyTermAST left) {
        this.left = left;
    }

    public OperatorItem getOperator() {
        return operator;
    }

    public void setOperator(OperatorItem operator) {
        this.operator = operator;
    }

    public AnyTermAST getRight() {
        return right;
    }

    public void setRight(AnyTermAST right) {
        this.right = right;
    }

    @Override
    public TermTypeAST getTermTypeAST() {
        return TermTypeAST.OPERATOR;
    }
}
