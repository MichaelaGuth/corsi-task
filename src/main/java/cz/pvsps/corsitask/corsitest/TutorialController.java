package cz.pvsps.corsitask.corsitest;

import cz.pvsps.corsitask.Constants;
import cz.pvsps.corsitask.result.SequenceScore;
import cz.pvsps.corsitask.tools.Block;
import cz.pvsps.corsitask.tools.Tools;
import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.util.ArrayList;

import static cz.pvsps.corsitask.Constants.BUTTON_STYLE;
import static cz.pvsps.corsitask.Main.configuration;

public class TutorialController {
    public Rectangle block1;
    public Rectangle block2;
    public Rectangle block4;
    public Rectangle block3;
    public Button confirmSelectionButton;
    public Label resultLabel;
    public Button continueToTestButton;
    public Button playTutorialButton;

    private ArrayList<Rectangle> allBlocks;

    private ArrayList<ArrayList<Block>> sequences;

    private ArrayList<Block> correctSequence;
    private ArrayList<Block> userSequence;

    private int numberOfCorrectlyAnsweredTrials;

    private boolean isContinueButtonDisabled;

    private boolean waitingForUser;

    private int sequenceIndex;


    private final String RESULT_LABEL_START = "VÝSLEDEK: ";

    private final String WRONG_ANSWER_STYLE = "-fx-background-color: red; -fx-text-fill: white;";
    private final String RIGHT_ANSWER_STYLE = "-fx-background-color: greenyellow; -fx-text-fill: black;";

    @FXML
    public void initialize() {
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
        // TODO resize
        allBlocks = getListOfBlocks();
        setAllBlocksDisable(true);
        userSequence = new ArrayList<>();
        sequences = Tools.loadSequences(configuration.getPathToSequenceDir() + "\\tutorialSequences.json");
        continueToTestButton.setDisable(true);
        continueToTestButton.setVisible(false);
        numberOfCorrectlyAnsweredTrials = 0;
        isContinueButtonDisabled = true;
        waitingForUser = false;
        sequenceIndex = 0;
    }

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

    public void confirmSelectionButtonOnMouseClicked(MouseEvent event) {
        SequenceScore sequenceScore = new SequenceScore(correctSequence, userSequence, 0);
        if (sequenceScore.isUserCorrect()) {
            resultLabel.setText(RESULT_LABEL_START + "SPRÁVNĚ");
            resultLabel.setStyle(RIGHT_ANSWER_STYLE);
            new animatefx.animation.Pulse(resultLabel).play();
            numberOfCorrectlyAnsweredTrials++;
        } else {
            resultLabel.setText(RESULT_LABEL_START + "ŠPATNĚ");
            resultLabel.setStyle(WRONG_ANSWER_STYLE);
            new animatefx.animation.Shake(resultLabel).play();
        }
        waitingForUser = false;
        userSequence = new ArrayList<>();
    }

    public void playTutorialButtonOnMouseClicked(MouseEvent event) {
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
            waitingForUser = true;
        });
        if (!waitingForUser) {
            tutorialThread.setDaemon(true);
            tutorialThread.start();
        } else {
            resultLabel.setText("Nepotvrdil jste odpověď!");
            resultLabel.setStyle(WRONG_ANSWER_STYLE);
            new animatefx.animation.Shake(resultLabel).play();
        }
    }

    public void continueToTestButtonOnMouseClicked(MouseEvent event) {
        // TODO edit PROBLEM
        Tools.changeScene(Constants.FxmlFile.START_TEST);
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

    public void confirmSelectionButtonOnMouseEntered(MouseEvent event) {
        confirmSelectionButton.setStyle(String.format(BUTTON_STYLE, "green", "white" ));
    }

    public void confirmSelectionButtonOnMouseExited(MouseEvent event) {
        confirmSelectionButton.setStyle(String.format(BUTTON_STYLE, "green", "green"));
    }

    public void playTutorialButtonOnMouseEntered(MouseEvent event) {
        playTutorialButton.setStyle(String.format(BUTTON_STYLE, "orange", "white" ));
    }

    public void playTutorialButtonOnMouseExited(MouseEvent event) {
        playTutorialButton.setStyle(String.format(BUTTON_STYLE, "orange", "orange" ));
    }

    public void continueToTestButtonOnMouseExited(MouseEvent event) {
        continueToTestButton.setStyle(String.format(BUTTON_STYLE, "yellow", "yellow" ));
    }

    public void continueToTestButtonOnMouseEntered(MouseEvent event) {
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

}
