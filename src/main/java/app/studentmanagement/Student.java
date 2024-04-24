package app.studentmanagement;

import java.util.HashSet;

public class Student {
    private String group;
    private String firstName;
    private String lastName;
    private HashSet<String> attendanceDates = new HashSet<>();


    public Student(String firstName, String lastName, String group, HashSet<String> attendanceDates) {
        this.firstName = firstName;
        this.lastName=  lastName;
        this.group = group;
        this.attendanceDates = attendanceDates;
    }
    public Student (String firstName, String lastName, String group){
        this.firstName = firstName;
        this.lastName=  lastName;
        this.group = group;
    }

    public Student (String firstName, String lastName){
        this.firstName = firstName;
        this.lastName=  lastName;
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

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public HashSet<String> getAttendanceDates() {
        return attendanceDates;
    }

    public void setAttendanceDates(HashSet<String> attendanceDates) {
        this.attendanceDates = attendanceDates;
    }

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
}
