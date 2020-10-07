package com.dansoftware.sgmdialog;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;

public class SegmentDialog extends BorderPane
        implements ChangeListener<Segment> {

    private static final Logger logger = LoggerFactory.getLogger(SegmentDialog.class);

    private static final String STYLE_CLASS = "segmentDialog";

    private final SegmentSequence segmentSequence;
    private final SegmentLabelSequence labelSequence;
    private final SegmentDialogBottom dialogBottom;
    private Node placeHolder;

    public SegmentDialog(@NotNull ResourceBundle resourceBundle,
                         @NotNull SegmentSequence segmentSequence,
                         @Nullable List<Button> customButtons,
                         @Nullable Node placeHolder) {
        Objects.requireNonNull(segmentSequence, "segmentSequence shouldn't be null");
        checkSegmentSequenceCompatibility(segmentSequence);
        this.segmentSequence = segmentSequence;
        this.segmentSequence.setSegmentDialog(this);
        this.labelSequence = new SegmentLabelSequence(segmentSequence);
        this.dialogBottom = new SegmentDialogBottom(resourceBundle, customButtons, segmentSequence);
        this.placeHolder = placeHolder;
        this.segmentSequence.focusedSegmentProperty().addListener(this);
        this.getStyleClass().add(STYLE_CLASS);
        this.init(segmentSequence);
        this.setPadding(new Insets(10));
        this.setTop(labelSequence);
        this.setBottom(dialogBottom);
        this.blockFutureModifyOnTopBottom();
    }

    public SegmentDialog(@NotNull ResourceBundle resourceBundle,
                         @NotNull SegmentSequence segmentSequence,
                         @Nullable Node placeHolder) {
        this(resourceBundle, segmentSequence, null, placeHolder);
    }

    public SegmentDialog(@NotNull ResourceBundle resourceBundle,
                         @NotNull SegmentSequence segmentSequence) {
        this(resourceBundle, segmentSequence, null, null);
    }

    public SegmentDialog(@NotNull SegmentSequence segmentSequence) {
        this(ResourceBundle.getBundle("com.dansoftware.sgmdialog.BaseSgmDialogValues"), segmentSequence, null, null);
    }

    private void checkSegmentSequenceCompatibility(SegmentSequence segmentSequence) {
        if (this == segmentSequence.getSegmentDialog()) {
            throw new AlreadyUsedSequenceException("The passed sequence is already used by another dialog");
        }
    }

    private void init(SegmentSequence segmentSequence) {
        this.changed(segmentSequence.focusedSegmentProperty(), null, segmentSequence.getFocusedSegment());
    }

    private void blockFutureModifyOnTopBottom() {
        this.topProperty().addListener((observable, oldValue, newValue) -> {
            setTop(oldValue);
            throw new UnsupportedOperationException("Can't change the top of a SegmentDialog");
        });
        this.bottomProperty().addListener((observable, oldValue, newValue) -> {
            setBottom(oldValue);
            throw new UnsupportedOperationException("Can't change the bottom of a SegmentDialog");
        });
    }

    public final List<Button> getCustomButtons() {
        return this.dialogBottom.getCustomButtons();
    }

    public final void setCustomButtons(List<Button> customButtons) {
        this.dialogBottom.setCustomButtons(customButtons);
    }

    public final BooleanProperty nextButtonDisableProperty() {
        return this.dialogBottom.getNextItemButton().disableProperty();
    }

    public final BooleanProperty prevButtonDisableProperty() {
        return this.dialogBottom.getPrevItemButton().disableProperty();
    }

    public final Node getPlaceHolder() {
        return placeHolder;
    }

    public final SegmentSequence getSegmentSequence() {
        return segmentSequence;
    }

    @Override
    public void changed(ObservableValue<? extends Segment> observable, Segment oldValue, Segment newValue) {
        if (newValue == null) {
            setCenter(placeHolder);
            return;
        }

        logger.debug(String.format("Segment changed: %s", newValue.getTitle()));

        Optional<Segment> newSegmentOptional = Optional.of(newValue);
        newSegmentOptional.ifPresent(segment -> {
            setCenter(segment.getContent());
            segment.onSegmentFocused(this);
        });

        Optional<Segment> oldSegmentOptional = Optional.ofNullable(oldValue);
        oldSegmentOptional.ifPresent(segment -> segment.onSegmentHidden(this));
    }

    public static final class AlreadyUsedSequenceException extends RuntimeException {
        AlreadyUsedSequenceException(String msg) {
            super(msg);
        }
    }
}
