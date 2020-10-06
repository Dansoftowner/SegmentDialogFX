package com.dansoftware.sgmdialog;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.css.*;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import org.jetbrains.annotations.NotNull;

import java.util.*;

class SegmentLabelSequence extends HBox
        implements ChangeListener<Segment> {

    private static final String STYLE_CLASS = "segmentLabelArea";

    private final Map<Segment, SegmentLabel> segmentLabelMap;
    private final SegmentSequence segmentSequence;

    public SegmentLabelSequence(@NotNull SegmentSequence segmentSequence) {
        this.getStyleClass().add(STYLE_CLASS);
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
            SegmentLabel label = new SegmentLabel(segment.getTitle());
            segmentLabelMap.put(segment, label);
            this.getChildren().add(label);
            if (iterator.hasNext())
                this.getChildren().add(new SegmentLabelSeparator());
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

    private static final class SegmentLabel extends Label {

        private static final String CLASS_NAME = "segmentLabel";

        SegmentLabel(String text) {
            super(text);
            getStyleClass().add(CLASS_NAME);
        }

        public void setFocusedState(boolean value) {
            pseudoClassStateChanged(PseudoClass.getPseudoClass("focused"), value);
        }
    }
}
