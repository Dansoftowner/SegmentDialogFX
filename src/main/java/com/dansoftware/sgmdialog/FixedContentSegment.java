package com.dansoftware.sgmdialog;

import javafx.scene.Node;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ResourceBundle;

public abstract class FixedContentSegment extends Segment {

    private Node contentCache;

    public FixedContentSegment(@NotNull String name) {
        super(name);
    }

    public FixedContentSegment(@Nullable ResourceBundle resourceBundle, @NotNull String name) {
        super(resourceBundle, name);
    }

    @NotNull
    protected abstract Node createContent();

    @Override
    @NotNull
    protected  Node getContent() {
        if (contentCache == null)
            contentCache = createContent();
        return contentCache;
    }
}
