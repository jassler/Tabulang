package de.hskempten.tabulang.standardLibrary;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Helper {
    public static void assertArgumentLength(int length, Object... args){
        if(args.length == length)
            throw new IndexOutOfBoundsException("Expected " + length + " parameters, got " + args.length);
    }

    public static IllegalArgumentException generateIllegalArgument(Object got, String... expected) {
        return new IllegalArgumentException("Expected" + String.join(", ", expected)
                + ", got " + got.getClass().getSimpleName() + " instead");
    }

    public static String findPath(String item){
        if(item.contains("/") || item.contains("\\")) {
            return item;
        }
        return null;
    }

    public static String findSqlStatement(String item){
        if(item.contains("SELECT")) {
            return item;
        }
        return null;
    }
}
