package com.guidewire.signagecenter.model.dto.slide;

public class ImageSlideGetDTO extends AbstractSlideGetDTO {

    private String text;

    private String imageUrl;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
