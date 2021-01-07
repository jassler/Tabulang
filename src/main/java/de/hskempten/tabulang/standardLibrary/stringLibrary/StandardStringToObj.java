package de.hskempten.tabulang.standardLibrary.stringLibrary;

import de.hskempten.tabulang.datatypes.InternalString;
import de.hskempten.tabulang.standardLibrary.Helper;
import de.hskempten.tabulang.standardLibrary.InternalFunction;

import java.util.function.Function;

public class StandardStringToObj<E, O> implements InternalFunction {

    private Function<String, E> func;
    private Function<E, O> objectToReturn;

    public StandardStringToObj(Function<String, E> func, Function<E, O> objectToReturn) {
        this.func = func;
        this.objectToReturn = objectToReturn;
    }

    @Override
    public Object compute(Object... args) {
        Helper.assertArgumentLength(1, args);

        if(!(args[0] instanceof InternalString s))
            throw Helper.generateIllegalArgument(args[0], InternalString.class.getSimpleName());

        E result = func.apply(s.getString());
        return objectToReturn.apply(result);
    }
}
