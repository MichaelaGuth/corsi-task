package cz.pvsps.corsitask;

import cz.pvsps.corsitask.tools.SceneConfig;
import cz.pvsps.corsitask.tools.Tools;

public class Constants {

    // FXML files config
    public static final SceneConfig MENU = new SceneConfig ("menu.fxml", 400, 600, false, false);
    public static final SceneConfig TRIAL = new SceneConfig ("corsiTest.fxml", 1220, 820, true, false);
    public static final SceneConfig SETTINGS = new SceneConfig ("settings.fxml", 400, 600, false, true);
    public static final SceneConfig RESULT = new SceneConfig ("result.fxml", 1200, 600, false, true);
    public static final SceneConfig TEST_SETTINGS_DIALOG = new SceneConfig ("testSettingsDialog.fxml", 400, 600, false, true);
    public static final SceneConfig SEQUENCE_RESULT = new SceneConfig ("sequenceResultCopy2.fxml", 510, 410, false, false);
    public static final SceneConfig TEST_RESULTS_DIALOG = new SceneConfig ("testResultsDialog.fxml", 400, 250, false, true);
    public static final SceneConfig TUTORIAL = new SceneConfig ("tutorial.fxml", 800, 450, true, false);
    public static final SceneConfig START_TEST = new SceneConfig ("startTest.fxml", 600, 400, true, false);
    public static final SceneConfig END_TEST = new SceneConfig ("endTest.fxml", 600, 400, true, false);

    // Styling
    public static final String BUTTON_STYLE = "-fx-background-color: %s; -fx-border-color: %s; -fx-border-width: 5;";

    // Paths
    public static final String CONFIGURATION_LOCATION = Tools.getDocumentsPath() + "\\Corsi Test\\settings.json";
    public static final String RESULTS_DIR_PATH = Tools.getDocumentsPath() + "\\Corsi Test\\results";
    public static final String SEQUENCES_DIR_PATH = Tools.getDocumentsPath() + "\\Corsi Test\\sequences";
    public static final String CURRENTLY_USED_SEQUENCES_PATH = SEQUENCES_DIR_PATH + "\\defaultSequences.json";

    // Other
    public static final String APPLICATION_ICON_FILE_PATH = "/png/icon.png";
    public static final String APPLICATION_TITLE = "Corsi Test";
    public static final String BROWSE_OPTION = "Procházet místní soubory";

    // Default files backup location (resources)
    public static final String DEFAULT_SEQUENCES_FILE_PATH = "json/defaultSequences.json";
    public static final String DEFAULT_SETTINGS_FILE_PATH = "json/settings.json";
}
