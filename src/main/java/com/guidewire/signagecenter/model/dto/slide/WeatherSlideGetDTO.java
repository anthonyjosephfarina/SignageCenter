package com.guidewire.signagecenter.model.dto.slide;

public class WeatherSlideGetDTO extends AbstractSlideGetDTO {

    private Long cityId;

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }
}
