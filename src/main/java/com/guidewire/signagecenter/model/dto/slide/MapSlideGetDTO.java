package com.guidewire.signagecenter.model.dto.slide;

import com.guidewire.signagecenter.model.db.slide.MapSlide;

public class MapSlideGetDTO extends AbstractSlideGetDTO {

    private Double latCoord;

    private Double longCoord;

    private String address;

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public static MapSlideGetDTO map(MapSlide mapSlide) {
        MapSlideGetDTO mapSlideGetDTO = new MapSlideGetDTO();
        mapSlideGetDTO.setId(mapSlide.getId());
        mapSlideGetDTO.setName(mapSlide.getName());
        mapSlideGetDTO.setLatCoord(mapSlide.getLatCoord());
        mapSlideGetDTO.setLongCoord(mapSlide.getLongCoord());
        mapSlideGetDTO.setAddress(mapSlide.getAddress());
        mapSlideGetDTO.setSlideType(mapSlide.getSlideType());
        mapSlideGetDTO.setDuration(mapSlide.getDuration());
        mapSlideGetDTO.setStartDate(mapSlide.getStartDate());
        mapSlideGetDTO.setEndDate(mapSlide.getEndDate());
        return mapSlideGetDTO;
    }
}
