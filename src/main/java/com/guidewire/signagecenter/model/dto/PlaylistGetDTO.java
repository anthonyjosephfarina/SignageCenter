package com.guidewire.signagecenter.model.dto;

import com.guidewire.signagecenter.model.db.PlaylistEntity;
import com.guidewire.signagecenter.model.dto.slide.AbstractSlideGetDTO;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
/**
 * DTO for PlaylistGet.
 *
 * @author
 */
public class PlaylistGetDTO {

    private Long id;

    private String name;

    private Long officeId;

    private String officeName;

    private List<AbstractSlideGetDTO> slides = new ArrayList<>();

    private List<PlaylistGetDTO> subscribedPlaylists = new ArrayList<>();

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

    public List<PlaylistGetDTO> getSubscribedPlaylists() {
        return subscribedPlaylists;
    }

    public void setSubscribedPlaylists(List<PlaylistGetDTO> subscribedPlaylists) {
        this.subscribedPlaylists = subscribedPlaylists;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public static PlaylistGetDTO map(PlaylistEntity playlistEntity) {
        PlaylistGetDTO playlistGetDTO = new PlaylistGetDTO();
        playlistGetDTO.setId(playlistEntity.getId());
        playlistGetDTO.setName(playlistEntity.getName());
        playlistGetDTO.setOfficeId(playlistEntity.getOffice().getId());
        playlistGetDTO.setOfficeName(playlistEntity.getOffice().getName());
        playlistGetDTO.setCreatedAt(playlistEntity.getCreatedAt());
        return playlistGetDTO;
    }
}
