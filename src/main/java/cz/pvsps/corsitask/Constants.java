package cz.pvsps.corsitask;

public class Constants {

    public enum FxmlFile {
        MENU ("menu.fxml", 400, 600),
        TRIAL ("corsiTest.fxml", 1220, 820),
        RESULT ("result.fxml", 0, 0),
        SETTINGS ("settings.fxml", 400, 600);
        private final String name;
        private final int sceneWidth;
        private final int sceneHeight;
        FxmlFile(String fxmlFileName, int sceneWidth, int sceneHeight) {
            this.name = fxmlFileName;
            this.sceneWidth = sceneWidth;
            this.sceneHeight = sceneHeight;
        }
        public String getPath() {
            return "/fxml/" + name;
        }

        public int getSceneHeight() {
            return sceneHeight;
        }

        public int getSceneWidth() {
            return sceneWidth;
        }

        public String getName() {
            return name;
        }
    }

    public static final int SCENE_WIDTH = 4*255+200;
    public static final int SCENE_HEIGHT = 4*205;

    public static final String BUTTON_STYLE = "-fx-background-color: %s; -fx-border-color: %s; -fx-border-width: 5;";
    public static final String TEXT_RED = "red";
    public static final String TEXT_GREEN = "green";
    public static final String TEXT_WHITE = "white";

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
