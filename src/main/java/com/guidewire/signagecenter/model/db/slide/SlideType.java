package com.guidewire.signagecenter.model.db.slide;

public enum SlideType {
    IMAGE(Values.IMAGE),
    CALENDAR(Values.CALENDAR),
    WEATHER(Values.WEATHER),
    MAP(Values.MAP);

    SlideType(String value) {
        if (!this.name().equals(value)) {
            throw new IllegalArgumentException("SlideType value must be the same as it's name.");
        }
    }

    public static class Values {
        static final String IMAGE = "IMAGE";
        static final String CALENDAR = "CALENDAR";
        static final String WEATHER = "WEATHER";
        static final String MAP = "MAP";
    }
}
