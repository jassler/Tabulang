package de.hskempten.tabulang.standardBibliothek;

import de.hskempten.tabulang.datatypes.InternalString;
import de.hskempten.tabulang.mySql.DatabaseConnection;

public class DatabaseToTable implements InternalFunction{
    @Override
    public Object compute(Object... args) {
        if(Helper.LengthReviewer(1, args)){
            return DatabaseConnection.ExportAsTable((String) args[0]);
        }
        return null;
    }
}
