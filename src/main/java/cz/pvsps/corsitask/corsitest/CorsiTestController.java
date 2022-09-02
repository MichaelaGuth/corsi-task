package cz.pvsps.corsitask.corsitest;

import cz.pvsps.corsitask.result.Score;
import cz.pvsps.corsitask.result.SequenceScore;
import cz.pvsps.corsitask.tools.Tools;
import javafx.fxml.FXML;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.stage.Screen;

import java.time.LocalDate;
import java.util.ArrayList;

import static cz.pvsps.corsitask.Constants.BUTTON_STYLE;
import static cz.pvsps.corsitask.Main.configuration;
import static cz.pvsps.corsitask.Main.stage;
import static cz.pvsps.corsitask.dialogs.TestSettingsDialogController.patientName;
import static cz.pvsps.corsitask.dialogs.TestSettingsDialogController.patientSurname;
import static cz.pvsps.corsitask.tools.Tools.saveObjectToJSON;

public class CorsiTestController {
    public Rectangle block1;
    public Rectangle block2;
    public Rectangle block3;
    public Rectangle block4;
    public Rectangle block6;
    public Rectangle block5;
    public Rectangle block7;
    public Rectangle block8;
    public Rectangle block9;
    public AnchorPane anchorPane;
    public BorderPane borderPane;
    public Label labelBlock1;
    public Label labelBlock2;
    public Label labelBlock3;
    public Label labelBlock4;
    public Label labelBlock5;
    public Label labelBlock6;
    public Label labelBlock7;
    public Label labelBlock8;
    public Label labelBlock9;
    public Button confirmSelectionButton;
    public HBox hbox;
    public Button resetSelectionButton;


    private ArrayList<Rectangle> allBlocks;
    private ArrayList<Label> allBlockLabels;

    private ArrayList<ArrayList<Integer>> sequences;

    private boolean testInProgress = false;
    private boolean waitingForUser = false;
    private int sequenceIndex;

    private ArrayList<Integer> userSequence;
    private Score score;
    private SequenceScore currentSequence;
    private SequenceScore lastSequence;

    private final Color YELLOW = Color.web("#f5da0f");
    private final Color BLUE = Color.web("#1f3bff");
    private final Color WHITE = Color.WHITE;
    private final Color BLACK = Color.BLACK;
    private long startTime;
    private long time;

    @FXML
    public void initialize() {
        prepareTest();
        Thread testThread = new Thread(new Runnable() {
            @Override
            public void run() {
                sequenceIndex = 0;
                while (testInProgress) {
                    if (sequenceIndex >= sequences.size()) {
                        testInProgress = false;
                    }
                    if (lastSequence != null && currentSequence != null) {
                        if (lastSequence.correctSequence().size() == currentSequence.correctSequence().size()) {
                            if (!lastSequence.isUserCorrect() && !currentSequence.isUserCorrect()) {
                                testInProgress = false;
                            }
                        }
                    }
                    if (!waitingForUser) {
                        playSequence(sequences.get(sequenceIndex));
                        sequenceIndex++;
                        waitingForUser = true;
                        userSequence = new ArrayList<>();
                        confirmSelectionButton.setDisable(false);
                        setAllBlocksDisable(false);
                        startTime = System.currentTimeMillis();
                    }
                }
                endTest();
            }
        });
        startTest(testThread);
    }

    private void prepareTest() {
        allBlocks = getListOfBlocks();
        allBlockLabels = getListOfBlockLabels();
        setAllBlocksDisable(true);
        setAllLabelsMouseTransparency(true);
        setAllLabelsVisibility(configuration.isShowBlockNumbers());
        userSequence = new ArrayList<>();
        confirmSelectionButton.setDisable(true);
        resetSelectionButton.setDisable(true);
        resetSelectionButton.setVisible(false);
        resize();
        sequences = Tools.loadSequences("sequences.json");
        score = new Score(patientName, patientSurname);
        testInProgress = true;
    }

    private ArrayList<Label> getListOfBlockLabels() {
        ArrayList<Label> labels = new ArrayList<>();
        labels.add(labelBlock1);
        labels.add(labelBlock2);
        labels.add(labelBlock3);
        labels.add(labelBlock4);
        labels.add(labelBlock5);
        labels.add(labelBlock6);
        labels.add(labelBlock7);
        labels.add(labelBlock8);
        labels.add(labelBlock9);
        return labels;
    }

    private ArrayList<Rectangle> getListOfBlocks() {
        ArrayList<Rectangle> blocks = new ArrayList<>();
        blocks.add(block1);
        blocks.add(block2);
        blocks.add(block3);
        blocks.add(block4);
        blocks.add(block5);
        blocks.add(block6);
        blocks.add(block7);
        blocks.add(block8);
        blocks.add(block9);
        return blocks;
    }

    private void setAllLabelsVisibility(boolean value) {
        for (Label label :
                allBlockLabels) {
            label.setVisible(value);
        }
    }

    private void setAllLabelsMouseTransparency(boolean value) {
        for (Label label :
                allBlockLabels) {
            label.setMouseTransparent(value);
        }
    }

    private void startTest(Thread testThread) {
        testThread.setDaemon(true);
        testThread.start();
    }

    private void endTest() {
        // TODO test
        String filePath = configuration.getPathToResultsDir() + "\\";
        if (score.getPatientName().equals("") && score.getPatientSurname().equals("")) {
            filePath = filePath + "Anonym-%i_" + LocalDate.now();
        } else if (!score.getPatientSurname().equals("")) {
            filePath = filePath + score.getPatientSurname() + "_" + score.toString() + "_" + LocalDate.now();
        } else if (!score.getPatientName().equals("")) {
            filePath = filePath + score.getPatientName() + "_" + score.toString() + "_" + LocalDate.now();
        } else {
            filePath = filePath + score.getPatientName() + "-" + score.getPatientSurname() + "_" + score.toString() + "_" + LocalDate.now();
        }
        saveObjectToJSON(score, filePath);
    }

    public void resize() {
        if (stage.isFullScreen()) {
            Rectangle2D resolution = Screen.getPrimary().getBounds();
            double scale = resolution.getHeight() / borderPane.getPrefHeight();
            hbox.setScaleX(scale);
            hbox.setScaleY(scale);
        }
    }

    private void playSequence(ArrayList<Integer> sequence) {
        for (int i:
             sequence) {
            Rectangle block = allBlocks.get(i-1);
            try {
                Thread.sleep(500);
                changeBlockColor(block, YELLOW);
                Thread.sleep(500);
                changeBlockColor(block, BLUE);
            } catch (Exception e) {
                // TODO
                throw new RuntimeException(e);
            }

        }
    }

    private void resetAllLabels() {
        for (int i = 0; i < allBlockLabels.size(); i++) {
            allBlockLabels.get(i).setText(String.valueOf(i+1));
            allBlockLabels.get(i).setTextFill(WHITE);
            allBlockLabels.get(i).setVisible(false);
        }
    }

    private void resetAllBlocks() {
        for (Rectangle block :
                allBlocks) {
            changeBlockColor(block, BLUE);
        }
    }

    private void changeBlockColor(Rectangle block, Paint color) {
        block.setFill(color);
        block.setStroke(color);
    }

    private void changeBlockStrokeColor(Rectangle block, Paint color) {
        block.setStroke(color);
    }

    public void blockAny_OnMouseClicked(MouseEvent event) {
        if (event.getSource() instanceof Rectangle rectangle) {
            if (!rectangle.getFill().equals(YELLOW)) {
                changeBlockColor(rectangle, YELLOW);
                int blockIndex = allBlocks.indexOf(rectangle);
                userSequence.add(blockIndex+1);
                if (configuration.isShowUserSelectedOrderOnBlocks()) {
                    allBlockLabels.get(blockIndex).setText(String.valueOf(userSequence.size()));
                    allBlockLabels.get(blockIndex).setTextFill(BLUE);
                    allBlockLabels.get(blockIndex).setVisible(true);
                }
            }
        }
    }

    public void blockAny_OnMouseEntered(MouseEvent event) {
        if (event.getSource() instanceof Rectangle rectangle) {
            changeBlockStrokeColor(rectangle, WHITE);
        }
    }

    public void blockAny_OnMouseExited(MouseEvent event) {
        if (event.getSource() instanceof Rectangle rectangle) {
            changeBlockStrokeColor(rectangle, rectangle.getFill());
        }
    }

    public void confirmSelectionButtonOnMouseClicked(MouseEvent event) {
        long finishTime = System.currentTimeMillis();
        time = finishTime -startTime;
        currentSequence = new SequenceScore(sequences.get(sequenceIndex-1), userSequence, time);
        score.addSequenceScore(currentSequence);
        if (score.getNumberOfSequences()-2 >= 0) {
            lastSequence = score.getSequencesScores().get(score.getNumberOfSequences()-2);
        }
        prepareForNewUserSequence();
        setAllBlocksDisable(true);
        confirmSelectionButton.setDisable(true);
        waitingForUser = false;
    }

    public void resetSelectionButtonOnMouseClicked(MouseEvent event) {
        prepareForNewUserSequence();
    }

    private void prepareForNewUserSequence() {
        userSequence = new ArrayList<>();
        time = 0;

        resetAllLabels();
        resetAllBlocks();
    }

    private void setAllBlocksDisable(boolean value) {
        for (Rectangle block :
                allBlocks) {
            block.setDisable(value);
        }
    }

    public void resetSelectionButtonOnMouseEntered(MouseEvent event) {
        resetSelectionButton.setStyle(String.format(BUTTON_STYLE, "red", "white"));
    }

    public void resetSelectionButtonOnMouseExited(MouseEvent event) {
        resetSelectionButton.setStyle(String.format(BUTTON_STYLE, "red", "red"));
    }

    public void confirmSelectionButtonOnMouseEntered(MouseEvent event) {
        confirmSelectionButton.setStyle(String.format(BUTTON_STYLE, "green", "white"));
    }

    public void confirmSelectionButtonOnMouseExited(MouseEvent event) {
        confirmSelectionButton.setStyle(String.format(BUTTON_STYLE, "green", "green"));
    }

}
