package cz.pvsps.corsitask.animations;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.Node;
import javafx.util.Duration;

/**
 * Pulse animation implementation. Extends of {@link CorsiAnimation}.
 */
public class Pulse extends CorsiAnimation{

    /**
     * Class constructor specifying node, which will be animated.
     * @param node {@link javafx.scene.Node}, which will be animated.
     */
    public Pulse(Node node){
        super(node);
    }

    /**
     * Set up pulse animation for scene node specified in constructor.
     */
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
