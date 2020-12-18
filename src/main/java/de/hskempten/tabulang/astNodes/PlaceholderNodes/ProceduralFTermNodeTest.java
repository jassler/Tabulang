package de.hskempten.tabulang.astNodes.PlaceholderNodes;

import de.hskempten.tabulang.astNodes.IdentifierNode;
import de.hskempten.tabulang.astNodes.StatementNode;
import de.hskempten.tabulang.astNodes.TermNode;
import de.hskempten.tabulang.interpretTest.Interpretation;
import de.hskempten.tabulang.items.ast.interfaces.TermAST;
import de.hskempten.tabulang.items.ast.nodes.IdentifierAST;

import java.util.ArrayList;

public class ProceduralFTermNodeTest extends StatementNode {
    private IdentifierNode identifier;
    private ArrayList<IdentifierNode> identifierList;
    private TermNode term;

    public ProceduralFTermNodeTest(IdentifierNode identifier, ArrayList<IdentifierNode> identifierList, TermNode term) {
        this.setIdentifier(identifier);
        this.setIdentifierList(identifierList);
        this.setTerm(term);
    }

    public IdentifierNode getIdentifier() {
        return identifier;
    }

    public void setIdentifier(IdentifierNode identifier) {
        this.identifier = identifier;
    }

    public ArrayList<IdentifierNode> getIdentifierList() {
        return identifierList;
    }

    public void setIdentifierList(ArrayList<IdentifierNode> identifierList) {
        this.identifierList = identifierList;
    }

    public void setTerm(TermNode term) {
        this.term = term;
    }

    public TermNode getTerm() {
        return term;
    }

    @Override
    public Object evaluateNode(Interpretation interpretation) {
        return null;
    }
}
