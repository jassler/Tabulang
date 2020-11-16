package de.hskempten.tabulang.libreOffice;

import org.odftoolkit.odfdom.dom.element.OdfStyleBase;
import org.odftoolkit.odfdom.dom.element.office.OfficeSpreadsheetElement;
import org.odftoolkit.odfdom.dom.element.table.TableTableElement;
import org.odftoolkit.odfdom.dom.element.table.TableTableRowElement;
import org.odftoolkit.odfdom.dom.style.OdfStyleFamily;
import org.odftoolkit.odfdom.dom.style.props.*;
import org.odftoolkit.odfdom.incubator.doc.number.OdfNumberDateStyle;
import org.odftoolkit.odfdom.incubator.doc.number.OdfNumberStyle;
import org.odftoolkit.odfdom.incubator.doc.number.OdfNumberTimeStyle;
import org.odftoolkit.odfdom.incubator.doc.office.OdfOfficeAutomaticStyles;
import org.odftoolkit.odfdom.incubator.doc.office.OdfOfficeStyles;
import org.odftoolkit.odfdom.incubator.doc.style.OdfStyle;
import org.odftoolkit.odfdom.pkg.OdfFileDom;
import org.odftoolkit.simple.SpreadsheetDocument;
import org.odftoolkit.simple.table.Cell;
import org.odftoolkit.simple.table.Column;
import org.odftoolkit.simple.table.Row;
import org.odftoolkit.simple.table.Table;
import org.w3c.dom.Node;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;

public class SimpleOds {

    String inputFileName;
    String outputFileName;
    SpreadsheetDocument outputDocument;
    OdfFileDom contentDom;	// the document object model for content.xml
    OdfFileDom stylesDom;	// the document object model for styles.xml
    // the office:automatic-styles element in content.xml
    OdfOfficeAutomaticStyles contentAutoStyles;
    OdfOfficeStyles stylesOfficeStyles;
    String columnStyleName;
    String rowStyleName;
    String headingStyleName;
    String noaaTimeStyleName;
    String noaaDateStyleName;
    String noaaTempStyleName;
    String testStyle;
    OfficeSpreadsheetElement officeSpreadsheet;

    public static void main(String[] args) {
        var blubb = new SimpleOds();
        blubb.start();
    }

    public void start(){
        try {
            outputDocument = SpreadsheetDocument.newSpreadsheetDocument();
            contentDom = outputDocument.getContentDom();
            stylesDom = outputDocument.getStylesDom();
            contentAutoStyles = outputDocument.getContentDom().getOrCreateAutomaticStyles();
            stylesOfficeStyles = outputDocument.getOrCreateDocumentStyles();
            officeSpreadsheet = outputDocument.getContentRoot();

            var sheet = outputDocument.getSheetByIndex(0);
            sheet.setTableName("Blubber");

            var cell = sheet.getCellByPosition(0, 0);
            cell.setStringValue("Betrag");

            var style = contentAutoStyles.newStyle(OdfStyleFamily.TableCell);
            style.setProperty(OdfTableCellProperties.BackgroundColor, "#ff0000");
            setFontWeight(style, "bold");
            style.setStyleNameAttribute("yeah");

            cell.setCellStyleName("yeah");

            sheet.getCellByPosition(1, 0).setDoubleValue(23.0);

            var test = File.createTempFile("odf", ".ods").getPath();
            outputDocument.save(test);
            System.out.println(test);

            System.out.println(sheet.getOwnerDocument().getContentDom().toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void run(String[] args) throws Exception {
        setupOutputDocument();
        if (outputDocument != null) {
            cleanOutDocument();
            addAutomaticStyles();
            processInputDocument();
            saveOutputDocument();
        }
            else {
            System.err.println("Usage: SimpleOds infile outfile");
        }
    }

    void setupOutputDocument() {
        try {
            outputDocument = SpreadsheetDocument.newSpreadsheetDocument();
            contentDom = outputDocument.getContentDom();
            stylesDom = outputDocument.getStylesDom();
            contentAutoStyles = outputDocument.getContentDom().getOrCreateAutomaticStyles();
            stylesOfficeStyles = outputDocument.getOrCreateDocumentStyles();
            officeSpreadsheet = outputDocument.getContentRoot();
        } catch (Exception e) {
            System.err.println("Unable to create output file.");
            System.err.println(e.getMessage());
            outputDocument = null;
        }
    }

    void cleanOutDocument() {
        var childNode = officeSpreadsheet.getFirstChild();
        while (childNode != null) {
            officeSpreadsheet.removeChild(childNode);
            childNode = officeSpreadsheet.getFirstChild();
        }
    }

    void setFontWeight(OdfStyleBase style, String value) {
        style.setProperty(OdfTextProperties.FontWeight, value);
        style.setProperty(OdfTextProperties.FontWeightAsian, value);
        style.setProperty(OdfTextProperties.FontWeightComplex, value);
    }

    void addAutomaticStyles() {

        OdfStyle style;

        style = contentAutoStyles.newStyle(OdfStyleFamily.TableCell);
        testStyle = style.getStyleNameAttribute();
        style.setProperty(OdfTableCellProperties.BackgroundColor, "#ff0000");
        setFontWeight(style, "bold");
        style.setStyleNameAttribute("testStyle");

        // Column style (all columns same width)
        style = contentAutoStyles.newStyle(OdfStyleFamily.TableColumn);
        columnStyleName = style.getStyleNameAttribute();
        style.setProperty(OdfTableColumnProperties.ColumnWidth, "2.5cm");

        // Row style
        style = contentAutoStyles.newStyle(OdfStyleFamily.TableRow);
        rowStyleName = style.getStyleNameAttribute();
        style.setProperty(OdfTableRowProperties.RowHeight, "0.5cm");

        // bold centered cells (for first row)
        style = contentAutoStyles.newStyle(OdfStyleFamily.TableCell);
        headingStyleName = style.getStyleNameAttribute();
        style.setProperty(OdfParagraphProperties.TextAlign, "center");
        setFontWeight(style, "bold");

        // Create the date, time, and temperature styles and add them.
        // The null in OdfNumberDateStyle means "use default calendar system"
        var dateStyle = new OdfNumberDateStyle(contentDom,
                "yyyy-MM-dd", "numberDateStyle", null);
        var timeStyle = new OdfNumberTimeStyle(contentDom,
                "hh:mm:ss", "numberTimeStyle");
        var numberStyle = new OdfNumberStyle(contentDom,
                "#0.00", "numberTemperatureStyle");

        contentAutoStyles.appendChild(dateStyle);
        contentAutoStyles.appendChild(timeStyle);
        contentAutoStyles.appendChild(numberStyle);

        // cell style for Date cells
        style = contentAutoStyles.newStyle(OdfStyleFamily.TableCell);
        noaaDateStyleName = style.getStyleNameAttribute();
        style.setStyleDataStyleNameAttribute("numberDateStyle");

        // and for time cells
        style = contentAutoStyles.newStyle(OdfStyleFamily.TableCell);
        noaaTimeStyleName = style.getStyleNameAttribute();
        style.setStyleDataStyleNameAttribute("numberTimeStyle");

        // and for the temperatures
        style = contentAutoStyles.newStyle(OdfStyleFamily.TableCell);
        noaaTempStyleName = style.getStyleNameAttribute();
        style.setStyleDataStyleNameAttribute("numberTemperatureStyle");
        style.setProperty(OdfParagraphProperties.TextAlign, "right");

    }

    void processInputDocument() throws Exception {
        BufferedReader inReader;    // for reading the file
        String data;                // holds one line of the file
        String[] info;              // holds the split-up data
        Table table;
        Row row;
        Column column;
        Cell cell;

        var element = new TableTableElement(contentDom);
        var rowElement = new TableTableRowElement(contentDom);

        table = Table.getInstance(element);
        table.setTableName("Blubb");

        var test = table.getOwnerDocument().getContentDom().toString();

        var blubb = table.insertRowsBefore(0, 2);

        table.appendColumn().setDefaultCellStyle(contentAutoStyles.getStyle(noaaDateStyleName, OdfStyleFamily.TableCell));
        table.appendColumn().setDefaultCellStyle(contentAutoStyles.getStyle(noaaTimeStyleName, OdfStyleFamily.TableCell));
        table.appendColumn().setDefaultCellStyle(contentAutoStyles.getStyle(noaaTempStyleName, OdfStyleFamily.TableCell));

        /*
        column.setDefaultCellStyle(contentAutoStyles.getStyle(noaaTimeStyleName, OdfStyleFamily.TableCell));
        column.setDefaultCellStyle(contentAutoStyles.getStyle(noaaTempStyleName, OdfStyleFamily.TableCell));
        */

        // fill in the header row
        row = Row.getInstance(rowElement);
        row.setDefaultCellStyle(contentAutoStyles.getStyle(rowStyleName, OdfStyleFamily.TableRow));


        officeSpreadsheet.appendChild((Node) table);
    }

    private String convertToOdfTime(String hourMinuteSecondTime) {
        String[] timeParts = hourMinuteSecondTime.split(":");
        return "PT" + timeParts[0] + "H" +
                timeParts[1] + "M" + timeParts[2] + "S";
    }

    void saveOutputDocument() {
        try {
            outputDocument.save(outputFileName);
        } catch (Exception e) {
            System.err.println("Unable to save document.");
            System.err.println(e.getMessage());
        }
    }

}
