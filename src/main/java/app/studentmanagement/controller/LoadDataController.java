package app.studentmanagement.controller;

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
    void onClickImport() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("csv files", "*.csv"));
        fileChooser.setTitle("Open my student data");
        File selectedFile = fileChooser.showOpenDialog(LoadDataStage.GetInstance());
        if (selectedFile != null) {
            Data.getInstance().loadData(selectedFile.getPath());
        }
        LoadDataStage.GetInstance().close();
    }

}
