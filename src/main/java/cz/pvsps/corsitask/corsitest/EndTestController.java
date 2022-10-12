package cz.pvsps.corsitask.corsitest;

import cz.pvsps.corsitask.Constants;
import cz.pvsps.corsitask.tools.Tools;
import javafx.scene.control.Button;

import static cz.pvsps.corsitask.Constants.BUTTON_STYLE;
import static cz.pvsps.corsitask.Main.stage;

public class EndTestController {
    public Button endButton;

    public void initialize() {
        stage.setFullScreen(Constants.END_TEST.isFullscreen());
    }

    public void endButtonOnMouseClicked() {
        Tools.changeScene(Constants.MENU);
    }

    public void endButtonOnMouseEntered() {
        endButton.setStyle(String.format(BUTTON_STYLE, "yellow", "white" ));

    }

    public void endButtonOnMouseExited() {
        endButton.setStyle(String.format(BUTTON_STYLE, "yellow", "yellow" ));
    }
}
