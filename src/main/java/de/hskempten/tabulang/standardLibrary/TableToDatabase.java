package de.hskempten.tabulang.standardLibrary;

import de.hskempten.tabulang.datatypes.InternalString;
import de.hskempten.tabulang.datatypes.Table;
import de.hskempten.tabulang.mySql.DatabaseConnection;

/**
 * <p>{@link Table} object -> SQL Table</p>
 */
public class TableToDatabase implements InternalFunction {

    @Override
    public Object compute(Object... args) {
        Helper.assertArgumentLength(2, args);

        if(!(args[0] instanceof InternalString sqlTableName))
            throw Helper.generateIllegalArgument(args[0], InternalString.class.getSimpleName());

        if(!(args[1] instanceof Table<?> table))
            throw Helper.generateIllegalArgument(args[1], Table.class.getSimpleName());

        DatabaseConnection.ImportFromTable((Table<InternalString>) table, sqlTableName.getString());
        return true;
    }
}
