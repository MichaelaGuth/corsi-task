package cz.pvsps.corsitask;

import cz.pvsps.corsitask.tools.Configuration;
import cz.pvsps.corsitask.tools.Tools;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import static cz.pvsps.corsitask.Constants.FxmlFile;

public class Main extends Application {

    private static Logger LOGGER = Logger.getLogger(Main.class.getName());

    public static Configuration configuration;

    public static Stage stage;
    @Override
    public void start(Stage primaryStage) {
        stage = primaryStage;
        configuration = Tools.loadConfiguration();
        try {
            Parent root = FXMLLoader.load(getClass().getResource(FxmlFile.MENU.getPath()));      // menu.fxml

            stage.setTitle("Corsi Test");
            // TODO stage.getIcons().add();
            stage.setScene(new Scene(root, FxmlFile.MENU.getSceneWidth(), FxmlFile.MENU.getSceneHeight()));
            stage.setResizable(false);
            stage.show();

            LOGGER.log(Level.INFO, "File named: menu.fxml has been successfully loaded.");

        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "File named: " + " could not be loaded." );
        }
    }
}