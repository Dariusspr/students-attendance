package app.studentmanagement.controller;

import app.studentmanagement.file.CsvFileHandler;
import app.studentmanagement.file.ExcelFileHandler;
import app.studentmanagement.file.PdfFileHandler;
import app.studentmanagement.model.Data;
import app.studentmanagement.model.Student;
import app.studentmanagement.stage.TableStage;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;

import java.io.File;
import java.time.LocalDate;

public class TableController {
    private  LocalDate startDate;
    private LocalDate endDate;

    private ObservableList<String[]> tableData = FXCollections.observableArrayList();
    private ObservableList<String> tableHeader = FXCollections.observableArrayList();

    @FXML
    private DatePicker dpEnd;

    @FXML
    private DatePicker dpStart;

    @FXML
    private TableView<String[]> tbTable;

    @FXML
    void onClickExportCsv(MouseEvent event) {
        String path = getPath();
        if (path == null)
            return;
        CsvFileHandler csvFileHandler = new CsvFileHandler();
        csvFileHandler.setTableData(tableData);
        csvFileHandler.setTableHeader(tableHeader);
        csvFileHandler.exportData(path);
        alertExportedSuccess();
    }
    private void alertExportedSuccess() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Data Exported");
        alert.setHeaderText(null);
        alert.setContentText("Data exported successfully!");
        alert.showAndWait();
    }
    @FXML
    private void onClickExportPdf() {
        String path = getPath();
        if (path == null)
            return;
        PdfFileHandler pdfFileHandler = new PdfFileHandler();
        pdfFileHandler.setTableData(tableData);
        pdfFileHandler.setTableHeader(tableHeader);
        pdfFileHandler.exportData(path);
        alertExportedSuccess();
    }

    @FXML
    private  void onClickExportExcel() {
        String path = getPath();
        if (path == null)
            return;
        ExcelFileHandler excelFileHandler = new ExcelFileHandler();
        excelFileHandler.setTableData(tableData);
        excelFileHandler.setTableHeader(tableHeader);
        excelFileHandler.exportData(path);
        alertExportedSuccess();
    }
    private String getPath() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save File");

        File file = fileChooser.showSaveDialog(TableStage.getInstance());
        if (file == null)
            return null;
        return file.getAbsolutePath();
    }
    @FXML
    void onClickShowTable(MouseEvent event) {
        startDate = dpStart.getValue();
        endDate = dpEnd.getValue();
        tbTable.getItems().clear();
        tbTable.getColumns().clear();
        generateTableData();
        createTableView();
    }

    private void generateTableData() {
        tableHeader.clear();
        tableHeader.add("Group");
        tableHeader.add("Student");

        endDate = endDate.plusDays(1);
        for (LocalDate s = startDate; s.isBefore(endDate); s = s.plusDays(1)) {
            tableHeader.add(s.toString());
        }

        tableData.clear();
        // can be filtered list
        ObservableList<Student> students = Data.getInstance().getStudents();

        for (Student s : students) {
            String[] rowData = new String[tableHeader.size()];
            rowData[0] = s.getGroup();
            rowData[1] = s.getName();

            for (int i = 2; i < tableHeader.size(); i++) {
                rowData[i] = s.isPresent(tableHeader.get(i)) ? "+" : "";
            }
            tableData.add(rowData);
        }
    }

    private void createTableView() {
        for (int col = 0; col < tableHeader.size(); col++) {
            TableColumn<String[], String> column = new TableColumn<>(tableHeader.get(col));
            final int colIndex = col;
            column.setCellValueFactory(cellData -> {
                String[] row = cellData.getValue();
                return new SimpleStringProperty(row[colIndex]);
            });
            tbTable.getColumns().add(column);
        }

        tbTable.setItems(tableData);
    }

    private void clear() {
        dpStart.setValue(null);
        dpEnd.setValue(null);
    }

    public void onClickClose() {
        clear();
        TableStage.getInstance().close();
    }
}
