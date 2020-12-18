package de.hskempten.tabulang.astNodes.PlaceholderNodes;

import de.hskempten.tabulang.astNodes.FunctionCallNode;
import de.hskempten.tabulang.astNodes.StatementNode;
import de.hskempten.tabulang.astNodes.TermNode;
import de.hskempten.tabulang.interpretTest.Interpretation;
import de.hskempten.tabulang.items.ast.interfaces.TermAST;
import de.hskempten.tabulang.items.ast.nodes.FunCallAST;

public class GroupFunctionCallNodeTest extends StatementNode {
    private boolean hiding;
    private boolean area;
    private TermNode term;
    private FunctionCallNode funCall;

    public GroupFunctionCallNodeTest(boolean hiding, boolean area, TermNode term, FunctionCallNode funCall) {
        this.setHiding(hiding);
        this.setArea(area);
        this.setTerm(term);
        this.setFunCall(funCall);
    }

    public boolean isHiding() {
        return hiding;
    }

    public void setHiding(boolean hiding) {
        this.hiding = hiding;
    }

    public boolean isArea() {
        return area;
    }

    public void setArea(boolean area) {
        this.area = area;
    }

    public TermNode getTerm() {
        return term;
    }

    public void setTerm(TermNode term) {
        this.term = term;
    }

    public FunctionCallNode getFunCall() {
        return funCall;
    }

    public void setFunCall(FunctionCallNode funCall) {
        this.funCall = funCall;
    }

    @Override
    public Object evaluateNode(Interpretation interpretation) {
        return "GroupFunctionCallNode has to be implemented";
    }
}
