package cz.pvsps.corsitask.animations;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.Node;
import javafx.util.Duration;

public class Pulse extends CorsiAnimation{

    public Pulse(Node node){
        super(node);
    }

    @Override
    public void initTimeline() {
        setTimeline(new Timeline(
                new KeyFrame(Duration.millis(0),
                        new KeyValue(getNode().scaleXProperty(), 1, CorsiAnimation.INTERPOLATOR),
                        new KeyValue(getNode().scaleYProperty(), 1, CorsiAnimation.INTERPOLATOR),
                        new KeyValue(getNode().scaleZProperty(), 1, CorsiAnimation.INTERPOLATOR)
                ),
                new KeyFrame(Duration.millis(500),
                        new KeyValue(getNode().scaleXProperty(), 1.05, CorsiAnimation.INTERPOLATOR),
                        new KeyValue(getNode().scaleYProperty(), 1.05, CorsiAnimation.INTERPOLATOR),
                        new KeyValue(getNode().scaleZProperty(), 1.05, CorsiAnimation.INTERPOLATOR)
                ),
                new KeyFrame(Duration.millis(1000),
                        new KeyValue(getNode().scaleXProperty(), 1, CorsiAnimation.INTERPOLATOR),
                        new KeyValue(getNode().scaleYProperty(), 1, CorsiAnimation.INTERPOLATOR),
                        new KeyValue(getNode().scaleZProperty(), 1, CorsiAnimation.INTERPOLATOR)
                )
        ));
    }
}
