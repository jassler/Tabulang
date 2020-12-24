package de.hskempten.tabulang.astNodes;


import de.hskempten.tabulang.datatypes.Identifier;
import de.hskempten.tabulang.datatypes.exceptions.VariableNotDeclaredException;
import de.hskempten.tabulang.interpretTest.Interpretation;

public class IdentifierNode extends TermNode{
    private String identifier;

    public IdentifierNode(String identifier) {
        this.identifier = identifier;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    @Override
    public Object evaluateNode(Interpretation interpretation) {
        Interpretation found = interpretation.findIdentifier(identifier);
        if(found == null){
            //TODO Fehlermeldung oder Identifier -> Null setzen                                                                                                  111111                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              ee
            throw new VariableNotDeclaredException(identifier);
        } else {
            return found.getEnvironment().get(identifier);
        }
    }

    @Override
    public String toString() {
        return "IdentifierNode{" +
                "identifier='" + identifier + '\'' +
                "} ";
    }
}
