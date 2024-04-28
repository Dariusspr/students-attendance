package app.studentmanagement.controller;

import app.studentmanagement.App;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Objects;
import java.util.ResourceBundle;


public class MainController implements  Initializable {
    @FXML
    private BorderPane borderPane;

    private int selectedView = 0;
    private final int STUDENTS_VIEW = 1;
    private final int GROUPS_VIEW = 2;
    private final int VIEW_VIEW = 4;

    private static final HashMap<Integer, String> scenes = new HashMap<>();

    private static Node getView(int view) {
        Node pane = null;
        try {
            pane = FXMLLoader.load(Objects.requireNonNull(App.class.getResource(scenes.get(view))));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return pane;
    }

    @FXML
    private void changeSceneStudents() {
        if ((selectedView & STUDENTS_VIEW) != STUDENTS_VIEW) {
            changeScene(STUDENTS_VIEW);
        }
    }
    @FXML
    private void changeSceneGroups() {
        if ((selectedView & GROUPS_VIEW) != GROUPS_VIEW) {
            changeScene(GROUPS_VIEW);
        }
    }
    @FXML
    private void changeSceneView() {
        if ((selectedView & VIEW_VIEW) != VIEW_VIEW) {
            changeScene(VIEW_VIEW);
        }
    }

    private void changeScene(int view) {
        selectedView = 0;
        selectedView |= view;
        borderPane.setCenter(getView(view));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        scenes.put(STUDENTS_VIEW, "students-view.fxml");
        scenes.put(GROUPS_VIEW, "groups-view.fxml");
        scenes.put(VIEW_VIEW, "attendance-view.fxml");
        changeSceneStudents();
    }
}