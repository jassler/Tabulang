package de.hskempten.tabulang.standardBibliothek;

import de.hskempten.tabulang.astNodes.IdentifierNode;
import de.hskempten.tabulang.datatypes.InternalLibraryFunction;
import de.hskempten.tabulang.interpretTest.Interpretation;

import java.util.ArrayList;

public class StandardBibliothek {

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
        addToInterpreter(interpreter, new OpenDbConnection(), "ip", "port", "dbName", "userName", "password");
        addToInterpreter(interpreter, new CloseDbConnection());
        addToInterpreter(interpreter, new DatabaseToTable(), "query");
        addToInterpreter(interpreter, new DatabaseToFile(), "query", "path", "fileName", "odsExportService");
        addToInterpreter(interpreter, new OdsToTable(), "path");
        addToInterpreter(interpreter, new PowFunc(), "left", "right");
        addToInterpreter(interpreter, new TableToDatabase(), "table", "sqlTableName");
        addToInterpreter(interpreter, new TableToOds(), "sqlTableContent", "path", "fileName");
        addToInterpreter(interpreter, new TableToStyledOds(), "table", "path", "fileName");
        addToInterpreter(interpreter, new ToLowerCase(), "item");
        addToInterpreter(interpreter, new ToUpperCase(), "item");
    }
}
