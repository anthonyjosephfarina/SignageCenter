package com.guidewire.signagecenter.model.dto;

import com.guidewire.signagecenter.model.Playlist;
import com.guidewire.signagecenter.model.dto.slide.AbstractSlideGetDTO;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class PlaylistGetDTO {

    private Long id;

    private String name;

    private Long officeId;

    private String officeName;

    private List<AbstractSlideGetDTO> slides = new ArrayList<>();

    private Instant createdAt;

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

    public Long getOfficeId() {
        return officeId;
    }

    public void setOfficeId(Long officeId) {
        this.officeId = officeId;
    }

    public String getOfficeName() {
        return officeName;
    }

    public void setOfficeName(String officeName) {
        this.officeName = officeName;
    }

    public List<AbstractSlideGetDTO> getSlides() {
        return slides;
    }

    public void setSlides(List<AbstractSlideGetDTO> slides) {
        this.slides = slides;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public static PlaylistGetDTO map(Playlist playlist) {
        PlaylistGetDTO playlistGetDTO = new PlaylistGetDTO();
        playlistGetDTO.setId(playlist.getId());
        playlistGetDTO.setName(playlist.getName());
        playlistGetDTO.setOfficeId(playlist.getOffice().getId());
        playlistGetDTO.setOfficeName(playlist.getOffice().getName());
        playlistGetDTO.setCreatedAt(playlist.getCreatedAt());
        return playlistGetDTO;
    }
}
