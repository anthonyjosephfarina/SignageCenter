package com.guidewire.signagecenter.controller;

import com.guidewire.signagecenter.model.dto.ImageSlideCreateDTO;
import com.guidewire.signagecenter.model.slide.ImageSlide;
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
    public Long createImageSlide(@RequestBody ImageSlideCreateDTO imageSlideCreateDTO) {

        ImageSlide imageSlide = new ImageSlide();
        imageSlide.setName(imageSlideCreateDTO.getName());
        imageSlide.setText(imageSlideCreateDTO.getText());
        imageSlide.setDuration(imageSlideCreateDTO.getDuration());
        imageSlide.setStartDate(imageSlideCreateDTO.getStartDate());
        imageSlide.setEndDate(imageSlideCreateDTO.getEndDate());

        return imageSlideService.createImageSlide(imageSlide, imageSlideCreateDTO.getPlaylistId()).getId();
    }

    @PostMapping("/attach/{imageSlideId}")
    public ResponseEntity<?> attachImage(@RequestParam("file") MultipartFile file, @PathVariable Long imageSlideId) {
        imageSlideService.attachImage(file, imageSlideId);
        return ResponseEntity.ok().build();
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
