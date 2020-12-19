package de.hskempten.tabulang.astNodes;


import de.hskempten.tabulang.datatypes.Identifier;
import de.hskempten.tabulang.datatypes.exceptions.VariableNotDeclaredException;
import de.hskempten.tabulang.datatypes.exceptions.VariableNotInitializedException;
import de.hskempten.tabulang.interpretTest.Interpretation;

public abstract class Node {
    public abstract Object evaluateNode(Interpretation interpretation);

    public Object getIdentifierValue(Identifier identifier, Interpretation interpretation) {
        Interpretation found = interpretation.findIdentifier(identifier);
        if (found == null) {
            throw new VariableNotDeclaredException((identifier).getIdentifierName());
        }
        Object value = found.getEnvironment().get((identifier).getIdentifierName());
        if (value == null) {
            throw new VariableNotInitializedException((identifier).getIdentifierName());
        }
        return value;
    }
}
