package com.guidewire.signagecenter.model.db.slide;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Entity
@DiscriminatorValue(value = SlideType.Values.MAP)
public class MapSlideEntity extends AbstractSlideEntity {

    @NotNull
    @Column(scale = 9, precision = 6)
    private Double latCoord;

    @NotNull
    @Column(scale = 9, precision = 6)
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
}
