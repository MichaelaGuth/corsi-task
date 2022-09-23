package cz.pvsps.corsitask.result;

import cz.pvsps.corsitask.Constants;
import cz.pvsps.corsitask.tools.Tools;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.io.File;

public class ResultController {
    public Label PacientNameLabel;
    public Label pacientSurnameLabel;
    public TableView table;
    public TableColumn sequenceLengthColumn;
    public TableColumn trialNumberColumn;
    public TableColumn scoreColumn;
    public Label pacientBirthdateLabel;
    public Label totalScoreLabel;
    public Label blockSpanLabel;
    public Label correctTrialsLabel;

    public static File file;

    @FXML
    public void initialize() {
        if (file != null) {

        } else {
            Tools.changeScene(Constants.FxmlFile.MENU);
        }
    }


}
