package app.studentmanagement.controller;

import app.studentmanagement.model.Data;
import app.studentmanagement.stage.GroupCreationStage;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ResourceBundle;

public class GroupTabController implements Initializable {

    @FXML
    TableView<String> tbData;
    @FXML
    TableColumn<String, String> groupNameCol;
    @FXML
    private void onClickGroupMenu() {
        GroupCreationStage.GetInstance().show();
    }

    @FXML
    private void onClickDelete() {
        String group = tbData.getSelectionModel().getSelectedItem();
        if (group == null)
            return;
        Data.getInstance().updateStudentsGroup(group,"");
        Data.getInstance().getGroups().remove(group);
    }

    @FXML
    private void onClickChange() {
        String group = tbData.getSelectionModel().getSelectedItem();
        if (group == null)
            return;
        GroupCreationStage.GetInstance().changeCurrent(group);
        GroupCreationStage.GetInstance().show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        groupNameCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue()));
        tbData.setItems(Data.getInstance().getGroups());
    }
}
