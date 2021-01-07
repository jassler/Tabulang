package de.hskempten.tabulang.standardBibliothek;

import de.hskempten.tabulang.mySql.DatabaseConnection;
import de.hskempten.tabulang.standardLibrary.InternalFunction;
import de.hskempten.tabulang.mySql.Exceptions.MySqlConnectionException;

public class CloseDbConnection implements InternalFunction {
    @Override
    public Object compute(Object... args) throws MySqlConnectionException {
        DatabaseConnection.CloseConnection();
        return null;
    }
}
