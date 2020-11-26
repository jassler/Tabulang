package de.hskempten.tabulang.mySql;

import de.hskempten.tabulang.libreOffice.OdsExportService;
import de.hskempten.tabulang.mySql.Models.MSqlTableContent;

import java.util.ArrayList;

public class MainClass {
    private static final String _ip = "85.214.33.119";
    private static final int _port = 3306;
    private static final String _dbName = "sakila";
    private static final String _dbUser = "db_user";
    private static final String _dbPassword = "HsKemptenProjekt2020";
    private static final String _sqlTableName = "test_db";
    private static final String _instanceName = "Blubb";
    private static final String _calcTableName = "Blubb";
    private static final String _path = "D:\\TestLibreOffice";
    private static final String _fileName = "test";

    public static void main(String[] args) {
        // DECLARATION FOR DATABASE CONNECTION
        //var dbConnectionParameters = new MSqlConnectionParameters(_ip, _port, _dbName, _dbUser, _dbPassword);

        // DECLARATION CONNECTION TO LIBRE OFFICE
        //var calcConnection = new CalcConnection(_instanceName, _calcTableName, _path, _fileName);

        // OPEN DATABASE CONNECTION
        //DatabaseConnection.OpenConnection(dbConnectionParameters);

        // SQL QUERY DECLARATION
        //var query = String.format("SELECT * FROM %s", _sqlTableName);
        //var queryJoin = String.format("SELECT f.title, f.description, l.name FROM sakila.film f JOIN sakila.language l;");

        // TESTLIST FOR SQL-IMPORT
        /*
        var headlines = new ArrayList<String>();
        headlines.add("name");
        headlines.add("active");
        headlines.add("age");
        var values = new ArrayList<ArrayList<String>>();
        var first_value = new ArrayList<String>();
        first_value.add("Michi");
        first_value.add("true");
        first_value.add("34");
        values.add(first_value);
         */

        // INSERT DATA INTO DATABASE
        //var insertObject = new MSqlTableContent("test_db", headlines, values);
        //DatabaseConnection.Import(insertObject);

        // EXPORT DATA FROM DATABASE
        //DatabaseConnection.Export(query, calcConnection, true);
        //System.out.println(DatabaseConnection.ExportAsTable(query));
        //DatabaseConnection.ExportToFile(query, calcConnection);

        // OPEN FILE AND CALL IMPORT FUNCTION
        //calcConnection.Import(new File(_path, _fileName+".ods").toString());

        var headlines = new ArrayList<String>();
        headlines.add("id");
        headlines.add("firstname");
        headlines.add("lastname");

        var content = new ArrayList<ArrayList<String>>();
        var line1 = new ArrayList<String>();
        line1.add("1");
        line1.add("2");
        line1.add("3");

        var line2 = new ArrayList<String>();
        line2.add("simon");
        line2.add("anna");
        line2.add("michi");

        var line3 = new ArrayList<String>();
        line3.add("staiger");
        line3.add("müller");
        line3.add("wolf");

        content.add(line1);
        content.add(line2);
        content.add(line3);

        var wrapper = new MSqlTableContent("Test", headlines, content);

        var odfGenerator = new OdsExportService("YeahBlubb");
        //var data = DatabaseConnection.ExportCore(query);
        odfGenerator.AddHeadlines(wrapper.get_headlines());
        odfGenerator.AddContent(wrapper.get_content());

        odfGenerator.CreateStyle();
        odfGenerator.SetBackgroundColor("#007e8b");
        odfGenerator.SetRowHeight(3, 7);
        odfGenerator.SetStyleToRow(2);

        odfGenerator.CreateStyle();
        odfGenerator.SetBold();
        odfGenerator.SetCursive();
        odfGenerator.SetUnderline();
        odfGenerator.SetBackgroundColor("#ff0000");
        odfGenerator.SetFontSize(30);
        odfGenerator.SetFontColor("#007e8b");
        odfGenerator.SetTextAlign("center");
        odfGenerator.SetStyleToCell(0, 2);
        odfGenerator.SetColumnWidth(2, 8);

        odfGenerator.CreateStyle();
        odfGenerator.SetBackgroundColor("#eaeaea");
        odfGenerator.SetColumnWidth(1, 8);
        odfGenerator.SetStyleToColumn(1);

        odfGenerator.CreateTable("NOcheineTAbewlle");
        odfGenerator.AddHeadlines(wrapper.get_headlines());
        odfGenerator.AddContent(wrapper.get_content());
        odfGenerator.CreateStyle();
        odfGenerator.SetBold();
        odfGenerator.SetCursive();
        odfGenerator.SetUnderline();
        odfGenerator.SetBackgroundColor("#ff0000");
        odfGenerator.SetFontSize(30);
        odfGenerator.SetFontColor("#007e8b");
        odfGenerator.SetTextAlign("center");
        odfGenerator.SetStyleToCell(0, 2);
        odfGenerator.SetColumnWidth(2, 8);

        odfGenerator.CopyToClipboard();

        odfGenerator.SaveFile("C:\\Users\\sstaiger\\Desktop", "test_ods");
    }
}