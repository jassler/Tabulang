package de.hskempten.tabulang.standardBibliothek;

import de.hskempten.tabulang.mySql.DatabaseConnection;

public class DatabaseToTable implements InternalFunction{
    @Override
    public Object compute(Object... args) {
        if(Helper.LengthReviewer(1, args)){
            var query = (String) args[0];
            DatabaseConnection.ExportAsTable(query);
            return true;
        }
        return false;
    }
}
