package com.dansoftware.sgmdialog;

import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

class SegmentLabelSeparator extends StackPane {

    private static final String CLASS_NAME = "segmentLabelSeparator";

    SegmentLabelSeparator() {
        getStyleClass().add(CLASS_NAME);
        /*setScaleX(0.4);
        setScaleY(0.4);*/

        ImageView imageView = new ImageView();
        imageView.getStyleClass().add("graphic");
        getChildren().add(imageView);
    }

}
