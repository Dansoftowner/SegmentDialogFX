package com.dansoftware.sgmdialog;

import javafx.css.*;
import javafx.scene.shape.SVGPath;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

class SegmentLabelSeparator extends SVGPath {

    private static final String CLASS_NAME = "segmentLabelArrow";

    private final static CssMetaData<SegmentLabelSeparator, String> SVG_PATH_CSS_META =
            new CssMetaData<SegmentLabelSeparator, String>(
                    "-svg-path",
                    StyleConverter.getStringConverter()) {
        @Override
        public boolean isSettable(SegmentLabelSeparator styleable) {
            return !styleable.styleableContent.isBound();
        }

        @Override
        public StyleableProperty<String> getStyleableProperty(SegmentLabelSeparator styleable) {
            return styleable.styleableContent;
        }
    };

    private static final List<CssMetaData<? extends Styleable, ?>> CLASS_CSS_META_DATA;
    static {
        List<CssMetaData<? extends Styleable, ?>> cssMetaData = new LinkedList<>(SVGPath.getClassCssMetaData());
        cssMetaData.add(SVG_PATH_CSS_META);
        CLASS_CSS_META_DATA = Collections.unmodifiableList(cssMetaData);
    }


    private final StyleableStringProperty styleableContent =
            new StyleableStringProperty("M18.629 15.997l-7.083-7.081L13.462 7l8.997 8.997L13.457 25l-1.916-1.916z") {
        @Override
        public Object getBean() {
            return SegmentLabelSeparator.this;
        }

        @Override
        public String getName() {
            return "svgPath";
        }

        @Override
        public CssMetaData<? extends Styleable, String> getCssMetaData() {
            return SVG_PATH_CSS_META;
        }
    };


    SegmentLabelSeparator() {
        getStyleClass().add(CLASS_NAME);
        /*setScaleX(0.4);
        setScaleY(0.4);*/
        this.contentProperty().bind(styleableContent);
    }

    @Override
    public List<CssMetaData<? extends Styleable, ?>> getCssMetaData() {
        return CLASS_CSS_META_DATA;
    }

}
