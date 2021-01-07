package de.hskempten.tabulang.standardLibrary.stringLibrary;

import de.hskempten.tabulang.datatypes.InternalString;
import de.hskempten.tabulang.standardLibrary.Helper;
import de.hskempten.tabulang.standardLibrary.InternalFunction;

import java.util.function.Function;

public class StandardStringToStringMethod implements InternalFunction {

    private Function<String, String> func;

    public StandardStringToStringMethod(Function<String, String> func) {
        this.func = func;
    }

    @Override
    public Object compute(Object... args) {
        Helper.assertArgumentLength(1, args);

        if(!(args[0] instanceof InternalString s))
            throw Helper.generateIllegalArgument(args[0], InternalString.class.getSimpleName());

        return new InternalString(func.apply(s.getString()));
    }
}
