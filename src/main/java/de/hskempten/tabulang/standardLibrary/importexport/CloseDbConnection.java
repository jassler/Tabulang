package de.hskempten.tabulang.standardLibrary.importexport;

import de.hskempten.tabulang.mySql.DatabaseConnection;
import de.hskempten.tabulang.standardLibrary.InternalFunction;

public class CloseDbConnection implements InternalFunction {
    @Override
    public Object compute(Object... args) {
        DatabaseConnection.CloseConnection();
        return null;
    }
}
