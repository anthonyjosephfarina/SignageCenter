package com.guidewire.signagecenter.model.db;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.guidewire.signagecenter.model.db.audit.DateAuditableEntity;
import com.guidewire.signagecenter.model.db.slide.AbstractSlideEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "PLAYLIST")
public class PlaylistEntity extends DateAuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    private String name;

    @JsonManagedReference
    @ManyToOne
    private OfficeEntity office;

    @JsonBackReference
    @OneToMany(mappedBy = "playlist", fetch = FetchType.LAZY)
    private List<AbstractSlideEntity> slides = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "PLAYLIST_SUBSCRIPTION",
            joinColumns = {@JoinColumn(name = "parent_playlist_id")},
            inverseJoinColumns = {@JoinColumn(name = "sub_playlist_id")})
    private List<PlaylistEntity> subscribedPlaylists = new ArrayList<>();

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

    public OfficeEntity getOffice() {
        return office;
    }

    public void setOffice(OfficeEntity office) {
        this.office = office;
    }

    public List<AbstractSlideEntity> getSlides() {
        return slides;
    }

    public void setSlides(List<AbstractSlideEntity> slides) {
        this.slides = slides;
    }

    public List<PlaylistEntity> getSubscribedPlaylists() {
        return subscribedPlaylists;
    }

    public void setSubscribedPlaylistEntities(List<PlaylistEntity> subscribedPlaylists) {
        this.subscribedPlaylists = subscribedPlaylists;
    }
}
