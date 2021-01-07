package de.hskempten.tabulang.standardLibrary;

import de.hskempten.tabulang.astNodes.IdentifierNode;
import de.hskempten.tabulang.datatypes.InternalLibraryFunction;
import de.hskempten.tabulang.datatypes.InternalNumber;
import de.hskempten.tabulang.datatypes.InternalString;
import de.hskempten.tabulang.datatypes.Tuple;
import de.hskempten.tabulang.interpretTest.Interpretation;
import de.hskempten.tabulang.standardLibrary.importexport.*;
import de.hskempten.tabulang.standardLibrary.stringLibrary.Split;
import de.hskempten.tabulang.standardLibrary.stringLibrary.StandardStringToObj;
import de.hskempten.tabulang.standardLibrary.stringLibrary.StandardStringToStringMethod;

import java.util.ArrayList;

public class StandardLibrary {

    private static ArrayList<IdentifierNode> generateIdentifiers(String... names) {
        var result = new ArrayList<IdentifierNode>(names.length);

        for(String n : names) {
            result.add(new IdentifierNode(n));
        }

        return result;
    }

    private static void addToInterpreter(Interpretation interpreter, InternalFunction internalFunction, String... strings) {
        String name = internalFunction.getClass().getSimpleName();

        // make first letter lowercase
        name = Character.toLowerCase(name.charAt(0)) + name.substring(1);
        interpreter.putValue(name, new InternalLibraryFunction(generateIdentifiers(strings), internalFunction));
    }

    /**
     * <p>Add internal library function to the interpreter.</p>
     *
     * <p>Each function is of type {@link InternalLibraryFunction} that takes a set of parameters.</p>
     *
     * <p>Function names are generated from the corresponding class name (eg. Print.class -> print()).</p>
     *
     * @param interpreter Interpreter to add the internal functions to
     */
    public static void addStandardLibrary(Interpretation interpreter) {
        addToInterpreter(interpreter, new Print(), "x");

        // db / sql / odf stuff
        addToInterpreter(interpreter, new OpenDbConnection(), "ip", "port", "dbName", "userName", "password");
        addToInterpreter(interpreter, new CloseDbConnection());
        addToInterpreter(interpreter, new DatabaseToTable(), "query");
        addToInterpreter(interpreter, new DatabaseToFile(), "query", "path", "fileName", "odsExportService");
        addToInterpreter(interpreter, new OdsToTable(), "path");
        addToInterpreter(interpreter, new TableToDatabase(), "table", "sqlTableName");
        addToInterpreter(interpreter, new TableToOds(), "sqlTableContent", "path", "fileName");
        addToInterpreter(interpreter, new TableToStyledOds(), "table", "path", "fileName");

        // math stuff
        addToInterpreter(interpreter, new PowFunc(), "left", "right");

        // string stuff
        interpreter.putValue("toLowerCase", new InternalLibraryFunction(new StandardStringToStringMethod(String::toLowerCase), "s"));
        interpreter.putValue("toUpperCase", new InternalLibraryFunction(new StandardStringToStringMethod(String::toUpperCase), "s"));
        interpreter.putValue("strip", new InternalLibraryFunction(new StandardStringToStringMethod(String::strip), "s"));
        interpreter.putValue("trim", new InternalLibraryFunction(new StandardStringToStringMethod(String::trim), "s"));

        interpreter.putValue("stringLength", new InternalLibraryFunction(new StandardStringToObj<>(String::length, InternalNumber::new), "s"));
        interpreter.putValue("splitString", new InternalLibraryFunction(new Split(), "s", "regex"));
    }
}
