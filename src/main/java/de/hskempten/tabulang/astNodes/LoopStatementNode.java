package de.hskempten.tabulang.astNodes;

import de.hskempten.tabulang.datatypes.InternalDataObject;
import de.hskempten.tabulang.datatypes.Tuple;
import de.hskempten.tabulang.datatypes.exceptions.IllegalTupleOperandArgumentException;
import de.hskempten.tabulang.interpretTest.Interpretation;
import de.hskempten.tabulang.items.ast.ASTStatementSorter;
import de.hskempten.tabulang.tokenizer.TextPosition;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class LoopStatementNode extends StatementNode {
    private IdentifierNode identifier;
    private TermNode term;
    private ArrayList<Node> statements;
    private int nestingLevel;
    private boolean groupStatementFound = false;

    public LoopStatementNode(IdentifierNode identifier, TermNode term, ArrayList<Node> statements, int nestingLevel, TextPosition textPosition) {
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

    public int getNestingLevel() {
        return nestingLevel;
    }

    public void setNestingLevel(int nestingLevel) {
        this.nestingLevel = nestingLevel;
    }


    @Override
    public Object evaluateNode(Interpretation interpretation) {
        Object term = getTerm().evaluateNode(interpretation);

        if (!(term instanceof Tuple<?> tuple))
            throw new IllegalTupleOperandArgumentException(toString());

        String identifier = getIdentifier().getIdentifier();
        LinkedList<Object> resultList = new LinkedList<>();
        Interpretation nestedInterpretation = new Interpretation(interpretation, new HashMap<>());
        /*
        System.out.println("Loop Nested Interpretation vor erstem Schleifendurchlauf: ");
        Iterator<Map.Entry<String, Object>> it = interpretation.getEnvironment().entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, Object> pair = it.next();
            System.out.println("Key: " + pair.getKey() + " Value: " + pair.getValue());
        }
        System.out.println();
        */
        for (int i = 0; i < tuple.getElements().size(); ++i) {
            Object tupleElement = tuple.getElements().get(i);
            nestedInterpretation.getEnvironment().put(identifier, tupleElement);

            if (tupleElement instanceof Tuple<?> el) {
                for (int j = 0; j < el.getElements().size(); j++) {
                    String type = el.getNames().get(j).getString();
                    Object element = el.getElements().get(j);
                    nestedInterpretation.getEnvironment().put(type, element);
                }
            }
            for (Node statementNode : statements) {
                if (statementNode instanceof GroupNode && !groupStatementFound) {
                    ((GroupNode) statementNode).setNestingLevel(nestingLevel);
                    ((GroupNode) statementNode).setLastIteration(false);
                    groupStatementFound = true;
                }
                if (statementNode instanceof GroupNode && i + 1 == ((Tuple) term).getElements().size()) {
                    ((GroupNode) statementNode).setLastIteration(true);
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
        ArrayList<InternalDataObject> annotatedResults = new ArrayList<>(resultList.size());
        resultList.forEach(el -> annotatedResults.add(new InternalDataObject(el)));
        Tuple<InternalDataObject> result = new Tuple<>(annotatedResults);
        //System.out.println("Loop Result: " + result);
        return result;
    }

    @Override
    public String toString() {
        return "for " + identifier + " in " + term + "{" + statements + "}";
    }
}
