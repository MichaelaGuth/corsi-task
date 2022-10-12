package cz.pvsps.corsitask;

import cz.pvsps.corsitask.tools.SceneConfig;
import cz.pvsps.corsitask.tools.Tools;

public class Constants {

    // FXML files config
    public static final SceneConfig MENU = new SceneConfig ("menu.fxml", 400, 600, false, true);
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
    public static final String BROWSE_OPTION = "Procházet místní soubory";

    public static final String DEFAULT_SEQUENCES_FILE_NAME = "defaultSequences.json";
    public static final double LABEL_FONT_SIZE = 120;

    /*
    public static final int[] SEQUENCE_2_1 = new int[]{8,5};
    public static final int[] SEQUENCE_2_2 = new int[]{6,4};
    public static final int[] SEQUENCE_3_1 = new int[]{4,7,2};
    public static final int[] SEQUENCE_3_2 = new int[]{8,1,5};
    public static final int[] SEQUENCE_4_1 = new int[]{3,4,1,7};
    public static final int[] SEQUENCE_4_2 = new int[]{6,1,5,8};
    public static final int[] SEQUENCE_5_1 = new int[]{5,2,1,8,6};
    public static final int[] SEQUENCE_5_2 = new int[]{4,2,7,3,1};
    public static final int[] SEQUENCE_6_1 = new int[]{3,9,2,4,8,7};
    public static final int[] SEQUENCE_6_2 = new int[]{3,7,8,2,9,4};
    public static final int[] SEQUENCE_7_1 = new int[]{5,9,1,7,4,2,8};
    public static final int[] SEQUENCE_7_2 = new int[]{5,7,9,2,8,4,6};
    public static final int[] SEQUENCE_8_1 = new int[]{5,8,1,9,2,6,4,7};
    public static final int[] SEQUENCE_8_2 = new int[]{5,9,3,6,7,2,4,3};
    public static final int[] SEQUENCE_9_1 = new int[]{5,3,8,7,1,2,4,6,9};
    public static final int[] SEQUENCE_9_2 = new int[]{4,2,6,8,1,7,9,3,5};
     */
}
