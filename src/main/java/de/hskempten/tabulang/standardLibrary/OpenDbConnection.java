package de.hskempten.tabulang.standardLibrary;

import de.hskempten.tabulang.datatypes.InternalNumber;
import de.hskempten.tabulang.datatypes.InternalString;
import de.hskempten.tabulang.mySql.DatabaseConnection;
import de.hskempten.tabulang.mySql.Models.MSqlConnectionParameters;

/**
 * <p>Open connection to MySQL server.</p>
 *
 * <p>{@code openDbConnection(ip, port, dbName, userName, password)}</p>
 */
public class OpenDbConnection implements InternalFunction {
    @Override
    public Object compute(Object... args) {
        Helper.assertArgumentLength(5, args);

        if(!(args[0] instanceof InternalString ip))
            throw Helper.generateIllegalArgument(args[0], InternalString.class.getSimpleName());

        if(!(args[1] instanceof InternalNumber port))
            throw Helper.generateIllegalArgument(args[1], InternalNumber.class.getSimpleName());

        if(!(args[2] instanceof InternalString dbName))
            throw Helper.generateIllegalArgument(args[2], InternalString.class.getSimpleName());

        if(!(args[3] instanceof InternalString userName))
            throw Helper.generateIllegalArgument(args[3], InternalString.class.getSimpleName());

        if(!(args[4] instanceof InternalString password))
            throw Helper.generateIllegalArgument(args[4], InternalString.class.getSimpleName());

        var parameters = new MSqlConnectionParameters(ip.getString(), (int) port.getFloatValue(), dbName.getString(), userName.getString(), password.getString());
        DatabaseConnection.OpenConnection(parameters);
        return null;
    }
}
