package com.guidewire.signagecenter.model.dto;

import com.guidewire.signagecenter.model.dto.slide.AbstractSlideGetDTO;

import java.util.ArrayList;
import java.util.List;
/**
 * DTO for PlaylistPlay.
 *
 * @author
 */
public class PlaylistPlayDTO {

    private Long id;

    private List<AbstractSlideGetDTO> slides = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<AbstractSlideGetDTO> getSlides() {
        return slides;
    }

    public void setSlides(List<AbstractSlideGetDTO> slides) {
        this.slides = slides;
    }
}
