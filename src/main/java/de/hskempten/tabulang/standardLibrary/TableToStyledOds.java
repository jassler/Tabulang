package de.hskempten.tabulang.standardLibrary;

import de.hskempten.tabulang.datatypes.InternalDataObject;
import de.hskempten.tabulang.datatypes.InternalString;
import de.hskempten.tabulang.datatypes.Table;
import de.hskempten.tabulang.libreOffice.OdsExportService;
import de.hskempten.tabulang.mySql.Models.MSqlTableContent;

/**
 * <p>{@link Table} object -> *.ods file</p>
 */
public class TableToStyledOds implements InternalFunction{
    @Override
    public Object compute(Object... args) {
        Helper.assertArgumentLength(3, args);

        if(!(args[0] instanceof InternalString path))
            throw Helper.generateIllegalArgument(args[0], InternalString.class.getSimpleName());

        if(!(args[1] instanceof InternalString fileName))
            throw Helper.generateIllegalArgument(args[1], InternalString.class.getSimpleName());

        if(!(args[2] instanceof Table<?> table))
            throw Helper.generateIllegalArgument(args[2], Table.class.getSimpleName());

        var odsExportService = new OdsExportService();
        odsExportService.Export((Table<InternalDataObject>) table, path.getString(), fileName.getString());
        return null;
    }
}
