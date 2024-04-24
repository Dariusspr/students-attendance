module app.studentmanagement {
    requires javafx.controls;
    requires javafx.fxml;


    opens app.studentmanagement to javafx.fxml;
    exports app.studentmanagement;
}