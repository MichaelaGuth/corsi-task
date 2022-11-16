package cz.pvsps.corsitask.animations;

import javafx.animation.Interpolator;
import javafx.animation.Timeline;
import javafx.scene.Node;

public abstract class CorsiAnimation {

    public static final Interpolator INTERPOLATOR = Interpolator.SPLINE(0.25, 0.1, 0.25, 0.1);

    private Timeline timeline;
    private Node node;

     public CorsiAnimation(Node node){
         this.node = node;
         initTimeline();
     }

     public void play(){
         this.timeline.play();
     }

     public abstract void initTimeline();

     public Node getNode(){
         return this.node;
     }

     public void setTimeline(Timeline timeline){
         this.timeline = timeline;
     }
}
