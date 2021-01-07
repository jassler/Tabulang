package de.hskempten.tabulang.standardLibrary;

import de.hskempten.tabulang.libreOffice.OdsExportService;
import de.hskempten.tabulang.mySql.DatabaseConnection;

public class DatabaseToFile implements InternalFunction{
    @Override
    public Object compute(Object... args) {
        if(Helper.LengthReviewer(4, args)){
            String path, fileName, query;
            OdsExportService odsExportService;
            path = fileName = query = null;
            odsExportService = null;
            for(var item : args){
                if(item.getClass().equals(String.class)){
                    path = Helper.FindPath(item);
                    query = Helper.FindSqlStatement(item);
                    fileName = (String) item;
                }
                if(item.getClass().equals(OdsExportService.class)){
                    odsExportService = (OdsExportService) item;
                }
            }
            DatabaseConnection.ExportToFile(query, odsExportService, path, fileName);
            return true;
        }
        return false;
    }
}
