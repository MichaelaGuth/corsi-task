package cz.pvsps.corsitask.corsitest;

import cz.pvsps.corsitask.Constants;
import cz.pvsps.corsitask.animations.Pulse;
import cz.pvsps.corsitask.animations.Shake;
import cz.pvsps.corsitask.result.SequenceScore;
import cz.pvsps.corsitask.tools.Block;
import cz.pvsps.corsitask.tools.Tools;
import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.stage.Screen;
import javafx.util.Duration;

import java.nio.file.Paths;
import java.util.ArrayList;

import static cz.pvsps.corsitask.Constants.BUTTON_STYLE;
import static cz.pvsps.corsitask.Main.configuration;
import static cz.pvsps.corsitask.Main.stage;

public class TutorialController {
    public Rectangle block1;
    public Rectangle block2;
    public Rectangle block4;
    public Rectangle block3;
    public Button confirmSelectionButton;
    public Label resultLabel;
    public Button continueToTestButton;
    public Button playTutorialButton;
    public BorderPane borderPane;
    public VBox vBox;
    public HBox hBox;

    private ArrayList<Rectangle> allBlocks;

    private ArrayList<ArrayList<Block>> sequences;

    private ArrayList<Block> correctSequence;
    private ArrayList<Block> userSequence;

    private int numberOfCorrectlyAnsweredTrials;

    private boolean isContinueButtonDisabled;

    private int sequenceIndex;

    @FXML
    public void initialize() {
        stage.setFullScreen(Constants.TUTORIAL.isFullscreen());
        prepareTutorial();
        Thread checkForContinueButtonThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (isContinueButtonDisabled) {
                    if (numberOfCorrectlyAnsweredTrials >= 3) {
                        continueToTestButton.setVisible(true);
                        continueToTestButton.setDisable(false);
                        isContinueButtonDisabled = false;
                    }
                }
            }
        });
        checkForContinueButtonThread.setDaemon(true);
        checkForContinueButtonThread.start();
    }

    private void prepareTutorial() {
        resize();
        allBlocks = getListOfBlocks();
        setAllBlocksDisable(true);
        userSequence = new ArrayList<>();
        sequences = Tools.loadSequences(Paths.get(configuration.getPathToSequenceDir(), "tutorialSequences.json").toString());
        continueToTestButton.setDisable(true);
        continueToTestButton.setVisible(false);
        numberOfCorrectlyAnsweredTrials = 0;
        isContinueButtonDisabled = true;
        sequenceIndex = 0;
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
                changeBlockColor(rectangle, Color.YELLOW);
                Thread.sleep(500);
                changeBlockColor(rectangle, Color.BLUE);
            } catch (Exception e) {
                // TODO
                throw new RuntimeException(e);
            }

        }
    }

    public void confirmSelectionButtonOnMouseClicked() {
        SequenceScore sequenceScore = new SequenceScore(correctSequence, userSequence, 0);
        final String RESULT_LABEL_START = "VÝSLEDEK: ";
        if (sequenceScore.isUserCorrect()) {
            resultLabel.setText(RESULT_LABEL_START + "SPRÁVNĚ");
            String RIGHT_ANSWER_STYLE = "-fx-background-color: greenyellow; -fx-text-fill: black;";
            resultLabel.setStyle(RIGHT_ANSWER_STYLE);
            new Pulse(resultLabel).play();
            numberOfCorrectlyAnsweredTrials++;
        } else {
            resultLabel.setText(RESULT_LABEL_START + "ŠPATNĚ");
            String WRONG_ANSWER_STYLE = "-fx-background-color: red; -fx-text-fill: white;";
            resultLabel.setStyle(WRONG_ANSWER_STYLE);
            new Shake(resultLabel).play();
        }
        playTutorialButton.setDisable(false);
        confirmSelectionButton.setDisable(true);
        setAllBlocksDisable(true);
        userSequence = new ArrayList<>();
    }

    public void playTutorialButtonOnMouseClicked() {
        Thread tutorialThread = new Thread(() -> {
            confirmSelectionButton.setDisable(true);
            playSequence(sequences.get(sequenceIndex));
            correctSequence = sequences.get(sequenceIndex);
            if (sequenceIndex >= 3) {
                sequenceIndex = 0;
            } else {
                sequenceIndex++;
            }
            confirmSelectionButton.setDisable(false);
            setAllBlocksDisable(false);
            playTutorialButton.setDisable(true);
        });
        tutorialThread.setDaemon(true);
        tutorialThread.start();
    }

    public void continueToTestButtonOnMouseClicked() {
        Tools.changeScene(Constants.START_TEST);
    }

    public void blockAny_OnMouseClicked(MouseEvent event) {
        if (event.getSource() instanceof Rectangle rectangle) {
            changeBlockColor(rectangle, Color.YELLOW);
            PauseTransition pause = new PauseTransition(Duration.seconds(0.5));
            pause.setOnFinished(actionEvent -> {
                changeBlockColor(rectangle,Color.BLUE);
            });
            pause.play();
            int blockIndex = allBlocks.indexOf(rectangle);
            userSequence.add(new Block(blockIndex+1));
        }
    }

    public void blockAny_OnMouseEntered(MouseEvent event) {
        if (event.getSource() instanceof Rectangle rectangle) {
            changeBlockStrokeColor(rectangle, Color.WHITE);
        }
    }

    public void blockAny_OnMouseExited(MouseEvent event) {
        if (event.getSource() instanceof Rectangle rectangle) {
            changeBlockStrokeColor(rectangle, rectangle.getFill());
        }
    }

    public void confirmSelectionButtonOnMouseEntered() {
        confirmSelectionButton.setStyle(String.format(BUTTON_STYLE, "green", "white" ));
    }

    public void confirmSelectionButtonOnMouseExited() {
        confirmSelectionButton.setStyle(String.format(BUTTON_STYLE, "green", "green"));
    }

    public void playTutorialButtonOnMouseEntered() {
        playTutorialButton.setStyle(String.format(BUTTON_STYLE, "orange", "white" ));
    }

    public void playTutorialButtonOnMouseExited() {
        playTutorialButton.setStyle(String.format(BUTTON_STYLE, "orange", "orange" ));
    }

    public void continueToTestButtonOnMouseExited() {
        continueToTestButton.setStyle(String.format(BUTTON_STYLE, "yellow", "yellow" ));
    }

    public void continueToTestButtonOnMouseEntered() {
        continueToTestButton.setStyle(String.format(BUTTON_STYLE, "yellow", "white" ));
    }

    private void changeBlockStrokeColor(Rectangle block, Paint color) {
        block.setStroke(color);
    }

    private void changeBlockColor(Rectangle block, Paint color) {
        block.setFill(color);
        block.setStroke(color);
    }

    private void setAllBlocksDisable(boolean value) {
        for (Rectangle block :
                allBlocks) {
            block.setDisable(value);
        }
    }

    private ArrayList<Rectangle> getListOfBlocks() {
        ArrayList<Rectangle> blocks = new ArrayList<>();
        blocks.add(block1);
        blocks.add(block2);
        blocks.add(block3);
        blocks.add(block4);
        return blocks;
    }

    public void resize() {
        if (stage.isFullScreen()) {
            Rectangle2D resolution = Screen.getPrimary().getBounds();
            double scale = resolution.getHeight() / borderPane.getPrefHeight();
            vBox.setScaleX(scale);
            vBox.setScaleY(scale);
        }
    }

}
