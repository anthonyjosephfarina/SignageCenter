package com.guidewire.signagecenter.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.guidewire.signagecenter.model.audit.DateAuditable;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Office extends DateAuditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @NaturalId
    @Size(max = 100)
    private String name;

    @JsonBackReference
    @OneToMany(mappedBy = "office", fetch = FetchType.LAZY)
    private List<Playlist> playlists = new ArrayList<>();

    @JsonBackReference
    @OneToMany(mappedBy = "office", fetch = FetchType.LAZY)
    private List<Calendar> calendars = new ArrayList<>();

    public Office() {

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

    public List<Playlist> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(List<Playlist> playlists) {
        this.playlists = playlists;
    }

    public List<Calendar> getCalendars() {
        return calendars;
    }

    public void setCalendars(List<Calendar> calendars) {
        this.calendars = calendars;
    }
}
