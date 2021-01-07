package de.hskempten.tabulang.standardLibrary;

import de.hskempten.tabulang.datatypes.Table;
import de.hskempten.tabulang.datatypes.Tuple;

public class GetHeaderNames implements InternalFunction {

    @Override
    public Object compute(Object... args) {
        Helper.assertArgumentLength(1, args);

        if(args[0] instanceof Tuple<?> t)
            return new Tuple<>(t.getNames().getNames());

        else if(args[0] instanceof Table<?> t)
            return new Tuple<>(t.getColNames().getNames());

        else
            throw Helper.generateIllegalArgument(args[0], Tuple.class.getSimpleName(), Table.class.getSimpleName());
    }
}
