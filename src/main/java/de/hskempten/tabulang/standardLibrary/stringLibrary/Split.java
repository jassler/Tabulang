package de.hskempten.tabulang.standardLibrary.stringLibrary;

import de.hskempten.tabulang.datatypes.InternalString;
import de.hskempten.tabulang.datatypes.Tuple;
import de.hskempten.tabulang.standardLibrary.Helper;
import de.hskempten.tabulang.standardLibrary.InternalFunction;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Split implements InternalFunction {

    @Override
    public Object compute(Object... args) {
        Helper.assertArgumentLength(2, args);

        if(!(args[0] instanceof InternalString s))
            throw Helper.generateIllegalArgument(args[0], InternalString.class.getSimpleName());

        if(!(args[1] instanceof InternalString regex))
            throw Helper.generateIllegalArgument(args[1], InternalString.class.getSimpleName());

        String[] result = s.getString().split(regex.getString());
        return new Tuple<>(Stream.of(result).map(InternalString::new).collect(Collectors.toList()));
    }
}
