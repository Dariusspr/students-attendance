package app.studentmanagement.file;

import app.studentmanagement.model.Data;
import app.studentmanagement.model.Student;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;

import java.io.*;
import java.util.Arrays;
import java.util.HashSet;

public class CsvFileHandler implements FileHandler {
    private ObservableList<Student> students = FXCollections.observableArrayList();
    private ObservableList<String> groups = FXCollections.observableArrayList();
    private ObservableList<String> tableHeader = FXCollections.observableArrayList();
    private ObservableList<String[]> tableData = FXCollections.observableArrayList();
    @Override
    public void importData(String filePath) {
        students.clear();
        groups.clear();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                String firstName = parts[0];
                String lastName = parts[1];
                String group = parts[2];
                if (!groups.contains(group))
                    groups.add(group);
                HashSet<String> attendanceDates = new HashSet<>(Arrays.asList(parts).subList(3, parts.length));
                students.add(new Student(firstName, lastName, group, attendanceDates));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void exportData(String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(tableHeader.getFirst());
            for (int i = 1; i < tableHeader.size(); i++) {
                writer.write("," + tableHeader.get(i));
            }
            writer.newLine();
            for (String[] tableRow : tableData) {
                writer.write(tableRow[0]);
                for (int col = 1; col < tableHeader.size(); col++) {
                    writer.write("," + tableRow[col]);
                }
                writer.newLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void saveData(String filePath) {
        students = Data.getInstance().getStudents();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Student s : students) {
                writer.write(s.getFirstName() + "," + s.getLastName() + "," + s.getGroup());
                for (String date : s.getAttendanceDates()) {
                    writer.write("," + date);
                }
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ObservableList<Student> getStudents() {
        return students;
    }

    public void setStudents(ObservableList<Student> students) {
        this.students = students;
    }

    public ObservableList<String> getGroups() {
        return groups;
    }

    public void setGroups(ObservableList<String> groups) {
        this.groups = groups;
    }

    public ObservableList<String> getTableHeader() {
        return tableHeader;
    }

    public void setTableHeader(ObservableList<String> tableHeader) {
        this.tableHeader = tableHeader;
    }

    public ObservableList<String[]> getTableData() {
        return tableData;
    }

    public void setTableData(ObservableList<String[]> tableData) {
        this.tableData = tableData;
    }

}
