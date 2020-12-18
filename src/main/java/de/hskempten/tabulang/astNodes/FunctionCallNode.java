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
        if(!(o instanceof Identifier)){
            throw new IllegalArgumentException("Expected (InternalFunction)Identifier but got: " + o.getClass().getSimpleName());
        } else {
            Interpretation found = interpretation.findIdentifier((Identifier) o);
            if(found == null){
                throw new VariableNotDeclaredException(((Identifier) o).getIdentifierName());
            }
            Object value = found.getEnvironment().get(((Identifier) o).getIdentifierName());
            //System.out.println(value + " " + value.getClass().getSimpleName());
            if(!(value instanceof InternalFunction)){
                throw new IllegalArgumentException("Expected Identifier to be a InternalFunction but got: " + value.getClass().getSimpleName());
            } else {
                HashMap<String, Object> nestedHashmap = new HashMap<>();
                Interpretation nestedInterpretation = new Interpretation(interpretation, nestedHashmap);
                if(((InternalFunction) value).getParameters().size() != parameters.size()){
                    throw new IllegalArgumentException("Expected " + ((InternalFunction) value).getParameters().size() + " parameter(s) but got " + parameters.size());
                } else {
                    for(int i = 0; i < ((InternalFunction) value).getParameters().size(); i++){
                        nestedInterpretation.getEnvironment().put((((InternalFunction) value).getParameters().get(i)).getIdentifier(), parameters.get(i).evaluateNode(nestedInterpretation));
                    }
                    for(Object statement : ((InternalFunction) value).getStatements()){
                        if(nestedInterpretation.getEnvironment().containsKey("return")) {
                            break;
                        }
                            ((Node) statement).evaluateNode(nestedInterpretation);
                    }
                    Iterator it = nestedInterpretation.getEnvironment().entrySet().iterator();
                    while (it.hasNext()) {
                        Map.Entry pair = (Map.Entry)it.next();
                        System.out.println("Key: " + pair.getKey() + " Value: " + pair.getValue());
                    }
                }
                if(nestedInterpretation.getEnvironment().containsKey("return")){
                    return nestedInterpretation.getEnvironment().get("return");
                }
                return null;
            }
        }
    }
}
