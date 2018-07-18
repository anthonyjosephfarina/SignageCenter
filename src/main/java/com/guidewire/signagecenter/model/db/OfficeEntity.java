package com.guidewire.signagecenter.model.db;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.guidewire.signagecenter.model.db.audit.DateAuditableEntity;
import com.guidewire.signagecenter.model.db.calendar.AbstractCalendarEntity;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "OFFICE")
public class OfficeEntity extends DateAuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @NaturalId
    @Size(max = 100)
    private String name;

    @JsonBackReference
    @OneToMany(mappedBy = "office", fetch = FetchType.LAZY)
    private List<PlaylistEntity> playlists = new ArrayList<>();

    @JsonBackReference
    @OneToMany(mappedBy = "office", fetch = FetchType.LAZY)
    private List<AbstractCalendarEntity> calendars = new ArrayList<>();

    public OfficeEntity() {

    }

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

    public List<PlaylistEntity> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(List<PlaylistEntity> playlists) {
        this.playlists = playlists;
    }

    public List<AbstractCalendarEntity> getCalendars() {
        return calendars;
    }

    public void setCalendars(List<AbstractCalendarEntity> calendars) {
        this.calendars = calendars;
    }
}
