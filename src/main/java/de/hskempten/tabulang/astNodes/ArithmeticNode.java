package de.hskempten.tabulang.astNodes;

import de.hskempten.tabulang.datatypes.Identifier;
import de.hskempten.tabulang.datatypes.InternalNumber;
import de.hskempten.tabulang.datatypes.Table;
import de.hskempten.tabulang.datatypes.exceptions.VariableNotDeclaredException;
import de.hskempten.tabulang.datatypes.exceptions.VariableNotInitializedException;
import de.hskempten.tabulang.interpretTest.Interpretation;

import java.math.BigDecimal;

public abstract class ArithmeticNode extends TermNode {

    public Object getStringOrNumericValue(Node node, Interpretation interpretation) {
        Object o = node.evaluateNode(interpretation);
        if (o instanceof String || o instanceof InternalNumber) {
            return o;
        } else if (o instanceof Identifier) {
            o = getIdentifierValue((Identifier) o, interpretation);
            if ((o instanceof String) || (o instanceof InternalNumber)) {
                return o;
            } else {
                throw new IllegalArgumentException("Expected String or Number but got: " + o.getClass());
            }
        } else {
            throw new IllegalArgumentException("Expected String, Number or Identifier but got: " + o.getClass());
        }
    }

    public Object getTableOrNumericValue(Node node, Interpretation interpretation) {
        Object o = node.evaluateNode(interpretation);
        if(o instanceof Table || o instanceof BigDecimal){
            return o;
        } else if(o instanceof Identifier){
            o = getIdentifierValue((Identifier) o, interpretation);
            if(o instanceof Table || o instanceof BigDecimal){
                return o;
            } else {
                throw new IllegalArgumentException("Expected Table or BigDecimal but got: " + o.getClass());
            }
        } else {
            throw new IllegalArgumentException("Expected Table, BigDecimal or Identifier but got: " + o.getClass());
        }
    }

    public InternalNumber getNumericValue(Node node, Interpretation interpretation){
        Object o = node.evaluateNode(interpretation);
        if(o instanceof InternalNumber){
            return (InternalNumber) o;
        } else if(o instanceof Identifier){
            o = getIdentifierValue((Identifier) o, interpretation);
            if(o instanceof InternalNumber){
                return (InternalNumber) o;
            } else {
                throw new IllegalArgumentException("Expected Number but got: " + o.getClass());
            }
        } else {
            throw new IllegalArgumentException("Expected Number or Identifier but got: " + o.getClass());
        }
    }

    @Override
    public String toString() {
        return "ArithmeticNode{} " + super.toString();
    }
}

