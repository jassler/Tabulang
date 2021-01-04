package de.hskempten.tabulang.astNodes;

import de.hskempten.tabulang.datatypes.InternalDataObject;
import de.hskempten.tabulang.datatypes.Tuple;
import de.hskempten.tabulang.datatypes.exceptions.IllegalTupleOperandArgumentException;
import de.hskempten.tabulang.interpretTest.Interpretation;
import de.hskempten.tabulang.items.ast.ASTStatementSorter;
import de.hskempten.tabulang.tokenizer.TextPosition;

import java.util.*;

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

    public int getNestingLevel() {
        return nestingLevel;
    }

    public void setNestingLevel(int nestingLevel) {
        this.nestingLevel = nestingLevel;
    }


    @Override
    public Object evaluateNode(Interpretation interpretation) {
        Object termObject = getTerm().evaluateNode(interpretation);

        if (!(termObject instanceof Tuple term))
            throw new IllegalTupleOperandArgumentException("Expected Tuple but got " + term.getClass().getSimpleName());

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

        for (int i = 0; i < term.getElements().size(); ++i) {
            Object tupleElementObj = term.getElements().get(i);
            nestedInterpretation.getEnvironment().put(identifier, tupleElementObj);

            if (tupleElementObj instanceof Tuple tupleElement) {
                for (int j = 0; j < tupleElement.getElements().size(); j++) {
                    String type = tupleElement.getNames().getNames().get(j);
                    Object element = tupleElement.getElements().get(j);
                    nestedInterpretation.getEnvironment().put(type, element);
                }
            }

            for (Node statementNode : statements) {
                if (statementNode instanceof GroupBeforeFunctionCallNode && !groupStatementFound) {
                    ((GroupBeforeFunctionCallNode) statementNode).setNestingLevel(nestingLevel);
                    ((GroupBeforeFunctionCallNode) statementNode).setLastIteration(false);
                    groupStatementFound = true;
                }
                if (statementNode instanceof GroupBeforeFunctionCallNode && i + 1 == term.getElements().size()) {
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

        List<InternalDataObject> converted = new ArrayList<>(resultList.size());
        for (var obj : resultList)
            converted.add(new InternalDataObject(obj));
        Tuple<InternalDataObject> result = new Tuple<>(converted);
        System.out.println("Loop Result: " + result);
        return result;
    }

    @Override
    public String toString() {
        return "for " + identifier + " in " + term + "{" + statements + "}";
    }
}
