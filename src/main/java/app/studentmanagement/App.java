package app.studentmanagement;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {

    @Override
    public void start(Stage stage){
        stage = MainStage.getStage();
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}