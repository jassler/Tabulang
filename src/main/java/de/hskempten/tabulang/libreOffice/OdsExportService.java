package de.hskempten.tabulang.libreOffice;

import de.hskempten.tabulang.datatypes.Tuple;
import de.hskempten.tabulang.libreOffice.Models.MStyle;
import de.hskempten.tabulang.mySql.Models.MSqlTableContent;
import org.odftoolkit.odfdom.dom.style.OdfStyleFamily;
import org.odftoolkit.odfdom.dom.style.props.*;
import org.odftoolkit.odfdom.dom.style.props.OdfTextProperties;
import org.odftoolkit.odfdom.incubator.doc.office.OdfOfficeAutomaticStyles;
import org.odftoolkit.odfdom.incubator.doc.style.OdfStyle;
import org.odftoolkit.simple.SpreadsheetDocument;
import org.odftoolkit.simple.table.Table;

import java.awt.Point;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class OdsExportService {
    /* PROPERTIES */
    private SpreadsheetDocument _spreadsheetDocument;
    private OdfOfficeAutomaticStyles _odfOfficeAutomaticStyles;
    private Table _initTable;
    private ArrayList<MStyle> _styles;

    /**
     * Constructor: Create a new instance of a table to be exported
     *
     * @param tableName Specific name of the table in the *.ods-File
     */

    /* PUBLIC METHODS */
    public OdsExportService(String tableName){
        try {
            _spreadsheetDocument = SpreadsheetDocument.newSpreadsheetDocument();
            _odfOfficeAutomaticStyles = _spreadsheetDocument.getContentDom().getOrCreateAutomaticStyles();
            _initTable = _spreadsheetDocument.getSheetByIndex(0);
            _initTable.setTableName(tableName);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Export a table of the language to an *.ods-File
     *
     * @param table     Table to be exported
     * @param path      Specification of the path where the ods file should be saved
     * @param fileName  File name of the table to be exported (without specifying ods)
     */

    public void Export(de.hskempten.tabulang.datatypes.Table table, String path, String fileName){
        var content = new ArrayList<ArrayList<String>>();
        for(var item : table){
            var contentRows = new ArrayList<String>();
            for(var cell : (Tuple<String>) item){
                contentRows.add(cell.getData());
            }
            content.add(contentRows);
        }
        AddHeadlines(table.getColNames().getNames());
        AddContentFromTable(content);
        SetRowStylesFromTable(table.getRowStyles());
        SetColumnStylesFromTable(table.getColumnStyles());
        SetCellStylesFromTable(table.getCellStyles());
        SaveFile(path, fileName);
    }

    /**
     * Export a SQL-Statement to an *.ods-File
     *
     * @param sqlTableContent   Object of type MSqlTableContent, which contains all information for export
     * @param path              Specification of the path where the ods file should be saved
     * @param fileName          File name of the table to be exported (without specifying ods)
     */

    public void InstantlyExportToFile(MSqlTableContent sqlTableContent, String path, String fileName){
        AddHeadlines(sqlTableContent.get_headlines());
        AddContent(sqlTableContent.get_content());
        SaveFile(path, fileName);
    }

    /**
     * Add all column headlines to the table to be exported
     *
     * @param headlines ArrayList of string, which contains all headlines (as Strings)
     */

    public void AddHeadlines(ArrayList<String> headlines) {
        try {
            var length = headlines.size();
            for(var i = 0; i < length; i++){
                _initTable.getCellByPosition(i, 0).setStringValue(headlines.get(i));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Adds the rows with the associated contents to the table to be exported
     *
     * @param content ArrayList of ArrayList with Strings, which contains all associated contents
     */

    public void AddContent(ArrayList<ArrayList<String>> content){
        for(var i = 0; i < content.size(); i++){
            for(var j = 0; j < content.get(i).size(); j++){
                _initTable.getCellByPosition(i, j + 1).setStringValue(String.valueOf(content.get(i).get(j)));
            }
        }
    }

    /**
     * Create a new instance for a table to be exported
     * @param tableName Specific name of the table in the *.ods-File
     */

    public void CreateTable(String tableName){
        _initTable = _spreadsheetDocument.addTable();
        _initTable.setTableName(tableName);
    }

    /**
     * Create a new instance to style a row, column or cell of a table
     */

    public void CreateStyle(){
        this._styles = new ArrayList<>();
    }

    /**
     * Adds all called up styles to a specific row
     *
     * @param rowIndex  Index of the row in the table (starts with 0)
     */

    public void SetStyleToRow(int rowIndex){
        var row = _initTable.getRowByIndex(rowIndex);
        for(int i = 0; i < row.getCellCount(); i++){
            SetStyleToCell(row.getRowIndex(), i);
        }
    }

    /**
     * Adds all called up styles to a specific column
     *
     * @param columnIndex  Index of the column in the table (starts with 0)
     */

    public void SetStyleToColumn(int columnIndex){
        var column = _initTable.getColumnByIndex(columnIndex);
        for(int i = 0; i < column.getCellCount(); i++){
            SetStyleToCell(i, column.getColumnIndex());
        }
    }

    /**
     * Adds all called up styles to a specific cell
     *
     * @param rowIndex      Index of the row in the table (starts with 0)
     * @param columnIndex   Index of the column in the table (starts with 0)
     */

    public void SetStyleToCell(int rowIndex, int columnIndex){
        var cell = _initTable.getCellByPosition(columnIndex, rowIndex);
        cell.setCellStyleName(CreateOdfStyle().getStyleNameAttribute());
    }

    /**
     * Add background color to the style-instance
     *
     * @param value Value of the background color as HEX or name (#000000 or 'red')
     */

    public void SetBackgroundColor(String value){
        this._styles.add(new MStyle(OdfTableCellProperties.BackgroundColor, value));
    }

    /**
     * Add bold property to the style-instance
     */

    public void SetBold() {
        this._styles.add(new MStyle(OdfTextProperties.FontWeight, "bold"));
        this._styles.add(new MStyle(OdfTextProperties.FontWeightAsian, "bold"));
        this._styles.add(new MStyle(OdfTextProperties.FontWeightComplex, "bold"));
    }

    /**
     * Add italic property to the style-instance
     */

    public void SetItalic() {
        this._styles.add(new MStyle(OdfTextProperties.FontStyle, "italic"));
        this._styles.add(new MStyle(OdfTextProperties.FontStyleAsian, "italic"));
        this._styles.add(new MStyle(OdfTextProperties.FontStyleComplex, "italic"));
    }

    /**
     * Add underline property to the style-instance
     */

    public void SetUnderline() {
        this._styles.add(new MStyle(OdfTextProperties.TextUnderlineStyle, "solid"));
        this._styles.add(new MStyle(OdfTextProperties.TextUnderlineColor, "font-color"));
        this._styles.add(new MStyle(OdfTextProperties.TextUnderlineWidth, "auto"));
    }

    /**
     * Change the font family of the specific region (row, column, cell)
     *
     * @param value Font-Family name as String (e. g. 'Arial')
     */

    public void SetFontFamliy(String value){
        this._styles.add(new MStyle(OdfTextProperties.FontFamily, value));
        this._styles.add(new MStyle(OdfTextProperties.FontFamilyAsian, value));
        this._styles.add(new MStyle(OdfTextProperties.FontFamilyComplex, value));
        this._styles.add(new MStyle(OdfTextProperties.FontFamilyGeneric, value));
        this._styles.add(new MStyle(OdfTextProperties.FontFamilyGenericAsian, value));
        this._styles.add(new MStyle(OdfTextProperties.FontFamilyGenericComplex, value));
    }

    /**
     * Change the font color of the specific region (row, column, cell)
     *
     * @param value Value of the font color as HEX or name (#000000 or 'red')
     */

    public void SetFontColor(String value){
        this._styles.add(new MStyle(OdfTextProperties.Color, value));
    }

    /**
     * Change the font size of the specific region (row, column, cell)
     *
     * @param value Size as double value (e. g. 10)
     */

    public void SetFontSize(double value){
        this._styles.add(new MStyle(OdfTextProperties.FontSize, String.valueOf(value)));
    }

    /**
     * Change the column width of a specific column
     *
     * @param columnIndex   Index of the column in the table (starts with 0)
     * @param value         Size of the column (in cm)
     */

    public void SetColumnWidth(int columnIndex, double value){
        _initTable.getColumnByIndex(columnIndex).setWidth(value * 10);
    }

    /**
     * Change the row height of a specific row
     *
     * @param rowIndex  Index of the row in the table (starts with 0)
     * @param value     Size of the row (in cm)
     */

    public void SetRowHeight(int rowIndex, double value){
        _initTable.getRowByIndex(rowIndex).setHeight(value * 10, false);
    }

    /**
     * Change the align of specific region (row, column, cell)
     *
     * @param value Align of the text ('left', 'right' or 'center')
     */

    public void SetTextAlign(String value){
        if(value.equals("right")) { value = "end"; }
        if(value.equals("left")) { value = "start"; }
        this._styles.add(new MStyle(OdfParagraphProperties.TextAlign, value));
    }

    /**
     * Save the table instance and export it as an *.ods-File
     *
     * @param path      Specification of the path where the ods file should be saved
     * @param fileName  File name of the table to be exported (without specifying ods)
     */

    public void SaveFile(String path, String fileName){
        try {
            var file = new File(path, fileName + ".ods");
            _spreadsheetDocument.save(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* PRIVATE METHODS */

    /**
     * Helper function for {@link OdsExportService#Export(de.hskempten.tabulang.datatypes.Table, String, String)}.
     * Modify the content to the correct form
     *
     * @param content   Contains all associated contents
     */

    private void AddContentFromTable(ArrayList<ArrayList<String>> content){
        for(var i = 0; i < content.size(); i++){
            for(var j = 0; j < content.get(i).size(); j++){
                _initTable.getCellByPosition(j, i + 1).setStringValue(String.valueOf(content.get(i).get(j)));
            }
        }
    }

    /**
     * Helper function for {@link OdsExportService#SetStyleToCell(int, int)}
     * Create a new OdfStyle-Object as a style instance
     *
     * @return
     */

    private OdfStyle CreateOdfStyle(){
        var style = _odfOfficeAutomaticStyles.newStyle(OdfStyleFamily.TableCell);
        _styles.forEach(item -> style.setProperty(item.getProperty(), item.getValue()));
        return style;
    }

    /**
     * Helper function for {@link OdsExportService#Export(de.hskempten.tabulang.datatypes.Table, String, String)}.
     * Filter the row styles of the table of the language and modify it to the correct form
     *
     * @param styles Specific style for a row
     */

    private void SetRowStylesFromTable(HashMap styles){
        styles.forEach((key, value) -> {
            CreateStyle();
            WrapperSetStyleToTable((de.hskempten.tabulang.datatypes.Style) value, (Integer) key, -1);
            SetStyleToRow((Integer) key);
        });
    }

    /**
     * Helper function for {@link OdsExportService#Export(de.hskempten.tabulang.datatypes.Table, String, String)}.
     * Filter the column styles of the table of the language and modify it to the correct form
     *
     * @param styles Specific style for a column
     */

    private void SetColumnStylesFromTable(HashMap styles){
        styles.forEach((key, value) -> {
            CreateStyle();
            WrapperSetStyleToTable((de.hskempten.tabulang.datatypes.Style) value, -1, (Integer) key);
            SetStyleToColumn((Integer) key);
        });
    }

    /**
     * Helper function for {@link OdsExportService#Export(de.hskempten.tabulang.datatypes.Table, String, String)}.
     * Filter the cell styles of the table of the language and modify it to the correct form
     *
     * @param styles Specific style for a cell
     */

    private void SetCellStylesFromTable(HashMap styles){
        styles.forEach((key, value) -> {
            CreateStyle();
            WrapperSetStyleToTable((de.hskempten.tabulang.datatypes.Style) value, ((Point)key).x, ((Point)key).y);
            SetStyleToCell(((Point)key).x, ((Point)key).y);
        });
    }

    /**
     * Helper function for:
     * {@link OdsExportService#SetRowStylesFromTable(HashMap)}
     * {@link OdsExportService#SetColumnStylesFromTable(HashMap)} (HashMap)}
     * {@link OdsExportService#SetCellStylesFromTable(HashMap)} (HashMap)}
     *
     * Find the correct style methods with the help of keywords
     *
     * @param styles        List of all styles for a region (row, column or cell)
     * @param rowIndex      Index of the row in the table (starts with 0)
     * @param columnIndex   Index of the column in the table (starts with 0)
     */

    private void WrapperSetStyleToTable(de.hskempten.tabulang.datatypes.Style styles, int rowIndex, int columnIndex) {
        styles.forEach(item -> {
            var key = item.getKey();
            var value = item.getValue();
            switch(key){
                case "font-color":
                    SetFontColor(value);
                    break;
                case "font-family":
                    SetFontFamliy(value);
                    break;
                case "font-size":
                    SetFontSize(Double.parseDouble(value));
                    break;
                case "text-align":
                    SetTextAlign(value);
                    break;
                case "background-color":
                    SetBackgroundColor(value);
                    break;
                case "bold":
                    SetBold();
                    break;
                case "italics":
                    SetItalic();
                    break;
                case "underlined":
                    SetUnderline();
                    break;
                case "rowheight":
                    SetRowHeight(rowIndex, Double.parseDouble(value));
                    break;
                case "colwidth":
                    SetColumnWidth(columnIndex, Double.parseDouble(value));
                    break;
            }
        });
    }
}
