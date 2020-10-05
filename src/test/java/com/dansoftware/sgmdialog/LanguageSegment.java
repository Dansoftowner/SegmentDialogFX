package com.dansoftware.sgmdialog;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

public class LanguageSegment extends Segment
        implements Initializable {

    private static final Logger logger = LoggerFactory.getLogger(LanguageSegment.class);

    private Node content;

    @FXML
    private ListView<String> listView;

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
    protected void onSegmentHidden(@NotNull SegmentDialog segmentDialog) {
        logger.debug("Selected language: {}", listView.getSelectionModel().getSelectedItem());
    }

    @Override
    protected void onSegmentFocused(@NotNull SegmentDialog segmentDialog) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        listView.getItems().addAll(Locale.getISOLanguages());
    }
}
