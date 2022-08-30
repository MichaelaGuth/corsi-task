package cz.pvsps.corsitask;


import com.fasterxml.jackson.annotation.*;

public class Configuration {

    @JsonProperty("pathToResultsDir")
    private String pathToResultsDir;

    @JsonProperty("pathToSequenceDir")
    private String pathToSequenceDir;

    @JsonProperty("currentlyInUseSequenceFileName")
    private String currentlyInUseSequenceFileName;

    @JsonProperty("showUserSelectedOrderOnBlocks")
    private boolean showUserSelectedOrderOnBlocks;

    @JsonProperty("showBlockNumbers")
    private boolean showBlockNumbers;

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

    public String getCurrentlyInUseSequenceFileName() {
        return currentlyInUseSequenceFileName;
    }

    public void setCurrentlyInUseSequenceFileName(String currentlyInUseSequenceFileName) {
        this.currentlyInUseSequenceFileName = currentlyInUseSequenceFileName;
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
}
