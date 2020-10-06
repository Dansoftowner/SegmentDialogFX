package com.dansoftware.sgmdialog;

import javafx.scene.Node;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public abstract class Segment {

    private final String title;

    public Segment(@NotNull String title) {
        this.title = Objects.requireNonNull(title);
    }

    public String getTitle() {
        return title;
    }

    protected void onSegmentHidden(@Nullable SegmentDialog segmentDialog) {
    }

    protected void onSegmentFocused(@Nullable SegmentDialog segmentDialog) {
    }

    protected abstract Node getContent();
}
