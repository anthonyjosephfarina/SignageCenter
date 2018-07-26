package com.guidewire.signagecenter.service;

import com.guidewire.signagecenter.exception.ResourceNotFoundException;
import com.guidewire.signagecenter.messaging.MessageType;
import com.guidewire.signagecenter.messaging.MessagingService;
import com.guidewire.signagecenter.messaging.payload.SlideMessage;
import com.guidewire.signagecenter.model.db.slide.AbstractSlideEntity;
import com.guidewire.signagecenter.repository.AbstractSlideRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
/**
 * AbstractSlideService
 * @author
 */
@Primary
@Service
public class AbstractSlideService extends MessagingService {

    /**
     * The Logger for AbstractSlideService.
     */
    private static final Logger logger = LoggerFactory.getLogger(AbstractSlideService.class);


    /**
     *  Inject AbstractSlideRepository.
     */
    @Autowired
    private AbstractSlideRepository abstractSlideRepository;

    /**
     * Retrieve the AbstractSlideEntity .
     * @param slideId <code>Long</code>.
     * @return AbstractSlideEntity.
     * @throws
     */
    public AbstractSlideEntity getSlide(Long slideId) {
        return abstractSlideRepository.findById(slideId).orElseThrow(() ->
                new ResourceNotFoundException("AbstractSlideEntity", "id", slideId));
    }

    /**
     * Retrieves all  values from the  AbstractSlideEntity  in database.
     *  @return List<AbstractSlideEntity> .
     *  @throws
     */
    public List<AbstractSlideEntity> getAll() {
        return abstractSlideRepository.findAll();
    }

    /**
     * Retrieve the AbstractSlideEntity  .
     * @param playlistId <code>Long</code>.
     * @return AbstractSlideEntity.
     * @throws
     */
    public List<AbstractSlideEntity> getAllByPlaylist(Long playlistId) {
        return abstractSlideRepository.findByPlaylistId(playlistId);
    }

    /**
     * Retrieve the AbstractSlideEntity  .
     *
     * @param playlistId <code>Long</code>.
     * @return AbstractSlideEntity.
     * @throws
     */
    public List<AbstractSlideEntity> getAllActiveByPlaylist(Long playlistId) {
        return abstractSlideRepository.findByPlaylistIdAndDate(playlistId, Instant.now());
    }

    /**
     * Deletes the AbstractSlideEntity value matching slideId.
     * @param slideId <code>Long</code>.
     * @throws
     */
    public void deleteSlide(Long slideId) {
        deleteSlide(getSlide(slideId));
    }

    /**
     * Deletes the AbstractSlideEntity
     *
     * @param abstractSlideEntity
     */
    public void deleteSlide(AbstractSlideEntity abstractSlideEntity) {
        abstractSlideRepository.delete(abstractSlideEntity);
    }

    public void addDeleteMessage(AbstractSlideEntity slide) {
        String destination = "/topic/playlist-" + slide.getPlaylist().getId();
        SlideMessage payload = new SlideMessage(slide.getId(), MessageType.SLIDE_DELETE);

        this.sendMessage(destination, payload);
    }
}
