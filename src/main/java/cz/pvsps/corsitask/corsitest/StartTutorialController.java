package cz.pvsps.corsitask.corsitest;

import cz.pvsps.corsitask.Constants;
import cz.pvsps.corsitask.tools.Tools;
import javafx.scene.control.Button;

import static cz.pvsps.corsitask.Constants.BUTTON_STYLE;

public class StartTutorialController {
    public Button startButton;


    public void startButtonOnMouseClicked() {
        Tools.changeScene(Constants.TUTORIAL);
    }

    public void startButtonOnMouseExited() {
        startButton.setStyle(String.format(BUTTON_STYLE, "yellow", "yellow" ));

    }

    public void startButtonOnMouseEntered() {
        startButton.setStyle(String.format(BUTTON_STYLE, "yellow", "white" ));

    }
}
