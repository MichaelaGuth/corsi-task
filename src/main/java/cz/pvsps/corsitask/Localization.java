package cz.pvsps.corsitask;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Localization {


    //TODO dodělat localizační soubory
    @JsonProperty("startTest")
    private String startTest;

    @JsonProperty("settings")
    private String settings;

    @JsonProperty("exit")
    private String exit;

    @JsonProperty("showResults")
    private String showResults;

    @JsonProperty("start")
    private String start;

    @JsonProperty("end")
    private String end;

    @JsonProperty("name")
    private String name;

    @JsonProperty("surname")
    private String surname;

    @JsonProperty("dateOfBirth")
    private String dateOfBirth;

    @JsonProperty("dateOfTest")
    private String dateOfTest;

    @JsonProperty("blockSpan")
    private String blockSpan;

    @JsonProperty("totalScore")
    private String totalScore;

    @JsonProperty("generateCSV")
    private String generateCSV;

    @JsonProperty("exportToPDF")
    private String exportToPDF;

    @JsonProperty("sumOfScore")
    private String sumOfScore;

    @JsonProperty("patientProfile")
    private String patientProfile;

    @JsonProperty("taskSequence")
    private String taskSequence;

    @JsonProperty("userSequence")
    private String userSequence;

    @JsonProperty("sequenceLength")
    private String sequenceLength;

    @JsonProperty("trial")
    private String trial;

    @JsonProperty("score")
    private String score;

    @JsonProperty("totalTime")
    private String totalTime;

    @JsonProperty("time")
    private String time;

    @JsonProperty("sectionFromTo")
    private String sectionFromTo;

    @JsonProperty("highlightSection")
    private String highlightSection;

    @JsonProperty("task")
    private String task;

    @JsonProperty("basis")
    private String basis;

    @JsonProperty("answer")
    private String answer;

    @JsonProperty("pathToSequenceDir")
    private String pathToSequenceDir;

    @JsonProperty("backToGeneralOverview")
    private String backToGeneralOverview;

    @JsonProperty("pathToResultsDir")
    private String pathToResultsDir;

    @JsonProperty("allowTutorial")
    private String allowTutorial;

    @JsonProperty("showBlockNumbers")
    private String showBlockNumbers;

    @JsonProperty("allowCorrectionButton")
    private String allowCorrectionButton;

    @JsonProperty("resultsNameFormat")
    private String resultsNameFormat;

    @JsonProperty("example")
    private String example;

    @JsonProperty("exampleShort")
    private String exampleShort;

    @JsonProperty("selectSequenceFile")
    private String selectSequenceFile;

    @JsonProperty("browseLocalFiles")
    private String browseLocalFiles;

    @JsonProperty("confirmSelection")
    private String confirmSelection;

    @JsonProperty("testSettings")
    private String testSettings;

    @JsonProperty("sequenceFile")
    private String sequenceFile;

    @JsonProperty("saveSettingsAndGoBackToMenu")
    private String saveSettingsAndGoBackToMenu;

    @JsonProperty("continueToTest")
    private String continueToTest;

    @JsonProperty("startTrial")
    private String startTrial;

    @JsonProperty("showSequence")
    private String showSequence;

    public Localization() {}

    public String getStartTest() {
        return startTest;
    }

    public String getSettings() {
        return settings;
    }

    public String getExit() {
        return exit;
    }

    public String getShowResults() {
        return showResults;
    }

    public String getStart() {
        return start;
    }

    public String getEnd() {
        return end;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getDateOfTest() {
        return dateOfTest;
    }

    public String getBlockSpan() {
        return blockSpan;
    }

    public String getTotalScore() {
        return totalScore;
    }

    public String getGenerateCSV() {
        return generateCSV;
    }

    public String getExportToPDF() {
        return exportToPDF;
    }

    public String getSumOfScore() {
        return sumOfScore;
    }

    public String getPatientProfile() {
        return patientProfile;
    }

    public String getTaskSequence() {
        return taskSequence;
    }

    public String getUserSequence() {
        return userSequence;
    }

    public String getSequenceLength() {
        return sequenceLength;
    }

    public String getTrial() {
        return trial;
    }

    public String getScore() {
        return score;
    }

    public String getTotalTime() {
        return totalTime;
    }

    public String getTime() {
        return time;
    }

    public String getSectionFromTo() {
        return sectionFromTo;
    }

    public String getHighlightSection() {
        return highlightSection;
    }

    public String getTask() {
        return task;
    }

    public String getBasis() {
        return basis;
    }

    public String getAnswer() {
        return answer;
    }

    public String getPathToSequenceDir() {
        return pathToSequenceDir;
    }

    public String getBackToGeneralOverview() {
        return backToGeneralOverview;
    }

    public String getPathToResultsDir() {
        return pathToResultsDir;
    }

    public String getAllowTutorial() {
        return allowTutorial;
    }

    public String getShowBlockNumbers() {
        return showBlockNumbers;
    }

    public String getAllowCorrectionButton() {
        return allowCorrectionButton;
    }

    public String getResultsNameFormat() {
        return resultsNameFormat;
    }

    public String getExample() {
        return example;
    }

    public String getSelectSequenceFile() {
        return selectSequenceFile;
    }

    public String getBrowseLocalFiles() {
        return browseLocalFiles;
    }

    public String getConfirmSelection() {
        return confirmSelection;
    }

    public String getTestSettings() {
        return testSettings;
    }

    public String getSequenceFile() {
        return sequenceFile;
    }

    public String getSaveSettingsAndGoBackToMenu() {
        return saveSettingsAndGoBackToMenu;
    }

    public String getContinueToTest() {
        return continueToTest;
    }

    public String getStartTrial() {
        return startTrial;
    }

    public String getShowSequence() {
        return showSequence;
    }

    public String getExampleShort() {
        return exampleShort;
    }
}
