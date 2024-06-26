package app.studentmanagement.controller;

import app.studentmanagement.model.Data;
import app.studentmanagement.stage.GroupCreationStage;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class GroupCreationController {

    private boolean create = true;
    private String old = null;
    @FXML
    private TextField tfName;

    @FXML
    void onClickCreate() {
        if (create)
            Data.getInstance().addGroup(tfName.getText());
        else {
            if (old == null) {
                return;
            }
            ObservableList<String> list = Data.getInstance().getGroups();
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).equalsIgnoreCase(old)) {
                    list.set(i, tfName.getText());
                }
            }
            Data.getInstance().updateStudentsGroup(old,tfName.getText());
            close();
        }
        create = true;
        tfName.clear();
    }

    public void close() {
        tfName.clear();
        GroupCreationStage.GetInstance().close();
    }

    public void change(String current) {
        create = false;
        tfName.setText(current);
        old = current;
    }

}
