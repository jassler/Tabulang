package de.hskempten.tabulang.standardBibliothek;

public interface InternalFunction {
    public boolean hasExpectedObjectType(Object... args);
    public Object compute(Object... args);
}
