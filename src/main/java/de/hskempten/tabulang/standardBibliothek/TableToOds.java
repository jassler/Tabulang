package de.hskempten.tabulang.standardBibliothek;

import de.hskempten.tabulang.libreOffice.OdsExportService;
import de.hskempten.tabulang.mySql.Models.MSqlTableContent;

public class TableToOds implements InternalFunction{
    @Override
    public Object compute(Object... args) {
        if(Helper.LengthReviewer(3, args)){
            MSqlTableContent sqlTableContent = null;
            String path, fileName;
            path = fileName = null;
            for(var item : args){
                if(item.getClass().equals(String.class)){
                    path = Helper.FindPath(item);
                    fileName = (String) item;
                }
                if(item.getClass().equals(OdsExportService.class)){
                    sqlTableContent = (MSqlTableContent) item;
                }
            }
            var odsExportService = new OdsExportService();
            odsExportService.InstantlyExportToFile(sqlTableContent, path, fileName);
            return true;
        }
        return false;
    }
}
