package de.hskempten.tabulang.astNodes.PlaceholderNodes;

import de.hskempten.tabulang.astNodes.FunctionCallNode;
import de.hskempten.tabulang.astNodes.IdentifierNode;
import de.hskempten.tabulang.astNodes.StatementNode;
import de.hskempten.tabulang.astNodes.TermNode;
import de.hskempten.tabulang.datatypes.Tuple;
import de.hskempten.tabulang.interpretTest.Interpretation;
import de.hskempten.tabulang.items.ast.ASTStatementSorter;

import java.util.*;

public class GroupLoopNode extends StatementNode{
    private IdentifierNode identifier;
    private TermNode term;
    private ArrayList<StatementNode> statements;
    private int nestingLevel;
    private TermNode groupTerm;
    private FunctionCallNode funCall;

    public GroupLoopNode(IdentifierNode identifier, TermNode term, ArrayList<StatementNode> statements, int nestingLevel, TermNode groupTerm, FunctionCallNode funCall) {
        this.identifier = identifier;
        this.term = term;
        this.statements = statements;
        this.nestingLevel = nestingLevel;
        this.groupTerm = groupTerm;
        this.funCall = funCall;
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

    public FunctionCallNode getFunCall() {
        return funCall;
    }

    public void setFunCall(FunctionCallNode funCall) {
        this.funCall = funCall;
    }

    @Override
    public Object evaluateNode(Interpretation interpretation) {
        Object term = getTerm().evaluateNode(interpretation);
        if(term instanceof Tuple){
            String identifier = getIdentifier().getIdentifier();
            Object groupTermObject = groupTerm.evaluateNode(interpretation);
            LinkedHashMap<Object, LinkedList<Object>> groups = new LinkedHashMap<>();
            ArrayList<TermNode> parameters = funCall.getParameters();
            ArrayList<Object> loopResult = new ArrayList<>();
            Interpretation nestedInterpretation = new Interpretation(interpretation, new HashMap<>());
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

                for(TermNode parameter : parameters) {
                    if (groups.containsKey(groupTermObject.toString())) {
                        LinkedList<Object> l = interpretation.getGroup().get(groupTermObject.toString());
                        l.add(parameter.evaluateNode(interpretation));
                    } else {
                        LinkedList<Object> l = new LinkedList<>();
                        l.add(parameter.evaluateNode(interpretation));
                        groups.put(groupTermObject.toString(), l);
                    }
                }

                if(nestedInterpretation.getEnvironment().containsKey("mapValue" + nestingLevel)){
                    loopResult.add(nestedInterpretation.getEnvironment().get("mapValue" + nestingLevel));
                }
                System.out.print("Loop Nested Interpretation: ");
                System.out.println("Durchlauf: " + i);
                Iterator ite = nestedInterpretation.getEnvironment().entrySet().iterator();
                while (ite.hasNext()) {
                    Map.Entry pair = (Map.Entry)ite.next();
                    System.out.println("Key: " + pair.getKey() + " Value: " + pair.getValue());
                }
                System.out.println();
                Iterator iter = nestedInterpretation.getGroup().entrySet().iterator();
                System.out.print("GroupList Q: \n");
                while (iter.hasNext()) {
                    Map.Entry pair = (Map.Entry)iter.next();
                    System.out.println("Key: " + pair.getKey() + " Value: " + pair.getValue());
                }
                System.out.println();
                Iterator itera = groups.entrySet().iterator();
                System.out.print("VariableValue E: \n");
                while (itera.hasNext()) {
                    Map.Entry pair = (Map.Entry)itera.next();
                    System.out.println("Key: " + pair.getKey() + " Value: " + pair.getValue());
                }
            }
            System.out.println();
            System.out.println("MapValueValues: " + loopResult);
            return new Tuple<>(loopResult);
        } else {
            throw new IllegalArgumentException("Expected Tuple but got " + term.getClass().getSimpleName());
        }
    }
}
