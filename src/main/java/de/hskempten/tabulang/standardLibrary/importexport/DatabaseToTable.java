package de.hskempten.tabulang.standardLibrary.importexport;

import de.hskempten.tabulang.datatypes.InternalString;
import de.hskempten.tabulang.mySql.DatabaseConnection;
import de.hskempten.tabulang.standardLibrary.Helper;
import de.hskempten.tabulang.standardLibrary.InternalFunction;

/**
 * <p>MySQL query -> {@link de.hskempten.tabulang.datatypes.Table} object</p>
 *
 * <p>{@code databaseToTable(query);}</p>
 *
 * <p>query is a SQL command. A connection to a database has to be established through {@link OpenDbConnection}
 * before calling this function.</p>
 */
public class DatabaseToTable implements InternalFunction {

    @Override
    public Object compute(Object... args) {
        Helper.assertArgumentLength(1, args);

        if(!(args[0] instanceof InternalString query))
            throw Helper.generateIllegalArgument(args[0], InternalString.class.getSimpleName());

        return DatabaseConnection.ExportAsTable(query.getString());
    }
}
