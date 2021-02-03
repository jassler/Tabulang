package de.hskempten.tabulang.astNodes.statement;

import de.hskempten.tabulang.interpreter.Interpretation;
import de.hskempten.tabulang.astNodes.Node;
import de.hskempten.tabulang.astNodes.term.IdentifierNode;
import de.hskempten.tabulang.datatypes.InternalFunction;
import de.hskempten.tabulang.items.ast.ASTStatementSorter;
import de.hskempten.tabulang.tokenizer.TextPosition;

import java.util.ArrayList;

public class FunctionAssignmentNode extends StatementNode {
    private IdentifierNode identifier;
    private ArrayList<IdentifierNode> identifierList;
    private ArrayList<Node> statements;

    public FunctionAssignmentNode(IdentifierNode identifier, ArrayList<IdentifierNode> identifierList, ArrayList<Node> statements, TextPosition textPosition) {
        super(textPosition);
        this.setIdentifier(identifier);
        this.setIdentifierList(identifierList);
        this.setStatements(statements);
    }

    public IdentifierNode getIdentifier() {
        return identifier;
    }

    public void setIdentifier(IdentifierNode identifier) {
        this.identifier = identifier;
    }

    public void setIdentifierList(ArrayList<IdentifierNode> identifierList) {
        this.identifierList = identifierList;
    }

    public ArrayList<Node> getStatements() {
        return statements;
    }

    public void setStatements(ArrayList<Node> statements) {
        this.statements = ASTStatementSorter.sortStatements(statements);
    }

    /**
     * Assigns specified function to specified identifier name in the interpretation.
     *
     * @return value that got assigned to identifier.
     */
    @Override
    public Object evaluateNode(Interpretation interpretation) {
        String o = identifier.getIdentifier();
        InternalFunction newFunc = new InternalFunction(identifierList, statements);
        interpretation.getEnvironment().put(o, newFunc);
        return newFunc;
    }

    @Override
    public String toString() {
        return "function" + identifier +
                "(" + identifierList + ")"
                + "{" + statements + '}';
    }
}
