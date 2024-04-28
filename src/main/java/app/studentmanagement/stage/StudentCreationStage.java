package app.studentmanagement.stage;

import app.studentmanagement.model.Student;
import app.studentmanagement.controller.StudentCreationController;

public class StudentCreationStage extends InitialStage {
    private static StudentCreationStage stage;
    private final StudentCreationController controller;
    public static StudentCreationStage GetInstance() {
        return stage == null ? stage = new StudentCreationStage() : stage;
    }

    private StudentCreationStage() {
        super("createStudent-view.fxml", "Kurti studentą");
        controller = fxmlLoader.getController();
        setOnCloseRequest(e -> controller.close());
    }

    public void change(Student student) {
        controller.change(student);
    }
}
