package com.guidewire.signagecenter.model.calendar;

public enum CalendarType {
    INTERNAL(Values.INTERNAL),
    OUTLOOK(Values.OUTLOOK),
    WORKDAY(Values.WORKDAY),
    GMAIL(Values.GMAIL);

    CalendarType(String value) {
        if (!this.name().equals(value)) {
            throw new IllegalArgumentException("CalendarType value must be the same as it's name.");
        }
    }

    public static class Values {
        static final String INTERNAL = "INTERNAL";
        static final String OUTLOOK = "OUTLOOK";
        static final String WORKDAY = "WORKDAY";
        static final String GMAIL = "GMAIL";
    }
}
