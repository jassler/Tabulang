package de.hskempten.tabulang.astNodes;

import de.hskempten.tabulang.datatypes.Tuple;
import de.hskempten.tabulang.interpretTest.Interpretation;
import de.hskempten.tabulang.items.ast.ASTStatementSorter;

import java.util.*;

public class LoopNode extends StatementNode {
    private IdentifierNode identifier;
    private TermNode term;
    private ArrayList<Node> statements;
    private int nestingLevel;
    private boolean groupStatementFound = false;

    public LoopNode(IdentifierNode identifier, TermNode term, ArrayList<Node> statements, int nestingLevel) {
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

    public int getNestingLevel() {
        return nestingLevel;
    }

    public void setNestingLevel(int nestingLevel) {
        this.nestingLevel = nestingLevel;
    }


    @Override
    public Object evaluateNode(Interpretation interpretation) {
        Object term = getTerm().evaluateNode(interpretation);
        if (term instanceof Tuple) {
            String identifier = getIdentifier().getIdentifier();
            LinkedList<Object> resultList = new LinkedList<>();
            Interpretation nestedInterpretation = new Interpretation(interpretation, new HashMap<>());
            System.out.println("Loop Nested Interpretation vor erstem Schleifendurchlauf: ");
            Iterator<Map.Entry<String, Object>> it = interpretation.getEnvironment().entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<String, Object> pair = it.next();
                System.out.println("Key: " + pair.getKey() + " Value: " + pair.getValue());
            }
            System.out.println();
            for (int i = 0; i < ((Tuple) term).getElements().size(); ++i) {
                Object tupleElement = ((Tuple<?>) term).getElements().get(i);
                nestedInterpretation.getEnvironment().put(identifier, tupleElement);
                if (tupleElement instanceof Tuple) {
                    for (int j = 0; j < ((Tuple<?>) tupleElement).getElements().size(); j++) {
                        String type = ((Tuple<?>) tupleElement).getNames().getNames().get(j);
                        Object element = ((Tuple<?>) tupleElement).getElements().get(j);
                        nestedInterpretation.getEnvironment().put(type, element);
                    }
                }
                for (Node statementNode : statements) {
                    if (statementNode instanceof GroupBeforeFunctionCallNode && !groupStatementFound) {
                        ((GroupBeforeFunctionCallNode) statementNode).setNestingLevel(nestingLevel);
                        ((GroupBeforeFunctionCallNode) statementNode).setLastIteration(false);
                        groupStatementFound = true;
                    }
                    if (statementNode instanceof GroupBeforeFunctionCallNode && i + 1 == ((Tuple) term).getElements().size()) {
                        ((GroupBeforeFunctionCallNode) statementNode).setLastIteration(true);
                        resultList = (LinkedList<Object>) statementNode.evaluateNode(nestedInterpretation);
                    } else {
                        statementNode.evaluateNode(nestedInterpretation);
                    }
                }
                if (!groupStatementFound) {
                    if (nestedInterpretation.getEnvironment().containsKey("mapValue" + nestingLevel)) {
                        resultList.add(nestedInterpretation.getEnvironment().get("mapValue" + nestingLevel));
                    }
                }
            }
            Tuple result = new Tuple<>(resultList);
            System.out.println("Loop Result: " + result);
            return result;
        } else {
            throw new IllegalArgumentException("Expected Tuple but got " + term.getClass().getSimpleName());
        }
    }
}
