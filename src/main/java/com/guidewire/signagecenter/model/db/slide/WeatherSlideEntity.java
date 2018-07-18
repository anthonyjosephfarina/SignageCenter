package com.guidewire.signagecenter.model.db.slide;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Entity
@DiscriminatorValue(value = SlideType.Values.WEATHER)
public class WeatherSlideEntity extends AbstractSlideEntity {

    @NotNull
    private Long cityId;

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }
}
