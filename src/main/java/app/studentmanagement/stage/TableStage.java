package app.studentmanagement.stage;

import app.studentmanagement.controller.TableController;

public class TableStage extends InitialStage{
    private static TableStage stage;
    private final TableController controller;
    private TableStage() {
        super("table-view.fxml", "Generate Table");
        controller = fxmlLoader.getController();
        setOnCloseRequest(e -> controller.onClickClose());
    }

    public static TableStage getInstance() {
        return stage == null ? stage = new TableStage() : stage;
    }

}
