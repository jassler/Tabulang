package de.hskempten.tabulang.standardLibrary.stringLibrary;

import de.hskempten.tabulang.datatypes.InternalString;
import de.hskempten.tabulang.standardLibrary.Helper;
import de.hskempten.tabulang.standardLibrary.InternalFunction;

public class ToString implements InternalFunction {
    @Override
    public Object compute(Object... args) {
        Helper.assertArgumentLength(1, args);
        return new InternalString(args[0].toString());
    }
}
