package de.hskempten.tabulang.standardLibrary.importexport;

import de.hskempten.tabulang.datatypes.*;
import de.hskempten.tabulang.libreOffice.OdsExportService;
import de.hskempten.tabulang.mySql.Models.MSqlTableContent;
import de.hskempten.tabulang.standardLibrary.Helper;
import de.hskempten.tabulang.standardLibrary.InternalFunction;

/**
 * <p>{@link Table} object -> *.ods file</p>
 */
public class TableToStyledOds implements InternalFunction {
    @Override
    public Object compute(Object... args) {
        Helper.assertArgumentLength(3, args);

        if(!(args[0] instanceof InternalString path))
            throw Helper.generateIllegalArgument(args[0], InternalString.class.getSimpleName());

        if(!(args[1] instanceof InternalString fileName))
            throw Helper.generateIllegalArgument(args[1], InternalString.class.getSimpleName());

        Table<?> table = null;
        if(args[2] instanceof Table<?> t)
            table = t;
        else if(args[2] instanceof Tuple<?> t) {
            Object o = t.transformIntoTableIfPossible();
            if(o instanceof Table<?> ta)
                table = ta;
        }

        if(table == null)
            throw Helper.generateIllegalArgument(args[2], Table.class.getSimpleName());

        var odsExportService = new OdsExportService();
        odsExportService.Export((Table<InternalObject>) table, path.getString(), fileName.getString());
        return null;
    }
}
