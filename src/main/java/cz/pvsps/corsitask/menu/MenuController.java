package cz.pvsps.corsitask.menu;

import cz.pvsps.corsitask.Constants;
import cz.pvsps.corsitask.Tools;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;


public class MenuController {


    public Button goToSettingsButton;
    public Button startTestButton;
    public Button exitButton;

    public void exitButtonOnAction(ActionEvent actionEvent) {
        Platform.exit();
        System.exit(0);
    }

    public void startTestButtonOnAction(ActionEvent actionEvent) throws IOException {
        Tools.changeScene(Constants.FxmlFile.TRIAL);
    }

    public void goToSettingsButtonOnAction(ActionEvent actionEvent) throws IOException {
        Tools.changeScene(Constants.FxmlFile.SETTINGS);
    }
}
