package cz.pvsps.corsitask.corsitest;

import cz.pvsps.corsitask.Constants;
import cz.pvsps.corsitask.result.Score;
import cz.pvsps.corsitask.result.SequenceScore;
import cz.pvsps.corsitask.tools.Block;
import cz.pvsps.corsitask.tools.Tools;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
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
import javafx.util.Duration;

import java.io.File;
import java.util.ArrayList;

import static cz.pvsps.corsitask.Constants.BUTTON_STYLE;
import static cz.pvsps.corsitask.Main.configuration;
import static cz.pvsps.corsitask.Main.stage;
import static cz.pvsps.corsitask.settings.TestSettingsDialogController.*;
import static cz.pvsps.corsitask.tools.Tools.saveObjectToJSONFile;

/**
 * This class controls whole Corsi task testing process. It is responsible for displaying the sequence to the user,
 * recording the sequence created by the user, evaluating the inputs and terminating the testing.
 */
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

    private ArrayList<ArrayList<Block>> sequences;

    private boolean testInProgress = false;
    private boolean waitingForUser = false;
    private int sequenceIndex;

    private ArrayList<Block> userSequence;
    private Score score;
    private SequenceScore lastSequence;

    private final Color YELLOW = Color.web("#f5da0f");
    private final Color BLUE = Color.web("#1f3bff");
    private final Color WHITE = Color.WHITE;
    private long startTime;
    private long time;

    private long lastBlockClickTime;

    private ArrayList<Long> timesBetweenBlockClicks;

    /**
     * Initialize and prepare UI for test and start the logic in a new thread.
     */
    @FXML
    public void initialize() {
        prepareTest();
        Thread testThread = new Thread(new Runnable() {
            @Override
            public void run() {
                sequenceIndex = 0;
                while (testInProgress) {
                    if (!waitingForUser) {
                        if (sequenceIndex >= sequences.size()) {
                            testInProgress = false;
                            waitingForUser = true;
                        } else {
                            playSequence(sequences.get(sequenceIndex));
                            sequenceIndex++;
                            waitingForUser = true;
                            userSequence = new ArrayList<>();
                            confirmSelectionButton.setDisable(false);
                            setAllBlocksDisable(false);
                            timesBetweenBlockClicks = new ArrayList<>();
                            startTime = System.currentTimeMillis();
                            lastBlockClickTime = startTime;
                        }
                    }
                }
                endTest();
            }
        });
        startTest(testThread);
    }

    /**
     * Set up thread as daemon and run it.
     * @param testThread Thread that will be run.
     */
    private void startTest(Thread testThread) {
        testThread.setDaemon(true);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        testThread.start();
    }

    /**
     * Saves score achieved by the user and switch into "End test" scene.
     */
    private void endTest() {
        String filePath = configuration.getPathToResultsDir() + File.separator;
        String fileName = configuration.getResultFileNameFormat().getFileName(score);
        String directory = configuration.getResultFileNameFormat().getDirectoryName(score);
        filePath += fileName + File.separator + fileName + ".json";
        saveObjectToJSONFile(score, filePath);
        Platform.runLater(() -> Tools.changeScene(Constants.END_TEST));
    }

    /**
     * Gradually shows user a sequence of blocks.
     * @param sequence Array of {@link Block} that should be highlighted.
     */
    private void playSequence(ArrayList<Block> sequence) {
        for (Block block:
             sequence) {
            Rectangle rectangle = allBlocks.get(block.number()-1);
            try {
                Thread.sleep(500);
                changeBlockColor(rectangle, YELLOW);
                Thread.sleep(500);
                changeBlockColor(rectangle, BLUE);
            } catch (Exception e) {
                // TODO
                throw new RuntimeException(e);
            }

        }
    }

    /**
     * Trigger of mouse click event on a block. Highlights for a short duration clicked block and
     * adds it to the user's list of clicked blocks with the duration since the last block was clicked.
     * @param event {@link MouseEvent} arguments
     */
    public void blockAny_OnMouseClicked(MouseEvent event) {
        if (event.getSource() instanceof Rectangle rectangle) {
            if (!rectangle.getFill().equals(YELLOW)) {
                changeBlockColor(rectangle, YELLOW);
                PauseTransition pause = new PauseTransition(Duration.seconds(0.5));
                pause.setOnFinished(actionEvent -> {
                    changeBlockColor(rectangle,BLUE);
                });
                pause.play();
                int blockIndex = allBlocks.indexOf(rectangle);
                userSequence.add(new Block(blockIndex+1));
                timesBetweenBlockClicks.add(System.currentTimeMillis()-lastBlockClickTime);
                lastBlockClickTime = System.currentTimeMillis();
            }
        }
    }

    /**
     * Ends selecting blocks to the user's sequence and evaluate it
     */
    public void confirmSelectionButtonOnMouseClicked() {
        long finishTime = System.currentTimeMillis();
        timesBetweenBlockClicks.add(System.currentTimeMillis()-lastBlockClickTime);
        time = finishTime -startTime;
        if (score.getNumberOfSequences() > 0) {
            lastSequence = score.getSequencesScores().get(score.getNumberOfSequences()-1);
        }
        SequenceScore currentSequence = new SequenceScore(sequences.get(sequenceIndex - 1), userSequence, time, timesBetweenBlockClicks);
        if (lastSequence != null)  {
            if (lastSequence.getCorrectSequence().size() == currentSequence.getCorrectSequence().size()) {
                currentSequence.setTrialNumber(2);
            }
        }
        if (lastSequence != null) {
            if (lastSequence.getCorrectSequence().size() == currentSequence.getCorrectSequence().size()) {
                if (!lastSequence.isUserCorrect() && !currentSequence.isUserCorrect()) {
                    testInProgress = false;
                    waitingForUser = true;
                }
            }
        }
        score.addSequenceScore(currentSequence);
        prepareForNewUserSequence();
        setAllBlocksDisable(true);
        confirmSelectionButton.setDisable(true);
        waitingForUser = false;
    }

    /**
     * Reset current sequence of selected blocks
     */
    public void resetSelectionButtonOnMouseClicked() {
        prepareForNewUserSequence();
        if (sequenceIndex > 0) {
            sequenceIndex--;
        }
        waitingForUser = false;
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
        sequences = Tools.loadSequences(configuration.getCurrentlyInUseSequenceFilePath());
        score = new Score(patientName, patientSurname, patientBirthdate, patientID);
        testInProgress = true;
    }

    public void resize() {
        if (stage.isFullScreen()) {
            Rectangle2D resolution = Screen.getPrimary().getBounds();
            double scale = resolution.getHeight() / borderPane.getPrefHeight();
            hbox.setScaleX(scale);
            hbox.setScaleY(scale);
        }
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

    private void resetAllLabels() {
        for (int i = 0; i < allBlockLabels.size(); i++) {
            allBlockLabels.get(i).setText(String.valueOf(i+1));
            allBlockLabels.get(i).setTextFill(WHITE);
            allBlockLabels.get(i).setVisible(configuration.isShowBlockNumbers());
        }
    }

    private void resetAllBlocks() {
        for (Rectangle block :
                allBlocks) {
            changeBlockColor(block, BLUE);
        }
    }

    private void changeBlockStrokeColor(Rectangle block, Paint color) {
        block.setStroke(color);
    }

    private void changeBlockColor(Rectangle block, Paint color) {
        block.setFill(color);
        block.setStroke(color);
    }

    public void resetSelectionButtonOnMouseEntered() {
        resetSelectionButton.setStyle(String.format(BUTTON_STYLE, "red", "white"));
    }

    public void resetSelectionButtonOnMouseExited() {
        resetSelectionButton.setStyle(String.format(BUTTON_STYLE, "red", "red"));
    }

    public void confirmSelectionButtonOnMouseEntered() {
        confirmSelectionButton.setStyle(String.format(BUTTON_STYLE, "green", "white"));
    }

    public void confirmSelectionButtonOnMouseExited() {
        confirmSelectionButton.setStyle(String.format(BUTTON_STYLE, "green", "green"));
    }

    public void blockAny_OnMouseEntered(MouseEvent event) {
        if (event.getSource() instanceof Rectangle rectangle) {
            changeBlockStrokeColor(rectangle, WHITE);
        }
    }

    public void blockAny_OnMouseExited(MouseEvent event) {
        if (event.getSource() instanceof Rectangle rectangle) {
            changeBlockStrokeColor(rectangle, rectangle.getFill());
            //changeBlockColor(rectangle, rectangle.getFill());
        }
    }

}
