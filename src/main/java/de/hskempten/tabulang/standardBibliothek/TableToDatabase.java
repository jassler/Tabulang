package de.hskempten.tabulang.standardBibliothek;

import de.hskempten.tabulang.datatypes.Table;
import de.hskempten.tabulang.mySql.DatabaseConnection;

public class TableToDatabase implements InternalFunction{
    @Override
    public Object compute(Object... args) {
        if(Helper.LengthReviewer(2, args)){
            String sqlTableName = null;
            Table table = null;
            for(var item : args){
                if(item.getClass().equals(String.class)){
                    sqlTableName = (String) item;
                }
                else {
                    table = (Table) item;
                }
            }
            DatabaseConnection.ImportFromTable(table, sqlTableName);
            return true;
        }
        return false;
    }
}
