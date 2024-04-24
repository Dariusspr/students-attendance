package app.studentmanagement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Data {
    private static Data data = null;
    private ObservableList<Student> students;
    private ObservableList<String> groups;

    private Data(ObservableList<Student> students, ObservableList<String> groups) {
        this.students = students;
        this.groups = groups;
    }
    private Data() {
        groups = FXCollections.observableArrayList();
        students =  FXCollections.observableArrayList();
    }

    public static Data getInstance() {
        return data == null ? data = new Data() : data;
    }


    public void addStudent(Student student) {
        students.add(student);
    }

    public ObservableList<String> getGroups() {
        return groups;
    }

    public ObservableList<Student> getStudents() {
        return students;
    }

    public void addGroup(String group) {
        groups.add(group);
    }

    public void updateStudents(String oldGroup, String newGroup) {
        for (Student s : students) {
            if (s.getGroup().equalsIgnoreCase(oldGroup)) {
                s.setGroup(newGroup);
            }
        }
    }
}
