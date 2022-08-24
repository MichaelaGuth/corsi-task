package cz.pvsps.corsitask.trial;

import cz.pvsps.corsitask.Tools;
import cz.pvsps.corsitask.result.Result;
import javafx.fxml.FXML;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.stage.Screen;

import java.util.ArrayList;

import static cz.pvsps.corsitask.Constants.BUTTON_STYLE;
import static cz.pvsps.corsitask.Main.stage;
import static cz.pvsps.corsitask.Tools.saveResults;

public class TrialController {
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
    private ArrayList<Result> results;

    private final Color YELLOW = Color.web("#f5da0f");
    private final Color BLUE = Color.web("#1f3bff");
    private final Color WHITE = Color.WHITE;
    private final Color BLACK = Color.BLACK;


    @FXML
    public void initialize() {
        prepareTest();
        Thread testThread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Boolean.TRUE);
                sequenceIndex = 0;
                while (testInProgress) {
                    if (!waitingForUser) {
                        if (sequenceIndex == 0) {
                            try {
                                Thread.sleep(3000);
                            } catch (Exception e) {

                            }
                        }
                        if (sequenceIndex == 2) {
                            testInProgress = false;
                        } else {
                            playSequence(sequences.get(sequenceIndex));
                            sequenceIndex++;
                            waitingForUser = true;
                            userSequence = new ArrayList<>();
                            setAllBlocksDisable(false);
                            setButtonsDisable(false);
                        }
                    } else {
                        confirmSelectionButton.setVisible(true);
                        resetSelectionButton.setVisible(true);
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
        setAllLabelsVisibility(false);
        userSequence = new ArrayList<>();
        confirmSelectionButton.setDisable(true);
        resetSelectionButton.setDisable(true);
        resize();
        sequences = Tools.loadSequences("sequences.json");
        results = new ArrayList<>();
        testInProgress = true;
    }

    private void setButtonsDisable(boolean value) {
        confirmSelectionButton.setDisable(value);
        resetSelectionButton.setDisable(value);
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
        saveResults(results, "results.json");
    }

    public void resize() {
        if (stage.isFullScreen()) {
            Rectangle2D resolution = Screen.getPrimary().getBounds();
            double scale = resolution.getHeight() / borderPane.getPrefHeight();
            hbox.setScaleX(scale);
            hbox.setScaleY(scale);
        }
        //anchorPane.setScaleY(scale);
        //anchorPane.setScaleX(scale);
    }

    // TODO
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
            changeBlockColor(rectangle, YELLOW);
            int blockIndex = allBlocks.indexOf(rectangle);
            userSequence.add(blockIndex+1);
            allBlockLabels.get(blockIndex).setText(String.valueOf(userSequence.size()));
            allBlockLabels.get(blockIndex).setTextFill(BLUE);
            allBlockLabels.get(blockIndex).setVisible(true);
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
        // TODO add timer
        Result result = new Result(sequences.get(sequenceIndex-1), userSequence, 0);
        results.add(result);

        prepareForNewUserSequence();
        setAllBlocksDisable(true);
        setButtonsDisable(true);
        waitingForUser = false;
    }

    public void resetSelectionButtonOnMouseClicked(MouseEvent event) {
        prepareForNewUserSequence();
    }

    private void prepareForNewUserSequence() {
        userSequence = new ArrayList<>();
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
