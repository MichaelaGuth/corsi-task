package cz.pvsps.corsitask;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCombination;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import static cz.pvsps.corsitask.Constants.FxmlFile;
import static cz.pvsps.corsitask.Constants.SCENE_HEIGHT;
import static cz.pvsps.corsitask.Constants.SCENE_WIDTH;

public class Main extends Application {

    private static Logger LOGGER = Logger.getLogger(Main.class.getName());

    public static Stage stage;
    @Override
    public void start(Stage primaryStage) {
        stage = primaryStage;
        try {
            Parent root = FXMLLoader.load(getClass().getResource(FxmlFile.MENU.getPath()));      // menu.fxml

            stage.setTitle("Corsi Test");
            //primaryStage.getIcons().add(ImageLoader.loadImage(ICON));
            stage.setScene(new Scene(root, FxmlFile.MENU.getSceneWidth(), FxmlFile.MENU.getSceneHeight()));
            stage.setResizable(false);
            stage.setFullScreen(false);
            stage.setFullScreenExitHint("");
            stage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
            stage.show();

            LOGGER.log(Level.INFO, "File named: menu.fxml has been successfully loaded.");

        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "File named: " + " could not be loaded." );
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}