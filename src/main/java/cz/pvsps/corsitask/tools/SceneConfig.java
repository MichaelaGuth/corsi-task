package cz.pvsps.corsitask.tools;

public record SceneConfig(String fileName, int width, int height, boolean isFullscreen, boolean isExitButtonOverridden) {

    public String getPath() {
        return "/fxml/" + fileName;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public String getFileName() {
        return fileName;
    }

    public boolean isFullscreen() {
        return isFullscreen;
    }

    public boolean isExitButtonOverridden() {
        return isExitButtonOverridden;
    }
    
}
