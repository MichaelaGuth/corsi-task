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
}
