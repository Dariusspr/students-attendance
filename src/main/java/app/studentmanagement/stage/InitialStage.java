package app.studentmanagement.stage;


import app.studentmanagement.App;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

abstract class InitialStage extends Stage {
    protected  FXMLLoader fxmlLoader;
    InitialStage(String fileName, String stageName) {
        try {
            fxmlLoader = new FXMLLoader(App.class.getResource(fileName));
            Scene scene = new Scene(fxmlLoader.load());

            this.setTitle(stageName);
            this.setScene(scene);
            this.setResizable(false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
