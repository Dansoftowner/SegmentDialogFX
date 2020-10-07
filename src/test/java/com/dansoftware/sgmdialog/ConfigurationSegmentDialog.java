package com.dansoftware.sgmdialog;

import javafx.scene.control.Button;

import java.util.Collections;

public class ConfigurationSegmentDialog extends SegmentDialog {
    public ConfigurationSegmentDialog() {
        super(new ConfigurationSegmentSequence());
        setCustomButtons(Collections.singletonList(createSkipButton()));
    }

    private Button createSkipButton() {
        Button button = new Button("Skip all");
        button.setOnAction(event -> {
            getSegmentSequence().skipAll();
        });

        return button;
    }
}
