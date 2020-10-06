package com.dansoftware.sgmdialog;

import javafx.scene.control.Button;

class NextSegmentButton extends Button {

    private static final String STYLE_CLASS = "nextSegmentButton";

    NextSegmentButton() {
        getStyleClass().add(STYLE_CLASS);
        setDefaultButton(true);
    }
}
