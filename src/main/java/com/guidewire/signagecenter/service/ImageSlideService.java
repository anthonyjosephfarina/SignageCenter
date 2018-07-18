package com.guidewire.signagecenter.service;

import com.guidewire.signagecenter.exception.ResourceNotFoundException;
import com.guidewire.signagecenter.model.db.PlaylistEntity;
import com.guidewire.signagecenter.model.db.slide.ImageSlideEntity;
import com.guidewire.signagecenter.repository.ImageSlideRepository;
import com.guidewire.signagecenter.service.storage.ImageStorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.nio.file.Path;
import java.util.List;

@Service
public class ImageSlideService {

    private static final Logger logger = LoggerFactory.getLogger(ImageSlideService.class);

    @Autowired
    private ImageSlideRepository imageSlideRepository;

    @Autowired
    private ImageStorageService imageStorageService;

    @Autowired
    private PlaylistService playlistService;

    public ImageSlideEntity createImageSlide(ImageSlideEntity imageSlide, Long playlistId) {

        PlaylistEntity playlistEntity = playlistService.getPlaylist(playlistId);
        imageSlide.setPlaylistEntity(playlistEntity);

        return imageSlideRepository.save(imageSlide);
    }

    public ImageSlideEntity attachImage(MultipartFile imageFile, Long imageSlideId) {

        ImageSlideEntity imageSlide = imageSlideRepository.findById(imageSlideId).orElseThrow(() ->
                new ResourceNotFoundException("ImageSlideEntity", "id", imageSlideId));

        Path path = imageStorageService.store(imageFile);
        imageSlide.setImageFilePath(path.getFileName().toString());

        String url = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/slide/image/download/")
                .path(path.getFileName().toString())
                .toUriString();
        imageSlide.setImageUrl(url);

        return imageSlideRepository.save(imageSlide);
    }

    public Resource loadImage(String fileName) {
        return imageStorageService.loadAsResource(fileName);
    }

    public List<ImageSlideEntity> getAll() {
        return imageSlideRepository.findAll();
    }
}
