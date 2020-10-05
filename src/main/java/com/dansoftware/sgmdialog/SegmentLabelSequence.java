package com.dansoftware.sgmdialog;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.css.PseudoClass;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.shape.SVGPath;
import org.jetbrains.annotations.NotNull;

import java.util.*;

class SegmentLabelSequence extends HBox
        implements ChangeListener<Segment> {

    private static final String CLASS_NAME = "segmentLabelArea";

    private final Map<Segment, LabelImpl> segmentLabelMap;
    private final SegmentSequence segmentSequence;

    public SegmentLabelSequence(@NotNull SegmentSequence segmentSequence) {
        this.getStyleClass().add(CLASS_NAME);
        this.segmentSequence = Objects.requireNonNull(segmentSequence, "segmentSequence shouldn't be null");
        this.segmentLabelMap = new HashMap<>();
        this.createGui(segmentSequence);
        this.setSpacing(10);
        this.segmentSequence.focusedSegmentProperty().addListener(this);
        this.init(segmentSequence);
    }

    private void createGui(SegmentSequence segmentSequence) {
        for (Iterator<Segment> iterator = segmentSequence.iterator(); iterator.hasNext(); ) {
            Segment segment = iterator.next();
            LabelImpl label = new LabelImpl(segment.getTitle());
            segmentLabelMap.put(segment, label);
            this.getChildren().add(label);
            if (iterator.hasNext())
                this.getChildren().add(new Arrow());
        }
    }

    private void init(SegmentSequence segmentSequence) {
        this.changed(segmentSequence.focusedSegmentProperty(), null, segmentSequence.getFocusedSegment());
    }

    @Override
    public void changed(ObservableValue<? extends Segment> observable,
                        Segment oldValue,
                        Segment newValue) {
        Optional.ofNullable(segmentLabelMap.get(oldValue)).ifPresent(label -> label.setFocusedState(false));
        Optional.ofNullable(segmentLabelMap.get(newValue)).ifPresent(label -> label.setFocusedState(true));
    }

    private static final class Arrow extends SVGPath {

        private static final String CLASS_NAME = "segmentLabelArrow";

        Arrow() {
            getStyleClass().add(CLASS_NAME);
            setContent("M18.629 15.997l-7.083-7.081L13.462 7l8.997 8.997L13.457 25l-1.916-1.916z");
        }
    }

    private static final class LabelImpl extends Label {

        private static final String CLASS_NAME = "segmentLabel";

        LabelImpl(String text) {
            super(text);
            getStyleClass().add(CLASS_NAME);
        }

        public void setFocusedState(boolean value) {
            pseudoClassStateChanged(PseudoClass.getPseudoClass("focused"), value);
        }
    }
}
