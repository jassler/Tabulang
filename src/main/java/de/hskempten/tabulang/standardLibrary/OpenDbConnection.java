package de.hskempten.tabulang.standardLibrary;

import de.hskempten.tabulang.datatypes.InternalNumber;
import de.hskempten.tabulang.datatypes.InternalString;
import de.hskempten.tabulang.mySql.DatabaseConnection;
import de.hskempten.tabulang.mySql.Models.MSqlConnectionParameters;

public class OpenDbConnection implements InternalFunction {
    @Override
    public Object compute(Object... args) {
        if(Helper.LengthReviewer(5, args)){
            var ip = ((InternalString)args[0]).getString();
            var port = (int) ((InternalNumber)args[1]).getFloatValue();
            var dbName = ((InternalString)args[2]).getString();
            var userName = ((InternalString)args[3]).getString();
            var password = ((InternalString)args[4]).getString();
            var parameters = new MSqlConnectionParameters(ip, port, dbName, userName, password);
            DatabaseConnection.OpenConnection(parameters);
            return true;
        }
        return false;
    }
}
