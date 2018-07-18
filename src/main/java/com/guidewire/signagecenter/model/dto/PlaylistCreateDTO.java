package com.guidewire.signagecenter.model.dto;

import java.util.ArrayList;
import java.util.List;

public class PlaylistCreateDTO {

    private Long officeId;

    private String name;

    private List<Long> subscribedPlaylistIds = new ArrayList<>();

    public Long getOfficeId() {
        return officeId;
    }

    public void setOfficeId(Long officeId) {
        this.officeId = officeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Long> getSubscribedPlaylistIds() {
        return subscribedPlaylistIds;
    }

    public void setSubscribedPlaylistIds(List<Long> subscribedPlaylistIds) {
        this.subscribedPlaylistIds = subscribedPlaylistIds;
    }
}
