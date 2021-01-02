package de.hskempten.tabulang.standardBibliothek;

import de.hskempten.tabulang.datatypes.InternalNumber;

public class PowFunc implements InternalFunction {

    @Override
    public Object compute(Object... args) {
        Helper.LengthReviewer(2, args);

        InternalNumber left = (InternalNumber) args[0];
        InternalNumber right = (InternalNumber) args[1];
        return left.mod(right);
    }
}
