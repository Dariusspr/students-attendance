module app.studentmanagement {
    requires javafx.controls;
    requires javafx.fxml;
    requires kernel;
    requires layout;


    opens app.studentmanagement to javafx.fxml;
    exports app.studentmanagement;
    exports app.studentmanagement.file;
    opens app.studentmanagement.file to javafx.fxml;
    exports app.studentmanagement.controller;
    opens app.studentmanagement.controller to javafx.fxml;
    exports app.studentmanagement.stage;
    opens app.studentmanagement.stage to javafx.fxml;
    exports app.studentmanagement.model;
    opens app.studentmanagement.model to javafx.fxml;
}