package com.dansoftware.sgmdialog;

import javafx.stage.Stage;
import javafx.stage.Window;
import org.jetbrains.annotations.Nullable;

public class ConfigurationSegmentSequence extends SegmentSequence {

    ConfigurationSegmentSequence() {
        super(new LanguageSegment(), new ThemeSegment());
    }

    @Override
    protected void onSegmentsFinished(@Nullable SegmentDialog segmentDialog) {
        if (segmentDialog != null) {
            Window window = segmentDialog.getScene().getWindow();
            if (window instanceof Stage) {
                ((Stage) window).close();
            } else {
                window.hide();
            }
        }
    }
}
