package cz.pvsps.corsitask.tools;


import com.fasterxml.jackson.annotation.*;

public class Configuration {

    @JsonProperty("pathToResultsDir")
    private String pathToResultsDir;

    @JsonProperty("pathToSequenceDir")
    private String pathToSequenceDir;

    @JsonProperty("showUserSelectedOrderOnBlocks")
    private boolean showUserSelectedOrderOnBlocks;

    @JsonProperty("showBlockNumbers")
    private boolean showBlockNumbers;

    @JsonProperty("allowResetButton")
    private boolean allowResetButton;

    @JsonProperty("currentlyInUseSequenceFilePath")
    private String currentlyInUseSequenceFilePath;

    public Configuration() {

    }

    public String getPathToResultsDir() {
        return pathToResultsDir;
    }

    public void setPathToResultsDir(String pathToResultsDir) {
        this.pathToResultsDir = pathToResultsDir;
    }

    public String getPathToSequenceDir() {
        return pathToSequenceDir;
    }

    public void setPathToSequenceDir(String pathToSequenceDir) {
        this.pathToSequenceDir = pathToSequenceDir;
    }

    public boolean isShowUserSelectedOrderOnBlocks() {
        return showUserSelectedOrderOnBlocks;
    }

    public void setShowUserSelectedOrderOnBlocks(boolean showUserSelectedOrderOnBlocks) {
        this.showUserSelectedOrderOnBlocks = showUserSelectedOrderOnBlocks;
    }

    public boolean isShowBlockNumbers() {
        return showBlockNumbers;
    }

    public void setShowBlockNumbers(boolean showBlockNumbers) {
        this.showBlockNumbers = showBlockNumbers;
    }

    public boolean isAllowResetButton() {
        return allowResetButton;
    }

    public void setAllowResetButton(boolean allowResetButton) {
        this.allowResetButton = allowResetButton;
    }

    public String getCurrentlyInUseSequenceFilePath() {
        return currentlyInUseSequenceFilePath;
    }

    public void setCurrentlyInUseSequenceFilePath(String currentlyInUseSequenceFilePath) {
        this.currentlyInUseSequenceFilePath = currentlyInUseSequenceFilePath;
    }
}
