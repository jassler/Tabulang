package de.hskempten.tabulang.datatypes;

public class InternalString extends InternalObject{
    private String string;

    public InternalString(String string) {
        super(null);
        this.string = string;
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }

    @Override
    public String toString() {
        return string;
    }
}
