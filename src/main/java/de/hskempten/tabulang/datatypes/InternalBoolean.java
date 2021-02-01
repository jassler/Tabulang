package de.hskempten.tabulang.datatypes;

public class InternalBoolean extends InternalObject{
    private Boolean b;

    public InternalBoolean(Boolean aBoolean) {
        super();
        this.b = aBoolean;
    }

    public Boolean getBoolean() {
        return b;
    }

    public InternalBoolean and(InternalBoolean other){
        return new InternalBoolean(this.b && other.b);
    }

    @Override
    public String toString() {
        return b.toString();
    }
}
