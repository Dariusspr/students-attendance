package app.studentmanagement;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class StudentCreationController implements Initializable {
    private boolean create = true;
    private Student old = null;
    @FXML
    private ChoiceBox<String> groupOptions;

    @FXML
    private TextField tfFirst;

    @FXML
    private TextField tfLast;

    @FXML
    void OnClickCreate() {
        if (create) {
            Data.getInstance().addStudent(new Student(tfFirst.getText(), tfLast.getText(), groupOptions.getValue()));
        } else {
            if (old == null) {
                return;
            }
            ObservableList<Student> list = Data.getInstance().getStudents();
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).equals(old)) {
                    old.setFirstName(tfFirst.getText());
                    old.setLastName(tfLast.getText());
                    old.setGroup(groupOptions.getValue());
                    list.set(i, old);
                }
            }
            close();
        }
        create = true;
        clear();
    }
    private void clear() {
        tfFirst.clear();
        tfLast.clear();
        groupOptions.setValue("");
    }

    public void close() {
        clear();
        StudentCreationStage.GetInstance().close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> groups = Data.getInstance().getGroups();
        groupOptions.setItems(groups);
        groupOptions.setValue("");
    }

    public void change(Student student) {
        old = student;
        tfFirst.setText(student.getFirstName());
        tfLast.setText(student.getLastName());
        groupOptions.setValue(student.getGroup());
        create = false;
    }
}
