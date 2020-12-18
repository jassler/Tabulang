package de.hskempten.tabulang.standardBibliothek;

import de.hskempten.tabulang.datatypes.Table;
import de.hskempten.tabulang.libreOffice.OdsExportService;

public class TableToStyledOds implements InternalFunction{
    @Override
    public Object compute(Object... args) {
        if(Helper.LengthReviewer(3, args)){
            Table table = null;
            String path, fileName;
            path = fileName = null;
            for(var item : args){
                if(item.getClass().equals(String.class)){
                    path = Helper.FindPath(item);
                    fileName = (String) item;
                }
                if(item.getClass().equals(OdsExportService.class)){
                    table = (Table) item;
                }
            }
            var odsExportService = new OdsExportService();
            odsExportService.Export(table, path, fileName);
            return true;
        }
        return false;
    }
}
