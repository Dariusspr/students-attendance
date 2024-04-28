package app.studentmanagement;

import app.studentmanagement.stage.LoadDataStage;
import app.studentmanagement.stage.MainStage;
import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage stage){
        stage = MainStage.getStage();
        stage.show();
        LoadDataStage.GetInstance().show();
    }

    public static void main(String[] args) {
        launch();
    }
}