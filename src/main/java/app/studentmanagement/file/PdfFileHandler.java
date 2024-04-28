package app.studentmanagement.file;

import com.itextpdf.layout.element.IBlockElement;
import com.itextpdf.layout.element.Paragraph;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;

import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Table;

import java.io.IOException;

public class PdfFileHandler implements FileHandler {
    private ObservableList<String> tableHeader = FXCollections.observableArrayList();
    private ObservableList<String[]> tableData = FXCollections.observableArrayList();
    @Override
    public void importData(String filePath) {
        // Empty
    }

    @Override
    public void exportData(String filePath) {
        try {
            PdfDocument pdfDoc = new PdfDocument(new PdfWriter(filePath));
            Document doc = new Document(pdfDoc);
            Table table = new Table(tableHeader.size());

            for (String s : tableHeader) {
                table.addCell(new Cell().add(new Paragraph(s)));
            }
            for (String[] row : tableData) {
                for (String s : row) {
                    table.addCell(new Cell().add(new Paragraph(s)));
                }
            }
            doc.add(table);
            doc.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public ObservableList<String> getTableHeader() {
        return tableHeader;
    }

    public void setTableHeader(ObservableList<String> tableHeader) {
        this.tableHeader = tableHeader;
    }

    public ObservableList<String[]> getTableData() {
        return tableData;
    }

    public void setTableData(ObservableList<String[]> tableData) {
        this.tableData = tableData;
    }
}
