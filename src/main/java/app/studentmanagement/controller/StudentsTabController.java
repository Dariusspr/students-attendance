package app.studentmanagement.controller;

import app.studentmanagement.file.CsvFileHandler;
import app.studentmanagement.file.ExcelFileHandler;
import app.studentmanagement.model.Data;
import app.studentmanagement.stage.LoadDataStage;
import app.studentmanagement.model.Student;
import app.studentmanagement.stage.StudentCreationStage;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class StudentsTabController implements Initializable {

    @FXML
    private TableView<Student> tbData;
    @FXML
    private TableColumn<Student, String> firstNameColumn;
    @FXML
    private TableColumn<Student, String> lastNameColumn;
    @FXML
    private TableColumn<Student, String> groupColumn;

    @FXML
    private void onClickStudentMenu() {
        StudentCreationStage.GetInstance().show();
    }

    @FXML
    private void onClickDelete() {
        Student student = tbData.getSelectionModel().getSelectedItem();
        if (student == null)
            return;
        Data.getInstance().getStudents().remove(student);
    }

    @FXML
    private void onClickChange() {
        Student student = tbData.getSelectionModel().getSelectedItem();
        if (student == null)
            return;
        StudentCreationStage stage =  StudentCreationStage.GetInstance();
        stage.change(student);
        stage.show();
    }

    @FXML
    private void onClickImportStage() {
        LoadDataStage.GetInstance().show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        groupColumn.setCellValueFactory(new PropertyValueFactory<>("group"));

        tbData.setItems(Data.getInstance().getStudents());
    }

    @FXML
    private void onClickSaveExcel() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("excel files", "*.xls", "*.xlsx"));
        fileChooser.setTitle("Save my student data");
        File selectedFile = fileChooser.showSaveDialog(LoadDataStage.GetInstance());
        if (selectedFile != null) {
            ExcelFileHandler fileHandler = new ExcelFileHandler();
            fileHandler.saveData(selectedFile.getPath());
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Data saved");
            alert.setHeaderText(null);
            alert.setContentText("Data saved successfully!");
            alert.showAndWait();
        }
    }

    @FXML
    private void onClickSaveCsv() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("csv files", "*.csv"));
        fileChooser.setTitle("Save my student data");
        File selectedFile = fileChooser.showSaveDialog(LoadDataStage.GetInstance());
        if (selectedFile != null) {
            CsvFileHandler fileHandler = new CsvFileHandler();
            fileHandler.saveData(selectedFile.getPath());
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Data saved");
            alert.setHeaderText(null);
            alert.setContentText("Data saved successfully!");
            alert.showAndWait();
        }
    }
}
