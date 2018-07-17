package com.guidewire.signagecenter.model.dto.slide;

import com.guidewire.signagecenter.model.db.slide.ImageSlide;

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

    public static ImageSlideGetDTO map(ImageSlide imageSlide) {
        ImageSlideGetDTO imageSlideGetDTO = new ImageSlideGetDTO();
        imageSlideGetDTO.setId(imageSlide.getId());
        imageSlideGetDTO.setName(imageSlide.getName());
        imageSlideGetDTO.setText(imageSlide.getText());
        imageSlideGetDTO.setImageUrl(imageSlide.getImageUrl());
        imageSlideGetDTO.setSlideType(imageSlide.getSlideType());
        imageSlideGetDTO.setDuration(imageSlide.getDuration());
        imageSlideGetDTO.setStartDate(imageSlide.getStartDate());
        imageSlideGetDTO.setEndDate(imageSlide.getEndDate());
        return imageSlideGetDTO;
    }
}
