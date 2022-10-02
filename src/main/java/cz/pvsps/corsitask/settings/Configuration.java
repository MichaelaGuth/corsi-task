package cz.pvsps.corsitask.settings;


import com.fasterxml.jackson.annotation.*;
import cz.pvsps.corsitask.Constants;

public class Configuration {

    @JsonProperty("pathToResultsDir")
    private String pathToResultsDir = Constants.RESULTS_DIR_PATH;

    @JsonProperty("pathToSequenceDir")
    private String pathToSequenceDir = Constants.SEQUENCES_DIR_PATH;

    @JsonProperty("showUserSelectedOrderOnBlocks")
    private boolean showUserSelectedOrderOnBlocks = false;

    @JsonProperty("showBlockNumbers")
    private boolean showBlockNumbers = false;

    @JsonProperty("allowResetButton")
    private boolean allowResetButton = false;

    @JsonProperty("allowTutorial")
    private boolean allowTutorial = true;

    @JsonProperty("currentlyInUseSequenceFilePath")
    private String currentlyInUseSequenceFilePath = Constants.CURRENTLY_USED_SEQUENCES_PATH;

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

    public boolean isAllowTutorial() {
        return allowTutorial;
    }

    public void setAllowTutorial(boolean allowTutorial) {
        this.allowTutorial = allowTutorial;
    }
}
