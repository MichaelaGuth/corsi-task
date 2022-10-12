package cz.pvsps.corsitask.result;

import cz.pvsps.corsitask.tools.Block;
import cz.pvsps.corsitask.tools.Tools;
import javafx.animation.FadeTransition;
import javafx.animation.SequentialTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.WindowEvent;
import javafx.util.Duration;

import java.util.ArrayList;

import static cz.pvsps.corsitask.Main.stage;

public class SequenceResultController {
    public AnchorPane anchorPane;

    // TODO create class extending Rectangle with number
    public Rectangle block1;
    public Rectangle block2;
    public Rectangle block3;
    public Rectangle block4;
    public Rectangle block5;
    public Rectangle block6;
    public Rectangle block7;
    public Rectangle block8;
    public Rectangle block9;
    public Label labelBlock1;
    public Label labelBlock2;
    public Label labelBlock3;
    public Label labelBlock4;
    public Label labelBlock5;
    public Label labelBlock6;
    public Label labelBlock7;
    public Label labelBlock8;
    public Label labelBlock9;

    private ArrayList<Rectangle> allBlocks;
    private ArrayList<Label> allBlockLabels;


    // TODO Add feature
    // TODO Add times between each block click to new table

    // TODO Add option to export to csv

    @FXML
    public void initialize() {
        Tools.resize(anchorPane);
        allBlocks = getListOfBlocks();
        allBlockLabels = getListOfBlockLabels();
        setAllBlocksOpacity(0.1);

        stage.addEventHandler(WindowEvent.WINDOW_SHOWING, new  EventHandler<WindowEvent>()
        {
            @Override
            public void handle(WindowEvent window)
            {
                ArrayList<Block> correctSequence = new ArrayList<>();
                correctSequence.add(new Block(1));
                correctSequence.add(new Block(2));
                correctSequence.add(new Block(3));

                ArrayList<Block> userSequence = new ArrayList<>();
                userSequence.add(new Block(1));
                userSequence.add(new Block(2));
                userSequence.add(new Block(4));

                playSequenceScore(new SequenceScore(correctSequence, userSequence, 0));

//                SequenceScore sequenceScore = new SequenceScore(correctSequence, userSequence, 0);
//                drawArrowsForSequenceScore(sequenceScore);
            }
        });

        //drawLinesBetweenTwoPoints(new Coordinates(290,70), new Coordinates(90, 90), Color.GREEN);

    }

    private void playSequenceScore(SequenceScore sequenceScore) {
        //var userSequenceAnimation = prepareSequenceAnimation(sequenceScore.userSequence(), Color.RED);
        //var correctSequenceAnimation = prepareSequenceAnimation(sequenceScore.correctSequence(), Color.GREEN);

        var userSequenceAnimation = new SequentialTransition();
        var correctSequenceAnimation = new SequentialTransition();
/*
        for (Block block : sequenceScore.userSequence()) {
            var rectangle = allBlocks.get(block.number()-1);
            rectangle.setFill(color);
            FadeTransition fadeTransition = fadeTransitionBlock(rectangle);
            sequentialTransition.getChildren().add(fadeTransition);
        }
*/
        userSequenceAnimation.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println(System.currentTimeMillis());
            }
        });
        correctSequenceAnimation.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println("correct sequence");
                System.out.println(System.currentTimeMillis());
            }
        });

        userSequenceAnimation.play();
        correctSequenceAnimation.play();
    }


    private SequentialTransition prepareSequenceAnimation(ArrayList<Block> sequence, Color color) {
        SequentialTransition sequentialTransition = new SequentialTransition();
        for (Block block : sequence) {
            var rectangle = allBlocks.get(block.number()-1);
            rectangle.setFill(color);
            FadeTransition fadeTransition = fadeTransitionBlock(rectangle);
            sequentialTransition.getChildren().add(fadeTransition);
        }
        sequentialTransition.setCycleCount(0);
        return sequentialTransition;
    }

    private void setAllBlocksOpacity(double value) {
        for (Rectangle block :
                allBlocks) {
            block.setOpacity(value);
        }
    }

    private FadeTransition fadeTransitionBlock(Rectangle block) {
        FadeTransition ft = new FadeTransition(Duration.millis(3000), block);
        ft.setFromValue(0.1);
        ft.setToValue(1.0);
        ft.setCycleCount(0);
        ft.setAutoReverse(true);
        return ft;
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


}
