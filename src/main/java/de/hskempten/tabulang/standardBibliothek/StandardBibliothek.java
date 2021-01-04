package de.hskempten.tabulang.standardBibliothek;

import de.hskempten.tabulang.astNodes.IdentifierNode;
import de.hskempten.tabulang.datatypes.InternalLibraryFunction;
import de.hskempten.tabulang.datatypes.InternalString;
import de.hskempten.tabulang.datatypes.Table;
import de.hskempten.tabulang.datatypes.Tuple;
import de.hskempten.tabulang.interpretTest.Interpretation;

import java.util.ArrayList;

public class StandardBibliothek {
    private static Interpretation _interpreter;

    public static void main(String[] args){
        Table<InternalString> t = new Table<>(
                new Tuple<>(InternalString.objToArray("Felix", "Fritz", "Oberstdorf"), new String[]{"First name", "Last name", "Location"}),
                new Tuple<>(InternalString.objToArray("Tobias", "Teiher", "Kempten")),
                new Tuple<>(InternalString.objToArray("Manfred", "Meher", "Berlin"))
        );
    }

    private static ArrayList<IdentifierNode> generateIdentifiers(String... names) {
        var result = new ArrayList<IdentifierNode>(names.length);

        for(String n : names) {
            result.add(new IdentifierNode(n));
        }

        return result;
    }

    private static void AddToInterpreter(InternalFunction internalFunction, String... strings){
        var name = internalFunction.getClass().getName().substring(0, 1).toLowerCase() + internalFunction.getClass().getName().substring(1);
        _interpreter.putValue(name, new InternalLibraryFunction(generateIdentifiers(strings), internalFunction));
    }

    public static void addStandardLibrary(Interpretation interpreter) {
        _interpreter = interpreter;
        AddToInterpreter(new Print(), "x");
        AddToInterpreter(new OpenDbConnection(), "ip", "port", "dbName", "userName", "password");
        AddToInterpreter(new CloseDbConnection());
        AddToInterpreter(new DatabaseToTable(), "query");
        AddToInterpreter(new DatabaseToFile(), "query", "path", "fileName", "odsExportService");
        AddToInterpreter(new OdsToTable(), "path");
        AddToInterpreter(new PowFunc(), "left", "right");
        AddToInterpreter(new TableToDatabase(), "table", "sqlTableName");
        AddToInterpreter(new TableToOds(), "sqlTableContent", "path", "fileName");
        AddToInterpreter(new TableToStyledOds(), "table", "path", "fileName");
        AddToInterpreter(new ToLowerCase(), "item");
        AddToInterpreter(new ToUpperCase(), "item");
    }
}
