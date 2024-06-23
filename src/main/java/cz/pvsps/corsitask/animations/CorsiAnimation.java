package cz.pvsps.corsitask.animations;

import javafx.animation.Interpolator;
import javafx.animation.Timeline;
import javafx.scene.Node;

/**
 * Abstract class for animations in the project.
 */
public abstract class CorsiAnimation {

    public static final Interpolator INTERPOLATOR = Interpolator.SPLINE(0.25, 0.1, 0.25, 0.1);

    private Timeline timeline;
    private final Node node;

    /**
     * Class constructor specifying node to which animation is applied.
     * @param node {@link javafx.scene.Node}, which will be animated.
     */
    public CorsiAnimation(Node node) {
        this.node = node;
        initTimeline();
    }

    /**
     *  Starts defined animation.
     */
    public void play() {
        this.timeline.play();
    }

    /**
     * Initialize an animation. It is called by constructor.
     */
    public abstract void initTimeline();

    public Node getNode() {
        return this.node;
    }

    /**
     * Set up timeline for animation.
     * @param timeline an animation <code>{@link javafx.animation.Timeline}</code> defined by one or more <code>{@link javafx.animation.KeyFrame}.</code>
     */
    public void setTimeline(Timeline timeline) {
        this.timeline = timeline;
    }
}
