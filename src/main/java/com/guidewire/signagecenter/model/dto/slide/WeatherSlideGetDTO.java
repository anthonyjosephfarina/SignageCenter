package com.guidewire.signagecenter.model.dto.slide;

import com.guidewire.signagecenter.model.db.slide.WeatherSlideEntity;
/**
 * DTO for  WeatherSlideGet.
 *
 * @author
 */
public class WeatherSlideGetDTO extends AbstractSlideGetDTO {

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

    public static WeatherSlideGetDTO map(WeatherSlideEntity weatherSlide) {
        WeatherSlideGetDTO weatherSlideGetDTO = new WeatherSlideGetDTO();
        weatherSlideGetDTO.setId(weatherSlide.getId());
        weatherSlideGetDTO.setName(weatherSlide.getName());
        weatherSlideGetDTO.setLatCoord(weatherSlide.getLatCoord());
        weatherSlideGetDTO.setLongCoord(weatherSlide.getLongCoord());
        weatherSlideGetDTO.setSlideType(weatherSlide.getSlideType());
        weatherSlideGetDTO.setDuration(weatherSlide.getDuration());
        weatherSlideGetDTO.setStartDate(weatherSlide.getStartDate());
        weatherSlideGetDTO.setEndDate(weatherSlide.getEndDate());
        return weatherSlideGetDTO;
    }
}
