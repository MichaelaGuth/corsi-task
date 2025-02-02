package cz.pvsps.corsitask;

import cz.pvsps.corsitask.settings.Configuration;
import cz.pvsps.corsitask.tools.Tools;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCombination;
import javafx.stage.Stage;

import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

import static cz.pvsps.corsitask.Constants.*;

/**
 * Main class of the project.
 */
public class Main extends Application {
    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());

    public static Configuration configuration;

    public static Stage stage;

    // TODO create default folders if they dont exist

    /**
     *
     * @param primaryStage
     */
    @Override
    public void start(Stage primaryStage) {
        stage = primaryStage;
        configuration = Tools.loadConfiguration();
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(MENU.getPath())));
            stage.setTitle(APPLICATION_TITLE);
            stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResource(APPLICATION_ICON_FILE_PATH)).toString()));
            stage.setIconified(false);
            stage.setScene(new Scene(root, MENU.getWidth(), MENU.getHeight()));
            stage.setResizable(false);
            stage.setFullScreen(false);
            stage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
            stage.setFullScreenExitHint("");
            stage.show();
            LOGGER.log(Level.INFO, "File named: " + MENU.fileName() + " has been successfully loaded.");
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.log(Level.SEVERE, "File named: " + MENU.fileName() + " could not be loaded." );
            Platform.exit();
            System.exit(100);
        }
    }
}