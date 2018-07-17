package com.guidewire.signagecenter.model.dto.slide;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class CalendarSlideCreateDTO {

    private String name;

    private Double duration;

    private Instant startDate;

    private Instant endDate;

    private Long playlistId;

    private List<Long> calendarIds = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getDuration() {
        return duration;
    }

    public void setDuration(Double duration) {
        this.duration = duration;
    }

    public Instant getStartDate() {
        return startDate;
    }

    public void setStartDate(Instant startDate) {
        this.startDate = startDate;
    }

    public Instant getEndDate() {
        return endDate;
    }

    public void setEndDate(Instant endDate) {
        this.endDate = endDate;
    }

    public Long getPlaylistId() {
        return playlistId;
    }

    public void setPlaylistId(Long playlistId) {
        this.playlistId = playlistId;
    }

    public List<Long> getCalendarIds() {
        return calendarIds;
    }

    public void setCalendarIds(List<Long> calendarIds) {
        this.calendarIds = calendarIds;
    }
}
