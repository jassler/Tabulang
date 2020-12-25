package de.hskempten.tabulang.astNodes;

import de.hskempten.tabulang.astNodes.IdentifierNode;
import de.hskempten.tabulang.astNodes.StatementNode;
import de.hskempten.tabulang.astNodes.TermNode;
import de.hskempten.tabulang.datatypes.Tuple;
import de.hskempten.tabulang.interpretTest.Interpretation;
import de.hskempten.tabulang.items.ast.ASTStatementSorter;
import de.hskempten.tabulang.items.ast.interfaces.StatementAST;
import de.hskempten.tabulang.items.ast.interfaces.TermAST;
import de.hskempten.tabulang.items.ast.nodes.IdentifierAST;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class LoopNode extends StatementNode {
    private IdentifierNode identifier;
    private TermNode term;
    private ArrayList<StatementNode> statements;
    private int nestingLevel;

    public LoopNode(IdentifierNode identifier, TermNode term, ArrayList<StatementNode> statements, int nestingLevel) {
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

    public ArrayList<StatementNode> getStatements() {
        return statements;
    }

    public void setStatements(ArrayList<StatementNode> statements) {
        this.statements = ASTStatementSorter.sortStatements(statements);
    }

    public int getNestingLevel() {
        return nestingLevel;
    }

    public void setNestingLevel(int nestingLevel) {
        this.nestingLevel = nestingLevel;
    }

    //TODO can probably remove this if the method below works
    //@Override
    public Object evaluateNodeS(Interpretation interpretation) {
        Object term = getTerm().evaluateNode(interpretation);
        if(term instanceof Tuple){
            String identifier = getIdentifier().getIdentifier();
            int i;

            System.out.println("Loop Nested Interpretation vor erstem Schleifendurchlauf: ");
            Iterator it = interpretation.getEnvironment().entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry)it.next();
                System.out.println("Key: " + pair.getKey() + " Value: " + pair.getValue());
            }
            System.out.println();

            for(i = 0; i < ((Tuple) term).getElements().size(); i++){
                Object tupleElement = ((Tuple<?>) term).getElements().get(i);
                interpretation.getEnvironment().put(identifier, tupleElement);
                if(tupleElement instanceof Tuple){
                    for(int j = 0; j < ((Tuple<?>) tupleElement).getElements().size(); j++){
                        String type = ((Tuple<?>) tupleElement).getNames().getNames().get(j);
                        Object element = ((Tuple<?>) tupleElement).getElements().get(j);
                        interpretation.getEnvironment().put(type, element);
                    }
                }
                for(StatementNode statementNode : statements){
                    statementNode.evaluateNode(interpretation);
                }
                System.out.print("Loop Nested Interpretation: ");
                System.out.println("Durchlauf: " + i);
                Iterator ite = interpretation.getEnvironment().entrySet().iterator();
                while (ite.hasNext()) {
                    Map.Entry pair = (Map.Entry)ite.next();
                    System.out.println("Key: " + pair.getKey() + " Value: " + pair.getValue());
                }
                System.out.println();
            }
            //TODO this could be dangerous
            interpretation.getEnvironment().remove("mapValue" + nestingLevel);
            interpretation.getEnvironment().remove(identifier);
            //TODO appropriate return value or change to void
            return null;
        } else {
            throw new IllegalArgumentException("Expected Tuple but got " + term.getClass().getSimpleName());
        }
    }

    @Override
    //TODO test both; this one uses own environments -> needs more space, but definitely no collisions when calling environment.remove(identifier)
    public Object evaluateNode(Interpretation interpretation) {
        Object term = getTerm().evaluateNode(interpretation);
        if(term instanceof Tuple){
            String identifier = getIdentifier().getIdentifier();
            int i;
            System.out.println("Loop Nested Interpretation vor erstem Schleifendurchlauf: ");
            Iterator it = interpretation.getEnvironment().entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry)it.next();
                System.out.println("Key: " + pair.getKey() + " Value: " + pair.getValue());
            }
            System.out.println();

            for(i = 0; i < ((Tuple) term).getElements().size(); i++){
                HashMap<String, Object> nestedHashmap = new HashMap<>();
                Interpretation nestedInterpretation = new Interpretation(interpretation, nestedHashmap);
                Object tupleElement = ((Tuple<?>) term).getElements().get(i);
                nestedInterpretation.getEnvironment().put(identifier, tupleElement);
                if(tupleElement instanceof Tuple){
                    for(int j = 0; j < ((Tuple<?>) tupleElement).getElements().size(); j++){
                        String type = ((Tuple<?>) tupleElement).getNames().getNames().get(j);
                        Object element = ((Tuple<?>) tupleElement).getElements().get(j);
                        nestedInterpretation.getEnvironment().put(type, element);
                    }
                }
                for(StatementNode statementNode : statements){
                    statementNode.evaluateNode(nestedInterpretation);
                }
                System.out.print("Loop Nested Interpretation: ");
                System.out.println("Durchlauf: " + i);
                Iterator ite = nestedInterpretation.getEnvironment().entrySet().iterator();
                while (ite.hasNext()) {
                    Map.Entry pair = (Map.Entry)ite.next();
                    System.out.println("Key: " + pair.getKey() + " Value: " + pair.getValue());
                }
                System.out.println();
            }
            //TODO appropriate return value
            return null;
        } else {
            throw new IllegalArgumentException("Expected Tuple but got " + term.getClass().getSimpleName());
        }
    }
}
