package app.studentmanagement.stage;

import app.studentmanagement.controller.LoadDataController;

public class LoadDataStage extends InitialStage{
    private static LoadDataStage stage;
    private final LoadDataController controller;
    private LoadDataStage() {
        super("loadData-view.fxml", "Import data");
        controller = fxmlLoader.getController();
        setOnCloseRequest(e -> controller.onClickClose());
    }

    public static LoadDataStage GetInstance() {
        return stage == null ? stage = new LoadDataStage() : stage;
    }

}
