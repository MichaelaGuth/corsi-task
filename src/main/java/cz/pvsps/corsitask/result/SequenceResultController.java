package cz.pvsps.corsitask.result;

import cz.pvsps.corsitask.tools.Point;
import cz.pvsps.corsitask.tools.Tools;
import cz.pvsps.corsitask.tools.Vector;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.WindowEvent;

import java.util.ArrayList;
import java.util.List;

import static cz.pvsps.corsitask.Main.stage;

public class SequenceResultController {
    public AnchorPane anchorPane;
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

    @FXML
    public void initialize() {
        Tools.resize(anchorPane);
        allBlocks = getListOfBlocks();
        allBlockLabels = getListOfBlockLabels();

        stage.addEventHandler(WindowEvent.WINDOW_SHOWING, new  EventHandler<WindowEvent>()
        {
            @Override
            public void handle(WindowEvent window)
            {
                ArrayList<Integer> correctSequence = new ArrayList<>();
                correctSequence.add(1);
                correctSequence.add(2);
                correctSequence.add(3);

                ArrayList<Integer> userSequence = new ArrayList<>();
                userSequence.add(1);
                userSequence.add(2);
                userSequence.add(4);

                SequenceScore sequenceScore = new SequenceScore(correctSequence, userSequence, 0);
                drawArrowsForSequenceScore(sequenceScore);
            }
        });

        //drawLinesBetweenTwoPoints(new Coordinates(290,70), new Coordinates(90, 90), Color.GREEN);

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

    private void drawArrowsForSequenceScore(SequenceScore sequenceScore) {
        if (!sequenceScore.isUserCorrect()) {
            drawArrowsForSequence(sequenceScore.userSequence(), Color.RED);
        }
        drawArrowsForSequence(sequenceScore.correctSequence(), Color.GREEN);
    }

    private void drawArrowsForSequence(List<Integer> sequence, Color color) {
        for (int i = 0; i < sequence.size()-1; i++) {
            drawArrowBetweenTwoBlocks(allBlocks.get(sequence.get(i)-1), allBlocks.get(sequence.get(i+1)-1), color);
        }
    }

    private void drawArrowBetweenTwoBlocks(Rectangle block1, Rectangle block2, Color color) {
        double tmp = block1.getHeight() / 2;
        Point startPoint = new Point(block1.getX() + tmp, block1.getY() + tmp);
        Point endPoint = new Point(block2.getX() + tmp, block2.getY() + tmp);
        Line line = drawLineBetweenTwoPoints(startPoint, endPoint, color);
        drawArrowHead(line);
    }

    private Line drawLineBetweenTwoPoints(Point startPoint, Point endPoint, Paint color) {
        Line line = new Line();
        line.setStroke(color);
        line.setStrokeWidth(3);
        line.setVisible(true);
        line.setStartX(startPoint.x());
        line.setStartY(startPoint.y());
        line.setEndX(endPoint.x());
        line.setEndY(endPoint.y());
        anchorPane.getChildren().add(line);
        return line;
    }


    private Point findRotatedUnitVector(Point startPoint, Point endPoint, double angleInRad) {
        Point vector = new Point(endPoint.x()-startPoint.x(), endPoint.y()-startPoint.y());
        double vectorLength = Math.sqrt((vector.x()*vector.x()) + (vector.y()* vector.y()));
        Point unitVector = new Point(vector.x()/vectorLength, vector.y()/vectorLength);
        double sin = Math.sin(angleInRad);
        double cos = Math.cos(angleInRad);
        return new Point(cos*unitVector.x() - sin*unitVector.y(), sin*unitVector.x() + cos*unitVector.y());
    }

    private Vector getVector(Point startPoint, Point endPoint) {
        return new Vector(endPoint.x()-startPoint.x(), endPoint.y()-startPoint.y());
    }

    private Vector getUnitVector(Vector vector) {
        return new Vector(vector.x()/ vector.getLength(), vector.y()/vector.getLength());
    }

    private Vector rotateVector(Vector vector, double angleInRad) {
        double sin = Math.sin(angleInRad);
        double cos = Math.cos(angleInRad);
        return new Vector(cos*vector.x() - sin*vector.y(), sin*vector.x() + cos*vector.y());
    }

    private final double ARROWHEAD_ANGLE = Math.PI/4;
    private final int ARROWHEAD_LENGTH = 20;

    private void drawArrowHead(Line line) {
        Point lineStartPoint = new Point(line.getStartX(), line.getStartY());
        Point lineEndPoint = new Point(line.getEndX(), line.getEndY());
        Vector vector = getVector(lineEndPoint, lineStartPoint);
        Vector unitVector = getUnitVector(vector);

        // right
        Vector rotatedVector = rotateVector(unitVector, ARROWHEAD_ANGLE);
        Point arrowHeadPoint = new Point(lineEndPoint.x() + rotatedVector.x()*ARROWHEAD_LENGTH, lineEndPoint.y() + rotatedVector.y()*ARROWHEAD_LENGTH);
        drawLineBetweenTwoPoints(lineEndPoint, arrowHeadPoint, line.getStroke());

        // left
        rotatedVector = rotateVector(unitVector, -ARROWHEAD_ANGLE);
        arrowHeadPoint = new Point(lineEndPoint.x() + rotatedVector.x()*ARROWHEAD_LENGTH, lineEndPoint.y() + rotatedVector.y()*ARROWHEAD_LENGTH);
        drawLineBetweenTwoPoints(lineEndPoint, arrowHeadPoint, line.getStroke());

    }
}
