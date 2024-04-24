package app.studentmanagement;

public class GroupCreationStage extends InitialStage{
    private final GroupCreationController controller;

    private GroupCreationStage() {
        super("createGroup-view.fxml", "kurti grupę");
        controller = fxmlLoader.getController();
        setOnCloseRequest(e -> controller.close());
    }

    private static GroupCreationStage stage;

    public static GroupCreationStage GetInstance() {
        return stage == null ? stage = new GroupCreationStage() : stage;
    }

    public void changeCurrent(String current) {
        controller.change(current);

    }

}
