package com.dansoftware.sgmdialog;

import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

class SegmentLabelSeparator extends StackPane {

    private static final String STYLE_CLASS = "segmentLabelSeparator";

    SegmentLabelSeparator() {
        getStyleClass().add(STYLE_CLASS);
        ImageView imageView = new ImageView();
        imageView.getStyleClass().add("graphic");
        getChildren().add(imageView);
    }

}
