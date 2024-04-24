package app.studentmanagement;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

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


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        groupColumn.setCellValueFactory(new PropertyValueFactory<>("group"));

        tbData.setItems(Data.getInstance().getStudents());
    }
}
