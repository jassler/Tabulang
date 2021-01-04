package de.hskempten.tabulang.datatypes;

public class InternalBoolean extends InternalObject{
    private Boolean aBoolean;

    public InternalBoolean(Boolean aBoolean) {
        this.aBoolean = aBoolean;
    }

    public Boolean getaBoolean() {
        return aBoolean;
    }

    public void setaBoolean(Boolean aBoolean) {
        this.aBoolean = aBoolean;
    }

    @Override
    public String toString() {
        return aBoolean.toString();
    }
}
