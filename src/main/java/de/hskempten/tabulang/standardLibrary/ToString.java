package de.hskempten.tabulang.standardLibrary;

// Standarddatentypen der Tabellensprache (Zahlen, String, Tuple, Tabellen und Bool) --> System.out.print()

public class ToString implements InternalFunction{
    @Override
    public Object compute(Object... args) {
        return args[0].toString();
    }
}
