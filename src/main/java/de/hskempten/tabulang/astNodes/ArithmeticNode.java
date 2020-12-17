package de.hskempten.tabulang.astNodes;

import de.hskempten.tabulang.datatypes.Identifier;
import de.hskempten.tabulang.datatypes.Table;
import de.hskempten.tabulang.datatypes.exceptions.VariableNotDeclaredException;
import de.hskempten.tabulang.datatypes.exceptions.VariableNotInitializedException;
import de.hskempten.tabulang.interpretTest.Interpretation;

import java.math.BigDecimal;

public abstract class ArithmeticNode extends Node {

    public Object getStringOrNumericValue(Node node, Interpretation interpretation) {
        Object o = node.evaluateNode(interpretation);
        if (o instanceof String || o instanceof BigDecimal) {
            return o;
        } else if (o instanceof Identifier) {
            Interpretation found = interpretation.findIdentifier((Identifier) o);
            if(found == null){
                throw new VariableNotDeclaredException(((Identifier) o).getIdentifierName());
            }
            Object value = found.getEnvironment().get(((Identifier) o).getIdentifierName());
            if (value == null) {
                throw new VariableNotInitializedException(((Identifier) o).getIdentifierName());
            } else if ((value instanceof String) || (value instanceof BigDecimal)) {
                return value;
            } else {
                throw new IllegalArgumentException("Expected String or BigDecimal but got: " + value.getClass());
            }
        } else {
            throw new IllegalArgumentException("Expected String, BigDecimal or Identifier but got: " + o.getClass());
        }
    }

    public Object getTableOrNumericValue(Node node, Interpretation interpretation) {
        Object o = node.evaluateNode(interpretation);
        if(o instanceof Table || o instanceof BigDecimal){
            return o;
        } else if(o instanceof Identifier){
            Interpretation found = interpretation.findIdentifier((Identifier) o);
            if(found == null){
                throw new VariableNotDeclaredException(((Identifier) o).getIdentifierName());
            }
            Object value = found.getEnvironment().get(((Identifier) o).getIdentifierName());
            if(value == null){
                throw new VariableNotInitializedException(((Identifier) o).getIdentifierName());
            } else if(value instanceof Table || value instanceof BigDecimal){
                return value;
            } else {
                throw new IllegalArgumentException("Expected Table or BigDecimal but got: " + value.getClass());
            }
        } else {
            throw new IllegalArgumentException("Expected Table, BigDecimal or Identifier but got: " + o.getClass());
        }
    }

    public BigDecimal getNumericValue(Node node, Interpretation interpretation){
        Object o = node.evaluateNode(interpretation);
        if(o instanceof BigDecimal){
            return (BigDecimal) o;
        } else if(o instanceof Identifier){
            Interpretation found = interpretation.findIdentifier((Identifier) o);
            if(found == null){
                throw new VariableNotDeclaredException(((Identifier) o).getIdentifierName());
            }
            Object value = found.getEnvironment().get(((Identifier) o).getIdentifierName());
            if(value == null){
                throw new VariableNotInitializedException(((Identifier) o).getIdentifierName());
            } else if(value instanceof BigDecimal){
                return (BigDecimal) value;
            } else {
                throw new IllegalArgumentException("Expected BigDecimal but got: " + value.getClass());
            }
        } else {
            throw new IllegalArgumentException("Expected BigDecimal or Identifier but got: " + o.getClass());
        }
    }
}

