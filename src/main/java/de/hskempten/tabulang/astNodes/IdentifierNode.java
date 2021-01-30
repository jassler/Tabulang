package de.hskempten.tabulang.astNodes;


import de.hskempten.tabulang.datatypes.exceptions.VariableNotDeclaredException;
import de.hskempten.tabulang.interpretTest.Interpretation;
import de.hskempten.tabulang.tokenizer.TextPosition;

public class IdentifierNode extends TermNode {
    private String identifier;

    public IdentifierNode(String identifier, TextPosition textPosition) {
        super(textPosition);
        this.identifier = identifier;
    }

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
        if(identifier.equals("mapValue")){
            identifier = "mapValue" + interpretation.getNestingLevel();
        }
        Interpretation found = interpretation.findIdentifier(identifier);
        if (found == null) {
            throw new VariableNotDeclaredException(identifier);
        } else {
            return found.getEnvironment().get(identifier);
        }
    }

    @Override
    public String toString() {
        return identifier;
    }
}
