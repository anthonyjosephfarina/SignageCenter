package com.guidewire.signagecenter.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.guidewire.signagecenter.model.audit.DateAuditable;
import com.guidewire.signagecenter.model.slide.AbstractSlide;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Playlist extends DateAuditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    private String name;

    @JsonBackReference
    @OneToMany(mappedBy = "playlist", fetch = FetchType.LAZY)
    private List<AbstractSlide> slides = new ArrayList<>();

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

    public List<AbstractSlide> getSlides() {
        return slides;
    }

    public void setSlides(List<AbstractSlide> slides) {
        this.slides = slides;
    }
}
