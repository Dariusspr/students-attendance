package app.studentmanagement.controller;

import app.studentmanagement.model.Data;
import app.studentmanagement.model.Student;
import app.studentmanagement.stage.TableStage;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class AttendanceTabController implements Initializable {

    private ObservableList<Student> filteredList = FXCollections.observableArrayList();
    private String groupFilter = "";
    private String studentFilter = "";
    private boolean onlyPresentFilter = false;
    private LocalDate date = LocalDate.now();

    @FXML
    private TableColumn<Student, String> groupCol;

    @FXML
    private TableColumn<Student, String> studentCol;

    @FXML
    private TableColumn<Student, Boolean> attendedCol;

    @FXML
    private CheckBox cbAttended;

    @FXML
    private DatePicker dpDate;

    @FXML
    private TableView<Student> tbAttendance;

    @FXML
    private TextField tfGroupFilter;

    @FXML
    private TextField tfStudentFilter;

    @FXML
    void filterByAttendance() {
        onlyPresentFilter = cbAttended.isSelected();
        updateList();
    }

    @FXML
    void filterByDate() {
        date = dpDate.getValue();
        updateList();
    }

    @FXML
    void filterByGroup() {
        groupFilter = tfGroupFilter.getText();
        updateList();
    }

    @FXML
    void filterByStudent() {
        studentFilter = tfStudentFilter.getText();
        updateList();
    }

    private void updateList() {
        filteredList.clear();
        for (Student s : Data.getInstance().getStudents()) {
            boolean add = true;
            if (!groupFilter.isEmpty()) {
                add &= (s.getGroup().contains(groupFilter));
            }
            if (!studentFilter.isEmpty()) {
                add &= (s.getName().contains(studentFilter));
            }
            if (onlyPresentFilter) {
                add &= s.isPresent(date.toString());
            }

            if (add) {
                filteredList.add(s);
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        groupCol.setCellValueFactory(new PropertyValueFactory<>("group"));
        studentCol.setCellValueFactory(new PropertyValueFactory<>("name"));

        attendedCol.setCellFactory(col -> new CheckBoxTableCell<>());
        attendedCol.setCellValueFactory(data -> {
            Student s = data.getValue();
            BooleanProperty property = new SimpleBooleanProperty(s.isPresent(date.toString()));
            property.addListener((observable,oldValue, newValue) -> {
                if (newValue) {
                    s.addAttendanceDate(date.toString());
                } else {
                    s.removeAttendanceDate(date.toString());
                }
            });
            return property;
        });
        //attendedCol.setCellFactory(CheckBoxTableCell.forTableColumn(attendedCol));
        attendedCol.setEditable(true);
        tbAttendance.setEditable(true);

        filteredList.addAll(Data.getInstance().getStudents());
        dpDate.setValue(date);
        tbAttendance.setItems(filteredList);

    }

    @FXML
    private void onClickExport() {
        TableStage.getInstance().show();
    }
}
