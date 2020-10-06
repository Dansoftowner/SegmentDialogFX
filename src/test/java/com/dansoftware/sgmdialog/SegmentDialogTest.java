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
        SegmentDialog segmentDialog = new ConfigurationSegmentDialog();
        segmentDialog.getStyleClass().add(JMetroStyleClass.BACKGROUND);

        Scene scene = new Scene(segmentDialog);
        new JMetro(Style.LIGHT).setScene(scene);
        scene.getStylesheets().add(TEST_STYLE_SHEET);
        primaryStage.setScene(scene);
        primaryStage.setWidth(900);
        primaryStage.setHeight(600);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
