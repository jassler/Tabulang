package de.hskempten.tabulang.standardBibliothek;

import de.hskempten.tabulang.datatypes.InternalNumber;
import de.hskempten.tabulang.libreOffice.OdsExportService;
import de.hskempten.tabulang.mySql.DatabaseConnection;
import de.hskempten.tabulang.mySql.Models.MSqlConnectionParameters;

public class OpenDbConnection implements InternalFunction {
    @Override
    public Object compute(Object... args) {
        if(Helper.LengthReviewer(5, args)){
            var ip = (String)args[0];
            var port = (int) ((InternalNumber)args[1]).getFloatValue();
            var dbName = (String)args[2];
            var userName = (String)args[3];
            var password = (String)args[4];
            var parameters = new MSqlConnectionParameters(ip, port, dbName, userName, password);
            DatabaseConnection.OpenConnection(parameters);
            return true;
        }
        return false;
    }
}
