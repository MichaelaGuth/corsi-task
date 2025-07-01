package cz.pvsps.corsitask;

import cz.pvsps.corsitask.tools.FileNameFormat;
import cz.pvsps.corsitask.tools.SceneConfig;
import cz.pvsps.corsitask.tools.Tools;

import java.io.File;
import java.nio.file.Paths;

/**
 * Class contains all constants used in whole project. You can find here paths to fxml files, default settings, styles, etc.
 */
public class Constants {

    // FXML files config
    public static final SceneConfig MENU = new SceneConfig ("menu.fxml", 400, 600, false, false);
    public static final SceneConfig TRIAL = new SceneConfig ("corsiTest.fxml", 1220, 820, true, false);
    public static final SceneConfig SETTINGS = new SceneConfig ("settings.fxml", 500, 600, false, true);
    public static final SceneConfig RESULT = new SceneConfig ("result.fxml", 1050, 600, false, true);
    public static final SceneConfig TEST_SETTINGS_DIALOG = new SceneConfig ("testSettingsDialog.fxml", 500, 650, false, true);
    public static final SceneConfig SEQUENCE_RESULT = new SceneConfig ("sequenceResult.fxml", 850, 530, false, false);
    public static final SceneConfig TEST_RESULTS_DIALOG = new SceneConfig ("testResultsDialog.fxml", 400, 250, false, true);
    public static final SceneConfig TUTORIAL = new SceneConfig ("tutorial.fxml", 800, 450, true, false);
    public static final SceneConfig START_TEST = new SceneConfig ("startTest1.fxml", 1200, 800, true, false);
    public static final SceneConfig END_TEST = new SceneConfig ("endTest.fxml", 600, 400, true, false);
    public static final SceneConfig START_TUTORIAL = new SceneConfig ("startTutorial.fxml", 1200, 800, true, false) ;


    // Styling
    public static final String BUTTON_STYLE = "-fx-background-color: %s; -fx-border-color: %s; -fx-border-width: 5;";

    // Paths
    public static final String LOCALIZATION_LOCATION = Paths.get(Tools.getDocumentsPath(), "Corsi Test", "localization").toString() + File.separator;
    public static final String CONFIGURATION_LOCATION = Paths.get(Tools.getDocumentsPath(), "Corsi Test", "settings.json").toString();
    public static final String RESULTS_DIR_PATH = Paths.get(Tools.getDocumentsPath(), "Corsi Test", "results").toString();
    public static final String SEQUENCES_DIR_PATH = Paths.get(Tools.getDocumentsPath(), "Corsi Test", "sequences").toString();
    public static final String CURRENTLY_USED_SEQUENCES_PATH = Paths.get(SEQUENCES_DIR_PATH , "defaultSequences.json").toString();
    public static final String CURRENTLY_USED_TUTORIAL_SEQUENCES_PATH = Paths.get(SEQUENCES_DIR_PATH, "tutorialSequences.json").toString();

    // Other
    public static final String APPLICATION_ICON_FILE_PATH = "/png/icon.png";
    public static final String APPLICATION_TITLE = "Corsi Test";
    public static final String BROWSE_OPTION = "Procházet místní soubory";
    public static final String EXAMPLE_SHORT = "např.";

    // Default files backup location (resources)
    public static final String DEFAULT_SEQUENCES_FILE_PATH = "/json/defaultSequences.json";
    public static final String DEFAULT_TUTORIAL_SEQUENCE_FILE_PATH = "/json/tutorialSequences.json";

    public static final FileNameFormat DATE_SURNAME_NAME_TIME = new FileNameFormat("<TestDate:yyyy-mm-dd> <Surname> <Name> <TestTime:hh_mm_ss>", "1972-10-02 Corsi Phillip 20_27_12");
    public static final FileNameFormat ID = new FileNameFormat("<Patient ID>", "4270a472-0614-4e54-8e50-2b8eff9cf01e");
    public static final FileNameFormat DATE_ID_TIME = new FileNameFormat("<TestDate:yyyy-mm-dd> <PatientID> <TestTime:hh_mm_ss>", "1972-10-02 4270a472-0614-4e54-8e50-2b8eff9cf01e 20_27_12");

    public static final String SEPARATOR_CSV = ";";

    public static final String CSV_FOLDER_NAME = File.separator + "csv" + File.separator;


    // LOCALIZATION
    public static enum LOCALIZATION {
        ENG("eng.json"), CZE("cze.json");
        private final String fileName;

        LOCALIZATION(String fileName) {
            this.fileName = fileName;
        }

    }
}
