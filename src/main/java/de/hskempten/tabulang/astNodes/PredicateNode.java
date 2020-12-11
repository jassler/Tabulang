package de.hskempten.tabulang.astNodes;

import de.hskempten.tabulang.datatypes.Identifier;
import de.hskempten.tabulang.datatypes.exceptions.VariableNotInitializedException;
import de.hskempten.tabulang.interpretTest.Interpretation;

import java.math.BigDecimal;

public abstract class PredicateNode extends Node{


    public PredicateNode() {
        super(NodeType.NODE);
    }

    public BigDecimal getNumericValue(Node node, Interpretation interpretation) {
        Object o = node.evaluateNode(interpretation);
        if (o instanceof Identifier) {
            Object identifierValue = interpretation.getEnvironment().get(((Identifier) o).getIdentifierName());
            if (identifierValue instanceof BigDecimal) {
                return (BigDecimal) identifierValue;
            } else if(identifierValue == null){
                throw new VariableNotInitializedException(((Identifier) o).getIdentifierName());
            }
        } else if (o instanceof BigDecimal) {
            return (BigDecimal) o;
        }
        throw new IllegalArgumentException("Expected Identifier or BigDecimal but got: " + o.getClass().getSimpleName());
    }
}
