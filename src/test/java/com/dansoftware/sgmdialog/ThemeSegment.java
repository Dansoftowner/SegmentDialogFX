package com.dansoftware.sgmdialog;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import jfxtras.styles.jmetro.JMetro;
import jfxtras.styles.jmetro.Style;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ThemeSegment extends Segment implements Initializable, ChangeListener<Toggle> {

    private static final Logger logger = LoggerFactory.getLogger(ThemeSegment.class);

    @FXML
    private Node root;

    @FXML
    private ToggleGroup themeGroup;

    @FXML
    private RadioButton darkThemeToggle;

    @FXML
    private RadioButton lightThemeToggle;

    public ThemeSegment() {
        super("Themes");
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ThemeSegment.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.load();
        } catch (IOException e) {
            logger.error("FXMLLoader error", e);
        }
    }

    @Override
    protected Node getContent() {
        return root;
    }

    @Override
    protected void onSegmentSkipped(@NotNull SegmentDialog segmentDialog) {
        logger.debug("ThemeSegment is skipped");
        logger.debug("Default theme is 'Light'");
    }

    @Override
    protected void onSegmentHidden(@NotNull SegmentDialog segmentDialog) {
        logger.debug("Selected theme is: {}", ((ToggleButton) themeGroup.getSelectedToggle()).getText());
    }

    @Override
    protected void onSegmentFocused(@NotNull SegmentDialog segmentDialog) {
    }

    @Override
    public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
        getContent().getScene().getStylesheets().clear();
        if (newValue.equals(darkThemeToggle)) {
            new JMetro(Style.DARK).setScene(getContent().getScene());
            getContent().getScene().getStylesheets().add(getClass().getResource("stylesheet_dark.css").toExternalForm());
        } else if (newValue.equals(lightThemeToggle)) {
            new JMetro(Style.LIGHT).setScene(getContent().getScene());
            getContent().getScene().getStylesheets().add(getClass().getResource("stylesheet_light.css").toExternalForm());
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        themeGroup.selectedToggleProperty().addListener(this);
    }
}
