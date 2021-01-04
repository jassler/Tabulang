package de.hskempten.tabulang.standardBibliothek;

import de.hskempten.tabulang.astNodes.IdentifierNode;
import de.hskempten.tabulang.datatypes.InternalLibraryFunction;
import de.hskempten.tabulang.datatypes.Table;
import de.hskempten.tabulang.datatypes.Tuple;
import de.hskempten.tabulang.interpretTest.Interpretation;

import java.util.ArrayList;

public class StandardBibliothek {
    private static Interpretation _interpreter;

    public static void main(String[] args){
        Table<String> t = new Table<>(
                new Tuple<>(new String[]{"Felix", "Fritz", "Oberstdorf"}, new String[]{"First name", "Last name", "Location"}),
                new Tuple<>(new String[]{"Tobias", "Teiher", "Kempten"}),
                new Tuple<>(new String[]{"Manfred", "Meher", "Berlin"})
        );
    }

    private static ArrayList<IdentifierNode> generateIdentifiers(String... names) {
        var result = new ArrayList<IdentifierNode>(names.length);

        for(String n : names) {
            result.add(new IdentifierNode(n));
        }

        return result;
    }



//    private static Object ILFCreator(InternalFunction internalFunction, String... strings){
//        return new InternalLibraryFunction(generateIdentifiers(strings), internalFunction);
//    }

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

//        interpreter
//                .putValue("print", ILFCreator(new Print(), "x"))
//                .putValue("openDbConnection", ILFCreator(new OpenDbConnection(), "ip", "port", "dbName", "userName", "password"))
//                .putValue("closeDbConnection", ILFCreator(new CloseDbConnection()))
//                .putValue("queryDatabase", ILFCreator(new DatabaseToTable(), "query"));

//        interpreter.putValue("print", new InternalLibraryFunction(
//                generateIdentifiers("x"), new Print()
//
//        )).putValue("openDbConnection", new InternalLibraryFunction(
//                generateIdentifiers("ip", "port", "dbName", "userName", "password"), new OpenDbConnection()
//
//        )).putValue("closeDbConnection", new InternalLibraryFunction(
//                generateIdentifiers(), new CloseDbConnection()
//
//        )).putValue("queryDatabase", new InternalLibraryFunction(
//                generateIdentifiers(), new DatabaseToTable()
//        ));

    }


}
