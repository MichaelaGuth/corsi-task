package cz.pvsps.corsitask.corsitest;

import cz.pvsps.corsitask.Constants;
import cz.pvsps.corsitask.tools.Tools;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

import static cz.pvsps.corsitask.Constants.BUTTON_STYLE;
import static cz.pvsps.corsitask.Main.stage;

public class EndTestController {
    public Button endButton;

    public void initialize() {
        stage.setFullScreen(Constants.FxmlFile.END_TEST.isFullscreen());
    }

    public void endButtonOnMouseClicked(MouseEvent event) {
        Tools.changeScene(Constants.FxmlFile.MENU);
    }

    public void endButtonOnMouseEntered(MouseEvent event) {
        endButton.setStyle(String.format(BUTTON_STYLE, "yellow", "white" ));

    }

    public void endButtonOnMouseExited(MouseEvent event) {
        endButton.setStyle(String.format(BUTTON_STYLE, "yellow", "yellow" ));
    }
}
