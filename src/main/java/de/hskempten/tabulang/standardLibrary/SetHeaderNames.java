package de.hskempten.tabulang.standardLibrary;

import de.hskempten.tabulang.datatypes.HeaderNames;
import de.hskempten.tabulang.datatypes.InternalString;
import de.hskempten.tabulang.datatypes.Table;
import de.hskempten.tabulang.datatypes.Tuple;

import java.util.List;
import java.util.stream.Collectors;

public class SetHeaderNames implements InternalFunction {
    @Override
    public Object compute(Object... args) {
        Helper.assertArgumentLength(2, args);

        if(!(args[1] instanceof Tuple<?> newHeaderTuple))
            throw Helper.generateIllegalArgument(args[1], Tuple.class.getSimpleName());

        List<InternalString> newHeader = newHeaderTuple.getElements().stream().map(v -> new InternalString(v.toString())).collect(Collectors.toList());

        if(args[0] instanceof Tuple<?> tuple) {
            tuple.setNames(new HeaderNames(newHeader));

        } else if(args[0] instanceof Table<?> table) {
            table.setColNames(new HeaderNames(newHeader));

        } else {
            throw Helper.generateIllegalArgument(args[0], Tuple.class.getSimpleName(), Table.class.getSimpleName());
        }

        return null;
    }
}
