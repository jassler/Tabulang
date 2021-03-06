package de.hskempten.tabulang.standardLibrary;

import de.hskempten.tabulang.datatypes.InternalNumber;

public class PowFunc implements InternalFunction {

    @Override
    public Object compute(Object... args) {
        Helper.assertArgumentLength(2, args);

        InternalNumber left = (InternalNumber) args[0];
        InternalNumber right = (InternalNumber) args[1];
        return left.pow(right);
    }
}
