package de.hskempten.tabulang.libreOffice;

import de.hskempten.tabulang.datatypes.*;
import de.hskempten.tabulang.libreOffice.Exceptions.OdsExportException;
import de.hskempten.tabulang.libreOffice.Models.MStyle;
import de.hskempten.tabulang.mySql.Models.MSqlTableContent;
import org.odftoolkit.odfdom.dom.style.OdfStyleFamily;
import org.odftoolkit.odfdom.dom.style.props.*;
import org.odftoolkit.odfdom.dom.style.props.OdfTextProperties;
import org.odftoolkit.odfdom.incubator.doc.office.OdfOfficeAutomaticStyles;
import org.odftoolkit.odfdom.incubator.doc.style.OdfStyle;
import org.odftoolkit.simple.SpreadsheetDocument;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Collectors;

public class OdsExportService {
    /* PROPERTIES */
    private SpreadsheetDocument _spreadsheetDocument;
    private OdfOfficeAutomaticStyles _odfOfficeAutomaticStyles;
    private org.odftoolkit.simple.table.Table _initTable;
    private ArrayList<MStyle> _styles;

    /* PUBLIC METHODS */

    /**
     * Constructor: Create a new instance of a table to be exported
     */

    public OdsExportService() throws OdsExportException {
        try {
            _spreadsheetDocument = SpreadsheetDocument.newSpreadsheetDocument();
            _odfOfficeAutomaticStyles = _spreadsheetDocument.getContentDom().getOrCreateAutomaticStyles();
            _initTable = _spreadsheetDocument.getSheetByIndex(0);
            _initTable.setTableName("Table1");
        }
        catch (Exception e) {
            throw new OdsExportException("Cannot create a new spreadsheet object");
        }
    }

    /**
     * Constructor: Create a new instance of a table to be exported
     *
     * @param tableName Specific name of the table in the *.ods-File
     */

    public OdsExportService(String tableName) throws OdsExportException {
        try {
            _spreadsheetDocument = SpreadsheetDocument.newSpreadsheetDocument();
            _odfOfficeAutomaticStyles = _spreadsheetDocument.getContentDom().getOrCreateAutomaticStyles();
            _initTable = _spreadsheetDocument.getSheetByIndex(0);
            _initTable.setTableName(tableName);
        }
        catch (Exception e) {
            throw new OdsExportException("Cannot create a new spreadsheet object with the table name %s".formatted(tableName));
        }
    }

    /**
     * Export a table of the language to an *.ods-File
     *
     * @param table     Table to be exported
     * @param path      Specification of the path where the ods file should be saved
     * @param fileName  File name of the table to be exported (without specifying ods)
     */
    public void Export(Table<? extends InternalObject> table, String path, String fileName){
        try {
            var content = new ArrayList<ArrayList<String>>();
            for(var row : table){
                var contentRows = new ArrayList<String>();
                for(var cell : row){
                    if(cell instanceof InternalDataObject c)
                        contentRows.add(c.getObject().toString());
                    else if(cell instanceof InternalString c)
                        contentRows.add(c.getString());
                    else if(cell instanceof InternalNumber c)
                        contentRows.add(c.getValue().toString());
                    else
                        throw new RuntimeException("Data cannot be put inside an ods file (yet)");
                }
                content.add(contentRows);
            }
            AddHeadlines(table.getColNames().getNames().stream().map(InternalString::getString)
                    .collect(Collectors.toCollection(ArrayList::new))
            );
            AddContentFromTable(content);
            SetRowStylesFromTable(table);
            // TODO what to do about columns and transposed tables
            // SetColumnStylesFromTable(table.getColumnStyles());
            SetCellStylesFromTable(table);
            SaveFile(path, fileName);
        }
        catch(Exception e){
            throw new OdsExportException("Cannot export table %s to %s with file name %s: %s".formatted(table, path, fileName, e.getLocalizedMessage()));
        }
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
            throw new OdsExportException("Cannot add headlines %s to table.".formatted(headlines));
        }
    }

    /**
     * Adds the rows with the associated contents to the table to be exported
     *
     * @param content ArrayList of ArrayList with Strings, which contains all associated contents
     */

    public void AddContent(ArrayList<ArrayList<String>> content){
        try {
            for(var i = 0; i < content.size(); i++){
                for(var j = 0; j < content.get(i).size(); j++){
                    _initTable.getCellByPosition(i, j + 1).setStringValue(String.valueOf(content.get(i).get(j)));
                }
            }
        }
        catch(Exception e){
            throw new OdsExportException("Cannot add content %s to table.".formatted(content));
        }
    }

    /**
     * Create a new instance for a table to be exported
     * @param tableName Specific name of the table in the *.ods-File
     */

    public void CreateTable(String tableName){
        try {
            _initTable = _spreadsheetDocument.addTable();
            _initTable.setTableName(tableName);
        }
        catch(Exception e){
            throw new OdsExportException("Cannot create a new table with table name %s.".formatted(tableName));
        }
    }

    /**
     * Create a new instance to style a row, column or cell of a table
     */

    public void CreateStyle(){
        try {
            this._styles = new ArrayList<>();
        }
        catch(Exception e){
            throw new OdsExportException("Cannot create a new style");
        }
    }

    /**
     * Adds all called up styles to a specific row
     *
     * @param rowIndex  Index of the row in the table (starts with 0)
     */

    public void SetStyleToRow(int rowIndex){
        try {
            var row = _initTable.getRowByIndex(rowIndex);
            for(int i = 0; i < row.getCellCount(); i++){
                SetStyleToCell(row.getRowIndex(), i);
            }
        }
        catch(Exception e){
            throw new OdsExportException("Cannot set style to row %s.".formatted(rowIndex));
        }
    }

    /**
     * Adds all called up styles to a specific column
     *
     * @param columnIndex  Index of the column in the table (starts with 0)
     */

    public void SetStyleToColumn(int columnIndex){
        try {
            var column = _initTable.getColumnByIndex(columnIndex);
            for(int i = 0; i < column.getCellCount(); i++){
                SetStyleToCell(i, column.getColumnIndex());
            }
        }
        catch(Exception e){
            throw new OdsExportException("Cannot set style to column %s.".formatted(columnIndex));
        }
    }

    /**
     * Adds all called up styles to a specific cell
     *
     * @param rowIndex      Index of the row in the table (starts with 0)
     * @param columnIndex   Index of the column in the table (starts with 0)
     */

    public void SetStyleToCell(int rowIndex, int columnIndex){
        try {
            var cell = _initTable.getCellByPosition(columnIndex, rowIndex);
            cell.setCellStyleName(CreateOdfStyle().getStyleNameAttribute());
        }
        catch(Exception e){
            throw new OdsExportException("Cannot set style to cell (row %s, column %s).".formatted(rowIndex, columnIndex));
        }
    }

    /**
     * Add background color to the style-instance
     *
     * @param value Value of the background color as HEX or name (#000000 or 'red')
     */

    public void SetBackgroundColor(String value){
        try {
            this._styles.add(new MStyle(OdfTableCellProperties.BackgroundColor, value));
        }
        catch(Exception e){
            throw new OdsExportException("Cannot set background color to %s.".formatted(value));
        }
    }

    /**
     * Add bold property to the style-instance
     */

    public void SetBold() {
        try {
            this._styles.add(new MStyle(OdfTextProperties.FontWeight, "bold"));
            this._styles.add(new MStyle(OdfTextProperties.FontWeightAsian, "bold"));
            this._styles.add(new MStyle(OdfTextProperties.FontWeightComplex, "bold"));
        }
        catch(Exception e){
            throw new OdsExportException("Cannot set font weight to bold");
        }
    }

    /**
     * Add italic property to the style-instance
     */

    public void SetItalic() {
        try {
            this._styles.add(new MStyle(OdfTextProperties.FontStyle, "italic"));
            this._styles.add(new MStyle(OdfTextProperties.FontStyleAsian, "italic"));
            this._styles.add(new MStyle(OdfTextProperties.FontStyleComplex, "italic"));
        }
        catch(Exception e){
            throw new OdsExportException("Cannot set text to italic");
        }
    }

    /**
     * Add underline property to the style-instance
     */

    public void SetUnderline() {
        try {
            this._styles.add(new MStyle(OdfTextProperties.TextUnderlineStyle, "solid"));
            this._styles.add(new MStyle(OdfTextProperties.TextUnderlineColor, "font-color"));
            this._styles.add(new MStyle(OdfTextProperties.TextUnderlineWidth, "auto"));
        }
        catch(Exception e){
            throw new OdsExportException("Cannot underline text");
        }
    }

    /**
     * Change the font family of the specific region (row, column, cell)
     *
     * @param value Font-Family name as String (e. g. 'Arial')
     */

    public void SetFonFamily(String value){
        try {
            this._styles.add(new MStyle(OdfTextProperties.FontFamily, value));
            this._styles.add(new MStyle(OdfTextProperties.FontFamilyAsian, value));
            this._styles.add(new MStyle(OdfTextProperties.FontFamilyComplex, value));
            this._styles.add(new MStyle(OdfTextProperties.FontFamilyGeneric, value));
            this._styles.add(new MStyle(OdfTextProperties.FontFamilyGenericAsian, value));
            this._styles.add(new MStyle(OdfTextProperties.FontFamilyGenericComplex, value));
        }
        catch(Exception e){
            throw new OdsExportException("Cannot set font family to %s.".formatted(value));
        }
    }

    /**
     * Change the font color of the specific region (row, column, cell)
     *
     * @param value Value of the font color as HEX or name (#000000 or 'red')
     */

    public void SetFontColor(String value){
        try {
            this._styles.add(new MStyle(OdfTextProperties.Color, value));
        }
        catch(Exception e){
            throw new OdsExportException("Cannot set font color to %s.".formatted(value));
        }
    }

    /**
     * Change the font size of the specific region (row, column, cell)
     *
     * @param value Size as double value (e. g. 10)
     */

    public void SetFontSize(double value){
        try {
            this._styles.add(new MStyle(OdfTextProperties.FontSize, String.valueOf(value)));
        }
        catch(Exception e){
            throw new OdsExportException("Cannot set font size to %s.".formatted(value));
        }
    }

    /**
     * Change the column width of a specific column
     *
     * @param columnIndex   Index of the column in the table (starts with 0)
     * @param value         Size of the column (in cm)
     */

    public void SetColumnWidth(int columnIndex, double value){
        try {
            _initTable.getColumnByIndex(columnIndex).setWidth(value * 10);
        }
        catch(Exception e){
            throw new OdsExportException("Cannot set column width to %s".formatted(value * 10));
        }
    }

    /**
     * Change the row height of a specific row
     *
     * @param rowIndex  Index of the row in the table (starts with 0)
     * @param value     Size of the row (in cm)
     */

    public void SetRowHeight(int rowIndex, double value){
        try {
            _initTable.getRowByIndex(rowIndex).setHeight(value * 10, false);
        }
        catch(Exception e){
            throw new OdsExportException("Cannot set row height to %s".formatted(value * 10));
        }
    }

    /**
     * Change the align of specific region (row, column, cell)
     *
     * @param value Align of the text ('left', 'right' or 'center')
     */

    public void SetTextAlign(String value){
        try {
            if(value.equals("right")) { value = "end"; }
            if(value.equals("left")) { value = "start"; }
            this._styles.add(new MStyle(OdfParagraphProperties.TextAlign, value));
        }
        catch(Exception e){
            throw new OdsExportException("Cannot set %s as text align".formatted(value));
        }
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
            throw new OdsExportException("Cannot save %s to %s.".formatted(fileName, path));
        }
    }

    /* PRIVATE METHODS */

    /**
     * Helper function for {@link OdsExportService#Export(Table, String, String)}.
     * Modify the content to the correct form
     *
     * @param content   Contains all associated contents
     */

    private void AddContentFromTable(ArrayList<ArrayList<String>> content){
        try {
            for(var i = 0; i < content.size(); i++){
                for(var j = 0; j < content.get(i).size(); j++){
                    _initTable.getCellByPosition(j, i + 1).setStringValue(String.valueOf(content.get(i).get(j)));
                }
            }
        }
        catch(Exception e){
            throw new OdsExportException("Cannot add content to table.");
        }
    }

    /**
     * Helper function for {@link OdsExportService#SetStyleToCell(int, int)}
     * Create a new OdfStyle-Object as a style instance
     *
     * @return OdfStyle
     */

    private OdfStyle CreateOdfStyle(){
        try {
            var style = _odfOfficeAutomaticStyles.newStyle(OdfStyleFamily.TableCell);
            _styles.forEach(item -> style.setProperty(item.getProperty(), item.getValue()));
            return style;
        }
        catch(Exception e){
            throw new OdsExportException("Cannot create automatic style to table cell.");
        }
    }

    /**
     * Helper function for {@link OdsExportService#Export(Table, String, String)}.
     * Filter the row styles of the table of the language and modify it to the correct form
     *
     * @param table Table with styled tuples
     */

    private void SetRowStylesFromTable(Table<? extends InternalObject> table){
        int rowNum = 0;
        for(var tuple : table) {
            rowNum++;
            if(tuple.getStyle().isEmpty())
                continue;

            CreateStyle();
            WrapperSetStyleToTable(tuple.getStyle(), rowNum, -1);
            SetStyleToRow(rowNum);
        }
    }

    /**
     * Helper function for {@link OdsExportService#Export(Table, String, String)}.
     * Filter the column styles of the table of the language and modify it to the correct form
     *
     * TODO not currently called, table might be transposed or not
     *
     * @param styles Specific style for a column
     */

    private void SetColumnStylesFromTable(HashMap<Integer, de.hskempten.tabulang.datatypes.Style> styles){
        styles.forEach((key, value) -> {
            CreateStyle();
            WrapperSetStyleToTable(value, -1, key);
            SetStyleToColumn(key);
        });
    }

    /**
     * Helper function for {@link OdsExportService#Export(Table, String, String)}.
     * Filter the cell styles of the table of the language and modify it to the correct form
     *
     * @param table Table with cells that have styles applied to them
     */

    private void SetCellStylesFromTable(Table<?> table){
        try {
            int x, y;

            y = -1;
            for(var tuple : table) {
                y++;
                x = -1;

                for(var cell : tuple) {
                    x++;
                    if(cell.getStyle().isEmpty())
                        continue;

                    CreateStyle();
                    WrapperSetStyleToTable(cell.getStyle(), y, x);
                    SetStyleToCell(y+1, x);
                }
            }
        }
        catch(Exception e){
            throw new OdsExportException("Cannot set styles to table %s".formatted(table));
        }
    }

    /**
     * Helper function for:
     * {@link OdsExportService#SetRowStylesFromTable(Table)}
     * {@link OdsExportService#SetColumnStylesFromTable(HashMap)} (HashMap)}
     * {@link OdsExportService#SetCellStylesFromTable(Table)} (HashMap)}
     *
     * Find the correct style methods with the help of keywords
     *
     * @param styles        List of all styles for a region (row, column or cell)
     * @param rowIndex      Index of the row in the table (starts with 0)
     * @param columnIndex   Index of the column in the table (starts with 0)
     */

    private void WrapperSetStyleToTable(de.hskempten.tabulang.datatypes.Style styles, int rowIndex, int columnIndex) {
        try {
            for(var item : styles) {
                var key = item.getKey();
                var value = item.getValue();
                switch (key) {
                    case "font-color" -> SetFontColor(value);
                    case "font-family" -> SetFonFamily(value);
                    case "font-size" -> SetFontSize(Double.parseDouble(value));
                    case "text-align" -> SetTextAlign(value);
                    case "background-color" -> SetBackgroundColor(value);
                    case "bold" -> SetBold();
                    case "italics" -> SetItalic();
                    case "underlined" -> SetUnderline();
                    case "rowheight" -> SetRowHeight(rowIndex, Double.parseDouble(value));
                    case "colwidth" -> SetColumnWidth(columnIndex, Double.parseDouble(value));
                }
            }
        }
        catch(Exception e){
            throw new OdsExportException("Cannot set style to row %s and column %s with style %s.".formatted(rowIndex, columnIndex, styles));
        }
    }
}
