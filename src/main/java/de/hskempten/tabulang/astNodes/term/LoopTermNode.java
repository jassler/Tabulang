package de.hskempten.tabulang.astNodes.term;

import de.hskempten.tabulang.interpreter.Interpretation;
import de.hskempten.tabulang.astNodes.Node;
import de.hskempten.tabulang.astNodes.helper.LoopHelper;
import de.hskempten.tabulang.items.ast.ASTStatementSorter;
import de.hskempten.tabulang.tokenizer.TextPosition;

import java.util.ArrayList;

public class LoopTermNode extends TermNode {
    private IdentifierNode identifier;
    private TermNode term;
    private ArrayList<Node> statements;
    private int nestingLevel;
    private boolean groupStatementFound = false;

    public LoopTermNode(IdentifierNode identifier, TermNode term, ArrayList<Node> statements, int nestingLevel, TextPosition textPosition) {
        super(textPosition);
        this.setIdentifier(identifier);
        this.setTerm(term);
        this.setStatements(statements);
        this.setNestingLevel(nestingLevel);
    }

    public IdentifierNode getIdentifier() {
        return identifier;
    }

    public void setIdentifier(IdentifierNode identifier) {
        this.identifier = identifier;
    }

    public TermNode getTerm() {
        return term;
    }

    public void setTerm(TermNode term) {
        this.term = term;
    }

    public ArrayList<Node> getStatements() {
        return statements;
    }

    public void setStatements(ArrayList<Node> statements) {
        this.statements = ASTStatementSorter.sortStatements(statements);
    }

    public void setNestingLevel(int nestingLevel) {
        this.nestingLevel = nestingLevel;
    }

    /**
     * Loops over a set of statements once for each element in a tuple.
     *
     * @return tuple of mapValue values for each iteration or a rearranged variation thereof if the set of statements contains a group statement.
     * See {@link LoopHelper} for more details.
     */
    @Override
    public Object evaluateNode(Interpretation interpretation) {
        return LoopHelper.loop(identifier, term, statements, nestingLevel, groupStatementFound, interpretation, getTextPosition());
    }

    @Override
    public String toString() {
        return "for " + identifier + " in " + term + "{" + statements + "}";
    }
}
