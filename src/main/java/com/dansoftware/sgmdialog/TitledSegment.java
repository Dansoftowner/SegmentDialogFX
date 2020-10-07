package com.dansoftware.sgmdialog;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;
import java.util.ResourceBundle;

/**
 * A {@link TitledSegment} is a {@link Segment} implementation that provides an additional
 * title label on the segment's gui (it's <b>styleClass</b> is <b>'segmentTitleLabel'</b>).
 *
 * <p>
 * In this case, instead of the {@link #getContent()} method, the {@link #getCenterContent()} should
 * be overridden.
 *
 * @see Segment
 * @author Daniel Gyorffy
 */
public abstract class TitledSegment extends Segment {

    private static final String TITLE_LABEL_STYLE_CLASS = "segmentTitleLabel";

    private Node content;
    private final String title;

    public TitledSegment(@NotNull String name, @NotNull String title) {
        super(name);
        this.title = Objects.requireNonNull(title, "title shouldn't be null");
    }

    public TitledSegment(@Nullable ResourceBundle resourceBundle,
                         @NotNull String name,
                         @NotNull String title) {
        super(resourceBundle, name);
        this.title = getI18nValue(resourceBundle, Objects.requireNonNull(title, "title shouldn't be null"));
    }

    private Node createContent() {
        BorderPane borderPane = new BorderPane();
        BorderPane titleBorderPane = new BorderPane();
        borderPane.setTop(titleBorderPane);

        Label titleLabel = new Label(title);
        titleLabel.getStyleClass().add(TITLE_LABEL_STYLE_CLASS);
        titleBorderPane.setLeft(titleLabel);

        return borderPane;
    }

    /**
     * Creates the content for this {@link TitledSegment}
     *
     * @return the {@link Node} content
     */
    protected abstract Node getCenterContent();

    @Override
    protected final @NotNull Node getContent() {
        if (content == null) {
            this.content = createContent();
            ((BorderPane) content).setCenter(getCenterContent());
        }

        return content;
    }
}
