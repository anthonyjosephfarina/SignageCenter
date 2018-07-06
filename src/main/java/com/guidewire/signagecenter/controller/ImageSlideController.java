package com.guidewire.signagecenter.controller;

import com.guidewire.signagecenter.model.dto.ImageSlideCreateDTO;
import com.guidewire.signagecenter.model.dto.ImageUploadReponse;
import com.guidewire.signagecenter.model.dto.slide.ImageSlideGetDTO;
import com.guidewire.signagecenter.model.slide.ImageSlide;
import com.guidewire.signagecenter.model.slide.SlideType;
import com.guidewire.signagecenter.service.ImageSlideService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/slide/image")
public class ImageSlideController {

    private static final Logger logger = LoggerFactory.getLogger(ImageSlideController.class);

    @Autowired
    private ImageSlideService imageSlideService;

    @PostMapping
    public ImageSlideGetDTO createImageSlide(@RequestBody ImageSlideCreateDTO imageSlideCreateDTO) {

        // create image slide
        ImageSlide imageSlide = new ImageSlide();
        imageSlide.setSlideType(SlideType.IMAGE);
        imageSlide.setName(imageSlideCreateDTO.getName());
        imageSlide.setText(imageSlideCreateDTO.getText());
        imageSlide.setDuration(imageSlideCreateDTO.getDuration());
        imageSlide.setStartDate(imageSlideCreateDTO.getStartDate());
        imageSlide.setEndDate(imageSlideCreateDTO.getEndDate());
        imageSlide = imageSlideService.createImageSlide(imageSlide, imageSlideCreateDTO.getPlaylistId());

        // convert to dto
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

    @PostMapping(value = "/attach/{imageSlideId}", produces = "application/json")
    public ImageUploadReponse attachImage(@RequestParam("file") MultipartFile file, @PathVariable Long imageSlideId) {
        return new ImageUploadReponse(imageSlideService.attachImage(file, imageSlideId).getImageUrl());
    }

    @GetMapping(value = "/download/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {

        Resource resource = imageSlideService.loadImage(fileName);

        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            logger.info("Could not determine file type.");
        }

        if (contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

    @GetMapping("/all")
    public List<ImageSlide> getAllImageSlides() {
        return imageSlideService.getAll();
    }

}
