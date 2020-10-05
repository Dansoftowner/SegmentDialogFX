package com.dansoftware.sgmdialog;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class SegmentDialogTest extends Application {

    private static final Logger logger = LoggerFactory.getLogger(SegmentDialogTest.class);

    private static final String TEST_STYLE_SHEET = "/com/dansoftware/sgmdialog/stylesheet.css";

    @Override
    public void start(Stage primaryStage) throws Exception {
        SegmentDialog segmentDialog = new SegmentDialog(getSegmentSequenceImplementation());

        Scene scene = new Scene(segmentDialog);
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
                new Segment("Segment 1", new Label("Sg One")),
                new Segment("Segment 2", new Label("Sg Two")),
                new Segment("Segment 3", new Label("Sg Three"))
        );
    }
}
