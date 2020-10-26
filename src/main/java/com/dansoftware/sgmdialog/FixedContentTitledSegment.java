package com.dansoftware.sgmdialog;

import javafx.scene.Node;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ResourceBundle;

public abstract class FixedContentTitledSegment extends TitledSegment {

    private Node contentCache;

    public FixedContentTitledSegment(@NotNull String name, @NotNull String title) {
        super(name, title);
    }

    public FixedContentTitledSegment(@Nullable ResourceBundle resourceBundle, @NotNull String name, @NotNull String title) {
        super(resourceBundle, name, title);
    }

    @NotNull
    protected abstract Node createCenterContent();

    @Override
    protected Node getCenterContent() {
        if (contentCache == null)
            contentCache = createCenterContent();
        return contentCache;
    }
}
