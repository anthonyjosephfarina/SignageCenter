package com.guidewire.signagecenter.model.dto.slide;

import com.guidewire.signagecenter.model.db.slide.WeatherSlideEntity;

public class WeatherSlideGetDTO extends AbstractSlideGetDTO {

    private Long cityId;

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public static WeatherSlideGetDTO map(WeatherSlideEntity weatherSlide) {
        WeatherSlideGetDTO weatherSlideGetDTO = new WeatherSlideGetDTO();
        weatherSlideGetDTO.setId(weatherSlide.getId());
        weatherSlideGetDTO.setName(weatherSlide.getName());
        weatherSlideGetDTO.setCityId(weatherSlide.getCityId());
        weatherSlideGetDTO.setSlideType(weatherSlide.getSlideType());
        weatherSlideGetDTO.setDuration(weatherSlide.getDuration());
        weatherSlideGetDTO.setStartDate(weatherSlide.getStartDate());
        weatherSlideGetDTO.setEndDate(weatherSlide.getEndDate());
        return weatherSlideGetDTO;
    }
}
