package de.hskempten.tabulang.astNodes;

import de.hskempten.tabulang.datatypes.Identifier;
import de.hskempten.tabulang.datatypes.exceptions.VariableNotInitializedException;
import de.hskempten.tabulang.interpretTest.Interpretation;

import java.math.BigDecimal;

public abstract class PredicateNode extends Node{

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

    public Boolean getBooleanValue(Node node, Interpretation interpretation){
        Object pred = node.evaluateNode(interpretation);
        if(pred instanceof Boolean){
            return (Boolean) pred;
        } else {
            throw new IllegalArgumentException("Expected Boolean but got " + pred.getClass().getSimpleName());
        }
    }
}
