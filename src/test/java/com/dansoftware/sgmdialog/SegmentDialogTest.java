package com.dansoftware.sgmdialog;

import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import jfxtras.styles.jmetro.JMetro;
import jfxtras.styles.jmetro.JMetroStyleClass;
import jfxtras.styles.jmetro.Style;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;

public class SegmentDialogTest extends Application {

    private static final Logger logger = LoggerFactory.getLogger(SegmentDialogTest.class);

    private static final String TEST_STYLE_SHEET = "/com/dansoftware/sgmdialog/stylesheet_light.css";

    @Override
    public void start(Stage primaryStage) throws Exception {
        SegmentDialog segmentDialog = new SegmentDialog(getSegmentSequenceImplementation());
        segmentDialog.getStyleClass().add(JMetroStyleClass.BACKGROUND);

        Scene scene = new Scene(segmentDialog);
        new JMetro(Style.LIGHT).setScene(scene);
        scene.getStylesheets().add(TEST_STYLE_SHEET);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private SegmentSequence getSegmentSequenceImplementation() {
        return new SegmentSequence(getSegments()) {
            @Override
            protected void onSegmentsFinished() {
                logger.debug("All segments are now done");
            }
        };
    }

    private List<Segment> getSegments() {
        return Arrays.asList(
                new LanguageSegment(),
                new ThemeSegment(),
                new SegmentThree()
        );
    }

    private static final class SegmentOne extends Segment {

        public SegmentOne() {
            super("Segment 1");
        }

        @Override
        protected Node getContent() {
            return new Label("Sg One");
        }

        @Override
        protected void onSegmentHidden(@NotNull SegmentDialog segmentDialog) {
            logger.debug(String.format("%s is hidden", getClass().getSimpleName()));
        }

        @Override
        protected void onSegmentFocused(@NotNull SegmentDialog segmentDialog) {
            logger.debug(String.format("%s is focused", getClass().getSimpleName()));
        }
    }

    private static final class SegmentTwo extends Segment {

        public SegmentTwo() {
            super("Segment 2");
        }

        @Override
        protected Node getContent() {
            return new Label("Sg Two");
        }

        @Override
        protected void onSegmentHidden(@NotNull SegmentDialog segmentDialog) {
            logger.debug(String.format("%s is hidden", getClass().getSimpleName()));
        }

        @Override
        protected void onSegmentFocused(@NotNull SegmentDialog segmentDialog) {
            logger.debug(String.format("%s is focused", getClass().getSimpleName()));
        }
    }

    private static final class SegmentThree extends Segment {

        public SegmentThree() {
            super("Segment 3");
        }

        @Override
        protected Node getContent() {
            return new Label("Sg Three");
        }

        @Override
        protected void onSegmentHidden(@NotNull SegmentDialog segmentDialog) {
            logger.debug(String.format("%s is hidden", getClass().getSimpleName()));
        }

        @Override
        protected void onSegmentFocused(@NotNull SegmentDialog segmentDialog) {
            logger.debug(String.format("%s is focused", getClass().getSimpleName()));
        }
    }
}
