package com.dansoftware.sgmdialog;

import javafx.scene.Node;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.MissingResourceException;
import java.util.Objects;
import java.util.ResourceBundle;

/**
 * A {@link Segment} object represents a 'slide' of a {@link SegmentDialog}.
 *
 * <p>
 * A {@link Segment} is not a GUI element itself, but it should create a {@link Node}
 * with the {@link #getContent()} method.
 *
 * <p>
 * Every segment has a <b>name</b> that will appear in the label-area in the top of the {@link SegmentDialog}.
 * <i>It can be internationalized using the constructor that accepts a ResourceBundle</i>
 *
 * @author Daniel Gyorffy
 */
public abstract class Segment {

    private ResourceBundle resourceBundle;
    private final String name;

    public Segment(@NotNull String name) {
        this.name = Objects.requireNonNull(name);
    }

    public Segment(@Nullable ResourceBundle resourceBundle,
                   @NotNull String name) {
        this.resourceBundle = resourceBundle;
        this.name = getI18nValue(resourceBundle, Objects.requireNonNull(name));
    }

    protected final ResourceBundle getResourceBundle() {
        return resourceBundle;
    }

    public String getName() {
        return name;
    }

    /**
     * Called when the particular {@link Segment} is left, and a new segment is shown.
     *
     * @param segmentDialog the owner {@link SegmentDialog}
     */
    protected void onSegmentHidden(@NotNull SegmentDialog segmentDialog) {
    }

    /**
     * Called when the particular {@link Segment} is shown to the user.
     *
     * @param segmentDialog the owner {@link SegmentDialog}
     */
    protected void onSegmentFocused(@NotNull SegmentDialog segmentDialog) {
    }

    /**
     * Called when the user skips the particular {@link Segment}.
     *
     * @param segmentDialog the owner {@link SegmentDialog}
     */
    protected void onSegmentSkipped(@NotNull SegmentDialog segmentDialog) {
    }

    /**
     * This method is called when the particular segment is on focus, and a
     * GUI element is needed.
     *
     * @return the {@link Node} content; shouldn't be null
     */
    @NotNull
    protected abstract Node getContent();


    static String getI18nValue(ResourceBundle resourceBundle, String key) {
        try {
            return resourceBundle.getString(key);
        } catch (MissingResourceException | NullPointerException e) {
            return key;
        }
    }
}
