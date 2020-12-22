package de.hskempten.tabulang.astNodes;

import de.hskempten.tabulang.datatypes.InternalFunction;
import de.hskempten.tabulang.datatypes.Identifier;
import de.hskempten.tabulang.datatypes.exceptions.VariableNotDeclaredException;
import de.hskempten.tabulang.interpretTest.Interpretation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class FunctionCallNode extends TermNode{
    private IdentifierNode node;
    private ArrayList<TermNode> parameters;

    public FunctionCallNode(IdentifierNode node, ArrayList<TermNode> parameters) {
        this.node = node;
        this.parameters = parameters;
    }

    public IdentifierNode getNode() {
        return node;
    }

    public void setNode(IdentifierNode node) {
        this.node = node;
    }

    public ArrayList<TermNode> getParameters() {
        return parameters;
    }

    public void setParameters(ArrayList<TermNode> parameters) {
        this.parameters = parameters;
    }

    @Override
    public Object evaluateNode(Interpretation interpretation) {
        Object o = node.evaluateNode(interpretation);
        if(!(o instanceof InternalFunction)){
            throw new IllegalArgumentException("Expected (InternalFunction)Identifier but got: " + o.getClass().getSimpleName());
        } else {
                Interpretation nestedInterpretation = new Interpretation(interpretation, new HashMap<>());
                if(((InternalFunction) o).getParameters().size() != parameters.size()){
                    throw new IllegalArgumentException("Expected " + ((InternalFunction) o).getParameters().size() + " parameter(s) but got " + parameters.size());
                } else {
                    for(int i = 0; i < ((InternalFunction) o).getParameters().size(); i++){
                        nestedInterpretation.getEnvironment().put((((InternalFunction) o).getParameters().get(i)).getIdentifier(), parameters.get(i).evaluateNode(interpretation));
                    }
                    for(Object statement : ((InternalFunction) o).getStatements()){
                        if(nestedInterpretation.getEnvironment().containsKey("return")) {
                            break;
                        }
                            ((Node) statement).evaluateNode(nestedInterpretation);
                    }
                    System.out.println("Function Call Nested Interpretation: ");
                    Iterator it = nestedInterpretation.getEnvironment().entrySet().iterator();
                    while (it.hasNext()) {
                        Map.Entry pair = (Map.Entry)it.next();
                        System.out.println("Key: " + pair.getKey() + " Value: " + pair.getValue());
                    }
                    System.out.println("");
                }
                if(nestedInterpretation.getEnvironment().containsKey("return")){
                    return nestedInterpretation.getEnvironment().get("return");
                }
                return null;

        }
    }
}
