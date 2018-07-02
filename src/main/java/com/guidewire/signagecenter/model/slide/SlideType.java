package com.guidewire.signagecenter.model.slide;

public enum SlideType {
    IMAGE(Values.IMAGE),
    CALENDAR(Values.CALENDAR),
    WEATHER(Values.WEATHER),
    POLL(Values.POLL),
    MAP(Values.MAP);

    SlideType(String value) {
        if (!this.name().equals(value)) {
            throw new IllegalArgumentException("SlideType value must be the same as it's name.");
        }
    }

    public static class Values {
        public static final String IMAGE = "IMAGE";
        public static final String CALENDAR = "CALENDAR";
        public static final String WEATHER = "WEATHER";
        public static final String POLL = "POLL";
        public static final String MAP = "MAP";
    }
}
