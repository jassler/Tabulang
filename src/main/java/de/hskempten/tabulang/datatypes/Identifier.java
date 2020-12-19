package de.hskempten.tabulang.datatypes;

import de.hskempten.tabulang.datatypes.exceptions.VariableNotDeclaredException;
import de.hskempten.tabulang.interpretTest.Interpretation;

public class Identifier {
    private String identifierName;

    public Identifier(String identifierName) {
        this.identifierName = identifierName;
    }

    public String getIdentifierName() {
        return identifierName;
    }

    public void setIdentifierName(String identifierName) {
        this.identifierName = identifierName;
    }

    @Override
    public String toString() {
        return "Identifier{" +
                "identifierName='" + identifierName + '\'' +
                '}';
    }
}
