package com.dansoftware.sgmdialog;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

class SegmentDialogBottom extends BorderPane
        implements ChangeListener<Segment> {

    private static final Logger logger = LoggerFactory.getLogger(SegmentDialogBottom.class);

    private static final String STYLE_CLASS = "segmentDialogBottom";

    private static final String NEXT_BUTTON_STRING = "segment.dialog.button.next";
    private static final String FINISH_BUTTON_STRING = "segment.dialog.button.finish";
    private static final String PREV_BUTTON_STRING = "segment.dialog.button.prev";

    private final SegmentSequence segmentSequence;

    private final BiStringProperty nextItemTextProperty;
    private final BiStringProperty prevItemTextProperty;

    private final Button nextItemButton;
    private final Button prevItemButton;

    private final HBox rightBox;

    private final ResourceBundle resourceBundle;

    SegmentDialogBottom(@NotNull ResourceBundle resourceBundle,
                        @Nullable List<Button> customButtons,
                        @NotNull SegmentSequence segmentSequence) {
        this.getStyleClass().add(STYLE_CLASS);
        this.resourceBundle = Objects.requireNonNull(resourceBundle, "ResourceBundle is needed!");
        this.segmentSequence = Objects.requireNonNull(segmentSequence, "segmentSequence shouldn't be null");
        this.nextItemButton = new NextSegmentButton();
        this.prevItemButton = new PreviousSegmentButton();
        this.rightBox = new HBox(10, nextItemButton);

        this.nextItemTextProperty = new BiStringProperty(resourceBundle.getString(NEXT_BUTTON_STRING), "", "");
        this.prevItemTextProperty = new BiStringProperty(resourceBundle.getString(PREV_BUTTON_STRING), "", "");

        this.nextItemButton.textProperty().bind(nextItemTextProperty);
        this.prevItemButton.textProperty().bind(prevItemTextProperty);

        nextItemButton.setOnAction(event -> segmentSequence.navigateNext());
        prevItemButton.setOnAction(event -> segmentSequence.navigateBack());

        this.setCustomButtons(customButtons);
        this.setRight(rightBox);

        segmentSequence.focusedSegmentProperty().addListener(this);
        this.init(segmentSequence);
    }

    SegmentDialogBottom(@NotNull ResourceBundle resourceBundle,
                        @NotNull SegmentSequence segmentSequence) {
        this(resourceBundle, null, segmentSequence);
    }

    private void init(SegmentSequence segmentSequence) {
        this.changed(segmentSequence.focusedSegmentProperty(), null, segmentSequence.getFocusedSegment());
    }

    @Override
    public void changed(ObservableValue<? extends Segment> observable,
                        Segment oldValue,
                        Segment newValue) {
        if (newValue != null) {

            if (segmentSequence.isSegmentLast(newValue)) {
                nextItemTextProperty.setFixValue(resourceBundle.getString(FINISH_BUTTON_STRING));
            } else {
                Segment nextSegment = segmentSequence.getNextFrom(newValue);
                nextItemTextProperty.set(resourceBundle.getString(NEXT_BUTTON_STRING), nextSegment.getTitle());
            }

            if (segmentSequence.isSegmentFirst(newValue)) {
                rightBox.getChildren().remove(prevItemButton);
            } else {
                try {
                    rightBox.getChildren().add(0, prevItemButton);
                } catch (IllegalArgumentException ignored) {
                    //we don't care, if the previous button is already on the scene
                }

                Segment previousSegment = segmentSequence.getPrevFrom(newValue);
                prevItemTextProperty.set(resourceBundle.getString(PREV_BUTTON_STRING), previousSegment.getTitle());
            }
        }
    }

    public void setCustomButtons(List<Button> customButtons) {
        if (customButtons != null) {
            HBox hbox = new HBox();
            hbox.getChildren().addAll(customButtons);
            this.setLeft(hbox);
        }
    }

    public List<Button> getCustomButtons() {
        return ((Pane) this.getLeft()).getChildren().stream()
                .filter(node -> node instanceof Button)
                .map(node -> (Button) node)
                .collect(Collectors.toList());
    }

    public Button getNextItemButton() {
        return nextItemButton;
    }

    public Button getPrevItemButton() {
        return prevItemButton;
    }

    private static final class BiStringProperty extends SimpleStringProperty {

        BiStringProperty(String prefix,
                         String suffix,
                         String separator) {
            this.set(prefix, suffix, separator);
        }

        void setFixValue(String value) {
            set(value, "", "");
        }

        void set(String prefix, String suffix) {
            set(prefix, suffix, ": ");
        }

        void set(String prefix, String suffix, String separator) {
            set(getFinalValue(prefix, suffix, separator));
        }

        private String getFinalValue(String prefix, String suffix, String separator) {
            return prefix == null ? suffix : prefix + separator + suffix;
        }
    }
}
