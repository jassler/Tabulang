package de.hskempten.tabulang.standardBibliothek;


public class ClassNotEqual extends RuntimeException{
    public ClassNotEqual() {}

    public ClassNotEqual(String message)
    {
        super(message);
    }
}
