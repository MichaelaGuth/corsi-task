package cz.pvsps.corsitask;

public class Constants {

    public enum FxmlFile {
        MENU ("menu.fxml"),
        TRIAL ("trial.fxml"),
        RESULT ("result.fxml");
        private final String fxmlFileName;
        FxmlFile(String fxmlFileName) {
            this.fxmlFileName = fxmlFileName;
        }
        public String getPath() {
            return "/fxml/" + fxmlFileName;
        }
    }

    public static final int SCENE_WIDTH = 4*255;
    public static final int SCENE_HEIGHT = 4*205;

    public static final int[] SEQUENCE_1_1 = new int[]{8,5};
    public static final int[] SEQUENCE_1_2 = new int[]{6,4};
    public static final int[] SEQUENCE_2_1 = new int[]{4,7,2};
    public static final int[] SEQUENCE_2_2 = new int[]{8,1,5};
    public static final int[] SEQUENCE_3_1 = new int[]{3,4,1,7};
    public static final int[] SEQUENCE_3_2 = new int[]{6,1,5,8};
    public static final int[] SEQUENCE_4_1 = new int[]{5,2,1,8,6};
    public static final int[] SEQUENCE_4_2 = new int[]{4,2,7,3,1};
    public static final int[] SEQUENCE_5_1 = new int[]{3,9,2,4,8,7};
    public static final int[] SEQUENCE_5_2 = new int[]{3,7,8,2,9,4};
    public static final int[] SEQUENCE_6_1 = new int[]{5,9,1,7,4,2,8};
    public static final int[] SEQUENCE_6_2 = new int[]{5,7,9,2,8,4,6};
    public static final int[] SEQUENCE_7_1 = new int[]{5,8,1,9,2,6,4,7};
    public static final int[] SEQUENCE_7_2 = new int[]{5,9,3,6,7,2,4,3};
    public static final int[] SEQUENCE_8_1 = new int[]{5,3,8,7,1,2,4,6,9};
    public static final int[] SEQUENCE_8_2 = new int[]{4,2,6,8,1,7,9,3,5};
}
