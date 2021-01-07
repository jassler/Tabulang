package de.hskempten.tabulang.standardLibrary;

import de.hskempten.tabulang.datatypes.InternalString;
import de.hskempten.tabulang.libreOffice.OdsExportService;
import de.hskempten.tabulang.mySql.Models.MSqlTableContent;

/**
 * <p>SQL Table -> *.ods file</p>
 */
public class TableToOds implements InternalFunction{
    @Override
    public Object compute(Object... args) {
        Helper.assertArgumentLength(3, args);

        if(!(args[0] instanceof InternalString path))
            throw Helper.generateIllegalArgument(args[0], InternalString.class.getSimpleName());

        if(!(args[1] instanceof InternalString fileName))
            throw Helper.generateIllegalArgument(args[1], InternalString.class.getSimpleName());

        if(!(args[2] instanceof MSqlTableContent sqlTableContent))
            throw Helper.generateIllegalArgument(args[2], OdsExportService.class.getSimpleName());

        var odsExportService = new OdsExportService();
        odsExportService.InstantlyExportToFile(sqlTableContent, path.getString(), fileName.getString());
        return true;
    }
}
