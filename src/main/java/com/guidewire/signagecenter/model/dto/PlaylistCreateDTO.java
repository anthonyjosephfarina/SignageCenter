package com.guidewire.signagecenter.model.dto;

public class PlaylistCreateDTO {

    private Long officeId;

    private String name;

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
}
