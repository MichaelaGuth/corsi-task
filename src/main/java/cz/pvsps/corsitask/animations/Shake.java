package cz.pvsps.corsitask.animations;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.Node;
import javafx.util.Duration;

public class Shake extends CorsiAnimation {

    public Shake(Node node){
        super(node);
    }

    @Override
    public void initTimeline() {
        setTimeline(new Timeline(
                new KeyFrame(Duration.millis(0),
                        new KeyValue(getNode().translateXProperty(), 0, CorsiAnimation.INTERPOLATOR)
                ),
                new KeyFrame(Duration.millis(100),
                        new KeyValue(getNode().translateXProperty(), -10, CorsiAnimation.INTERPOLATOR)
                ),
                new KeyFrame(Duration.millis(200),
                        new KeyValue(getNode().translateXProperty(), 10, CorsiAnimation.INTERPOLATOR)
                ),
                new KeyFrame(Duration.millis(300),
                        new KeyValue(getNode().translateXProperty(), -10, CorsiAnimation.INTERPOLATOR)
                ),
                new KeyFrame(Duration.millis(400),
                        new KeyValue(getNode().translateXProperty(), 10, CorsiAnimation.INTERPOLATOR)
                ),
                new KeyFrame(Duration.millis(500),
                        new KeyValue(getNode().translateXProperty(), -10, CorsiAnimation.INTERPOLATOR)
                ),
                new KeyFrame(Duration.millis(600),
                        new KeyValue(getNode().translateXProperty(), 10, CorsiAnimation.INTERPOLATOR)
                ),
                new KeyFrame(Duration.millis(700),
                        new KeyValue(getNode().translateXProperty(), -10, CorsiAnimation.INTERPOLATOR)
                ),
                new KeyFrame(Duration.millis(800),
                        new KeyValue(getNode().translateXProperty(), 10, CorsiAnimation.INTERPOLATOR)
                ),
                new KeyFrame(Duration.millis(900),
                        new KeyValue(getNode().translateXProperty(), -10, CorsiAnimation.INTERPOLATOR)
                ),
                new KeyFrame(Duration.millis(1000),
                        new KeyValue(getNode().translateXProperty(), 0, CorsiAnimation.INTERPOLATOR)
                )
        ));
    }
}
