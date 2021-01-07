package de.hskempten.tabulang.standardLibrary;

public class Print implements InternalFunction {

    @Override
    public Object compute(Object... args) {
        for(Object o : args)
            System.out.println(o);

        return null;
    }
}
