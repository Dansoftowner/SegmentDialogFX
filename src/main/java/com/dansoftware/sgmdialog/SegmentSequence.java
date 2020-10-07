package com.dansoftware.sgmdialog;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public abstract class SegmentSequence implements Iterable<Segment> {

    private static final Logger logger = LoggerFactory.getLogger(SegmentSequence.class);

    private SegmentDialog segmentDialog;

    private final ObjectProperty<Segment> focusedSegment;
    private final List<Segment> segments;

    public SegmentSequence(@NotNull List<Segment> segments) {
        this.segments = segments;
        this.focusedSegment = new SimpleObjectProperty<>();
        this.segments.stream().findFirst().ifPresent(focusedSegment::set);
    }

    public SegmentSequence(Segment... segments) {
        this(Arrays.asList(segments));
    }

    final SegmentDialog getSegmentDialog() {
        return segmentDialog;
    }

    final void setSegmentDialog(SegmentDialog segmentDialog) {
        this.segmentDialog = segmentDialog;
    }

    public final boolean isSegmentFirst(Segment segment) {
        return this.segments.get(0).equals(segment);
    }

    public final boolean isSegmentLast(@Nullable Segment segment) {
        return this.segments.get(segments.size() - 1).equals(segment);
    }

    public final Segment getFocusedSegment() {
        return focusedSegment.get();
    }

    public final ObjectProperty<Segment> focusedSegmentProperty() {
        return focusedSegment;
    }

    public final Segment getPrevFrom(Segment from) {
        Segment last = null;
        for (Segment segment : this) {
            if (segment.equals(from)) {
                return last;
            }
            last = segment;
        }

        return null;
    }

    public final Segment getNextFrom(Segment from) {
        Segment last = null;
        for (Segment segment : this) {
            if (last != null && last.equals(from)) {
                return segment;
            }
            last = segment;
        }

        return null;
    }

    public final void navigateBack() {
        Segment prev = getPrevFrom(this.focusedSegment.get());
        if (prev != null) {
            focusedSegment.set(prev);
        }
    }

    public final void navigateNext() {
        Segment next = getNextFrom(this.focusedSegment.get());
        if (next != null) {
            focusedSegment.set(next);
        } else {
            onSegmentsFinished(segmentDialog);
        }
    }

    public final void skipAll() {
        for (Segment segment : this) {
            segment.onSegmentFocused(null);
            segment.onSegmentHidden(null);
        }
    }

    @NotNull
    @Override
    public Iterator<Segment> iterator() {
        return segments.iterator();
    }

    protected abstract void onSegmentsFinished(@Nullable SegmentDialog segmentDialog);
}
