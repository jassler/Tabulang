package de.hskempten.tabulang.standardLibrary;

import de.hskempten.tabulang.libreOffice.OdsExportService;
import de.hskempten.tabulang.mySql.DatabaseConnection;

/**
 * <p>MySQL -> *.ods file</p>
 *
 * <p>{@code databaseToFile(query, path, fileName, odsExportService)}</p>
 */
public class DatabaseToFile implements InternalFunction{
    @Override
    public Object compute(Object... args) {
        Helper.assertArgumentLength(4, args);

        String path, fileName, query;
        OdsExportService odsExportService;
        path = fileName = query = null;
        odsExportService = null;

        for(var item : args){
            if(item instanceof String s){
                path = Helper.findPath(s);
                query = Helper.findSqlStatement(s);
                fileName = s;
            }

            if(item.getClass().equals(OdsExportService.class)){
                odsExportService = (OdsExportService) item;
            }
        }
        DatabaseConnection.ExportToFile(query, odsExportService, path, fileName);
        return true;
    }
}
