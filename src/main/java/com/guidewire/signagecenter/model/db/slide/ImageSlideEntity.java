package com.guidewire.signagecenter.model.db.slide;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = SlideType.Values.IMAGE)
public class ImageSlideEntity extends AbstractSlideEntity {

    @Column(length = 250)
    private String text;

    @Column(length = 512)
    private String imageFilePath;

    @Column(length = 512)
    private String imageUrl;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getImageFilePath() {
        return imageFilePath;
    }

    public void setImageFilePath(String imageFilePath) {
        this.imageFilePath = imageFilePath;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
