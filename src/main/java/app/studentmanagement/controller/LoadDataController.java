package app.studentmanagement.controller;

import app.studentmanagement.file.CsvFileHandler;
import app.studentmanagement.file.ExcelFileHandler;
import app.studentmanagement.model.Data;
import app.studentmanagement.stage.LoadDataStage;
import javafx.fxml.FXML;
import javafx.stage.FileChooser;

import java.io.File;

public class LoadDataController {

    @FXML
    public void onClickClose() {
        LoadDataStage.GetInstance().close();
    }

    @FXML
    void onClickImportCsv() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("csv files", "*.csv"));
        fileChooser.setTitle("Open my student data");
        File selectedFile = fileChooser.showOpenDialog(LoadDataStage.GetInstance());
        if (selectedFile != null) {
            CsvFileHandler file = new CsvFileHandler();
            file.importData(selectedFile.getPath());
            Data.getInstance().loadData(file.getStudents(), file.getGroups());
        }
        LoadDataStage.GetInstance().close();
    }

    @FXML
    void onClickImportExl() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("excel files", "*.xls", "*.xlsx"));
        fileChooser.setTitle("Open my student data");
        File selectedFile = fileChooser.showOpenDialog(LoadDataStage.GetInstance());
        if (selectedFile != null) {
            ExcelFileHandler file = new ExcelFileHandler();
            file.importData(selectedFile.getPath());
            Data.getInstance().loadData(file.getStudents(), file.getGroups());
        }
        LoadDataStage.GetInstance().close();
    }

}
