package de.hskempten.tabulang.datatypes;

public class InternalBoolean extends InternalObject{
    private Boolean aBoolean;

    public InternalBoolean(Boolean aBoolean) {
        super();
        this.aBoolean = aBoolean;
    }

    public Boolean getaBoolean() {
        return aBoolean;
    }

    public InternalBoolean and(InternalBoolean other){
        if(this.getaBoolean() && other.getaBoolean()){
            return new InternalBoolean(true);
        } else {
            return new InternalBoolean(false);
        }
    }

    @Override
    public String toString() {
        return aBoolean.toString();
    }
}
