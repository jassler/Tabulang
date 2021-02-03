package de.hskempten.tabulang.datatypes;


public class Identifier extends InternalObject {
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
        return identifierName;
    }
}


