package com.dansoftware.sgmdialog;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class LanguageSegment extends Segment
        implements Initializable {

    private static final Logger logger = LoggerFactory.getLogger(LanguageSegment.class);

    private Node content;

    @FXML
    private ListView<Locale> listView;

    public LanguageSegment() {
        super("Default language");
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/dansoftware/sgmdialog/LanguageSegment.fxml"));
            fxmlLoader.setController(this);
            content = fxmlLoader.load();
        } catch (IOException e) {
            logger.error("FXMLoader error", e);
        }
    }

    @Override
    protected Node getContent() {
        return content;
    }

    @Override
    protected void onSegmentHidden(@Nullable SegmentDialog segmentDialog) {
        logger.debug("Selected language: {}", listView.getSelectionModel().getSelectedItem());
    }

    @Override
    protected void onSegmentFocused(@Nullable SegmentDialog segmentDialog) {
        new animatefx.animation.SlideInLeft(content).play();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        listView.getItems().addAll(Arrays.stream(Locale.getAvailableLocales()).skip(1).collect(Collectors.toList()));
        listView.getSelectionModel().select(Locale.getDefault());
        listView.scrollTo(Locale.getDefault());
    }
}
