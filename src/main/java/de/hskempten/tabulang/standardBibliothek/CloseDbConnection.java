package de.hskempten.tabulang.standardBibliothek;

import de.hskempten.tabulang.mySql.DatabaseConnection;

public class CloseDbConnection implements InternalFunction {
    @Override
    public Object compute(Object... args) {
        DatabaseConnection.CloseConnection();
        return null;
    }
}