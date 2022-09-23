package cz.pvsps.corsitask.menu;

import cz.pvsps.corsitask.Constants;
import cz.pvsps.corsitask.tools.Tools;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;

import java.io.IOException;


public class MenuController {


    public Button goToSettingsButton;
    public Button startTestButton;
    public Button exitButton;
    public Button showResultsButton;

    public void exitButtonOnAction(ActionEvent actionEvent) {
        Platform.exit();
        System.exit(0);
    }

    public void startTestButtonOnAction(ActionEvent actionEvent) throws IOException {
        Tools.changeScene(Constants.FxmlFile.TEST_SETTINGS_DIALOG);
    }

    public void goToSettingsButtonOnAction(ActionEvent actionEvent) throws IOException {
        Tools.changeScene(Constants.FxmlFile.SETTINGS);
    }


    public void showResultsButtonOnAction(ActionEvent actionEvent) {
        Tools.changeScene(Constants.FxmlFile.TEST_RESULTS_DIALOG);
    }
}
