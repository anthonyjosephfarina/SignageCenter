package com.guidewire.signagecenter.model.dto.slide;

public class MapSlideGetDTO extends AbstractSlideGetDTO {

    private Double latCoord;

    private Double longCoord;

    public Double getLatCoord() {
        return latCoord;
    }

    public void setLatCoord(Double latCoord) {
        this.latCoord = latCoord;
    }

    public Double getLongCoord() {
        return longCoord;
    }

    public void setLongCoord(Double longCoord) {
        this.longCoord = longCoord;
    }
}
