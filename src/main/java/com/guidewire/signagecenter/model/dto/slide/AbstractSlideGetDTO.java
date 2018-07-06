package com.guidewire.signagecenter.model.dto.slide;

import com.guidewire.signagecenter.model.slide.SlideType;

import java.time.Instant;

public abstract class AbstractSlideGetDTO {

    private Long id;

    private String name;

    private SlideType slideType;

    private Double duration;

    private Instant startDate;

    private Instant endDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SlideType getSlideType() {
        return slideType;
    }

    public void setSlideType(SlideType slideType) {
        this.slideType = slideType;
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
}
