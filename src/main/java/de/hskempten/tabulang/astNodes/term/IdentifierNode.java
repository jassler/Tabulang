package de.hskempten.tabulang.astNodes.term;


import de.hskempten.tabulang.interpreter.Interpretation;
import de.hskempten.tabulang.datatypes.exceptions.VariableNotDeclaredException;
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

    /**
     * Gets the value associated with the specified identifier in the interpretation or its parents.
     *
     * @return the return value if the statements contain a return statement,
     * null otherwise
     * @throws VariableNotDeclaredException if the specified identifier has not been declared in any interpretation.
     */
    //mapValue has to be looked up separately as the user does not know that it is stored with an internal number
    //generated by the parser
    @Override
    public Object evaluateNode(Interpretation interpretation) {
        if (identifier.equals("mapValue")) {
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
