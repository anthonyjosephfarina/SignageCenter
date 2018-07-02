package com.guidewire.signagecenter.model.slide;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.guidewire.signagecenter.model.Playlist;
import com.guidewire.signagecenter.model.audit.DateAuditable;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.Instant;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "slide_type", discriminatorType = DiscriminatorType.STRING)
public abstract class AbstractSlide extends DateAuditable implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(length = 100, nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "slide_type", length = 30, nullable = false, insertable = false, updatable = false)
    private SlideType slideType;

    @Column(scale = 9, nullable = false)
    private Double duration;

    private Instant startDate;

    private Instant endDate;

    @JsonManagedReference
    @ManyToOne
    private Playlist playlist;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Playlist getPlaylist() {
        return playlist;
    }

    public void setPlaylist(Playlist playlist) {
        this.playlist = playlist;
    }
}
