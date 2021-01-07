package de.hskempten.tabulang.standardLibrary;


public class ClassNotEqual extends RuntimeException{
    public ClassNotEqual() {}

    public ClassNotEqual(String message)
    {
        super(message);
    }
}
