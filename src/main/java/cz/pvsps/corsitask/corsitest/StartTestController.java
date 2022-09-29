package cz.pvsps.corsitask.corsitest;

import cz.pvsps.corsitask.Constants;
import cz.pvsps.corsitask.tools.Tools;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import static cz.pvsps.corsitask.Constants.BUTTON_STYLE;
import static cz.pvsps.corsitask.Main.stage;

public class StartTestController {
    public Button startButton;

    @FXML
    public void initialize() {
        stage.setFullScreen(Constants.FxmlFile.START_TEST.isFullscreen());
    }

    public void startButtonOnMouseClicked() {
        Tools.changeScene(Constants.FxmlFile.TRIAL);
    }

    public void startButtonOnMouseExited() {
        startButton.setStyle(String.format(BUTTON_STYLE, "yellow", "white" ));

    }

    public void startButtonOnMouseEntered() {
        startButton.setStyle(String.format(BUTTON_STYLE, "yellow", "white" ));

    }
}
