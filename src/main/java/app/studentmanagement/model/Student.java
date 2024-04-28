package app.studentmanagement.model;

import javafx.beans.property.SetProperty;
import javafx.beans.property.SimpleSetProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableSet;

import java.util.HashSet;

public class Student {
    private String group;
    private String firstName;
    private String lastName;
    private final SetProperty<String> attendanceDates = new SimpleSetProperty<>(FXCollections.observableSet(new HashSet<>()));
    private String name;

    public Student(String firstName, String lastName, String group, HashSet<String> attendanceDates) {
        this.firstName = firstName;
        this.lastName=  lastName;
        this.group = group;
        this.attendanceDates.set(FXCollections.observableSet(attendanceDates));
        this.name = firstName + " " + lastName;
    }
    public Student (String firstName, String lastName, String group){
        this.firstName = firstName;
        this.lastName=  lastName;
        this.name = firstName + " " + lastName;
        this.group = group;
    }

    public Student (String firstName, String lastName){
        this.firstName = firstName;
        this.lastName=  lastName;
        this.name = firstName + " " + lastName;
    }
    public String getGroup() {
        return group;
    }
    public void setGroup(String group) {
        this.group = group;
    }

    public String getFirstName() {
        return firstName;
    }

    private void updateName() {
        this.name = firstName + " " + lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
        updateName();
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
        updateName();
    }

    public String getName() {
        return name;
    }

    public ObservableSet<String> getAttendanceDates() {
        return attendanceDates.get();
    }

//    public void setAttendanceDates(ObservableSet<String> attendanceDates) {
//        this.attendanceDates = attendanceDates;
//    }

    public void addAttendanceDate(String date) {
        this.attendanceDates.add(date);
    }

    public void removeAttendanceDate(String date) {
        this.attendanceDates.remove(date);
    }

    @Override
    public boolean equals(Object s) {
        if (s instanceof Student) {
            return super.equals(s) &&
                    this.firstName.equals(((Student) s).firstName) &&
                    this.lastName.equals(((Student) s).lastName) &&
                    this.group.equals(((Student)s).group);
        } else {
            return false;
        }
    }

    public boolean isPresent(String date) {
        return attendanceDates.contains(date);
    }
}
