package cz.pvsps.corsitask.menu;

import cz.pvsps.corsitask.Constants;
import cz.pvsps.corsitask.tools.Tools;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import static cz.pvsps.corsitask.Main.stage;


public class MenuController {


    public Button goToSettingsButton;
    public Button startTestButton;
    public Button exitButton;
    public Button showResultsButton;

    @FXML
    public void initialize() {
        stage.setFullScreen(Constants.MENU.isFullscreen());
    }

    public void exitButtonOnAction() {
        Platform.exit();
        System.exit(0);
    }

    public void startTestButtonOnAction()  {
        Tools.changeScene(Constants.TEST_SETTINGS_DIALOG);
    }

    public void goToSettingsButtonOnAction() {
        Tools.changeScene(Constants.SETTINGS);
    }


    public void showResultsButtonOnAction() {
        Tools.changeScene(Constants.TEST_RESULTS_DIALOG);
    }
}
