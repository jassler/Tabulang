package de.hskempten.tabulang.standardBibliothek;

import de.hskempten.tabulang.astNodes.IdentifierNode;
import de.hskempten.tabulang.datatypes.InternalLibraryFunction;
import de.hskempten.tabulang.datatypes.InternalString;
import de.hskempten.tabulang.datatypes.Table;
import de.hskempten.tabulang.datatypes.Tuple;
import de.hskempten.tabulang.interpretTest.Interpretation;

import java.util.ArrayList;

public class MainClass {

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



    public static void addStandardLibrary(Interpretation interpreter) {

        interpreter.putValue("print", new InternalLibraryFunction(
                generateIdentifiers("x"), new Print()

        )).putValue("openDbConnection", new InternalLibraryFunction(
                generateIdentifiers("ip", "port", "dbName", "userName", "password"), new OpenDbConnection()

        )).putValue("closeDbConnection", new InternalLibraryFunction(
                generateIdentifiers(), new CloseDbConnection()

        )).putValue("queryDatabase", new InternalLibraryFunction(
                generateIdentifiers("query"), new DatabaseToTable()
        ));

    }


}
