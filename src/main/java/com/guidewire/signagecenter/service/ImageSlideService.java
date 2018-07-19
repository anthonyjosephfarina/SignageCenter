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
/**
 * ImageSlideService
 * @author
 */
@Service
public class ImageSlideService {
    /**
     * The Logger for ImageSlideService.
     */
    private static final Logger logger = LoggerFactory.getLogger(ImageSlideService.class);

    /**
     *  Inject ImageSlideRepository.
     */
    @Autowired
    private ImageSlideRepository imageSlideRepository;

    /**
     *  Inject ImageStorageService.
     */
    @Autowired
    private ImageStorageService imageStorageService;

    /**
     *  Inject PlaylistService.
     */
    @Autowired
    private PlaylistService playlistService;

    /**
     * Creates the ImageSlideEntity .
     * @param imageSlide <code>ImageSlideEntity</code>.
     * @param playlistId <code>Long</code>.
     * @return ImageSlideEntity.
     * @throws
     */
    public ImageSlideEntity createImageSlide(ImageSlideEntity imageSlide, Long playlistId) {

        PlaylistEntity playlistEntity = playlistService.getPlaylist(playlistId);
        imageSlide.setPlaylistEntity(playlistEntity);

        return imageSlideRepository.save(imageSlide);
    }

    /**
     * Attaches the Image .
     * @param imageFile <code>MultipartFile</code>.
     * @param imageSlideId <code>Long</code>.
     * @return ImageSlideEntity.
     * @throws ResourceNotFoundException
     */
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

    /**
     *  Load Resource
     * @param fileName <code>String</code>.
     * @return Resource.
     * @throws
     */
    public Resource loadImage(String fileName) {
        return imageStorageService.loadAsResource(fileName);
    }

    /**
     * Retrieves all  values from the  ImageSlideEntity  in database.
     *  @return List<ImageSlideEntity> .
     *  @throws
     */
    public List<ImageSlideEntity> getAll() {
        return imageSlideRepository.findAll();
    }
}
