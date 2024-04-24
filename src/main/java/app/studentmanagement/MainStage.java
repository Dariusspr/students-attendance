package app.studentmanagement;

public class MainStage extends InitialStage {
    private static MainStage stage;

    public static MainStage getStage() {
        return stage == null ? stage = new MainStage() : stage;
    }

    private MainStage() {
        super("main-view.fxml", "Attendance app");
    }
}



