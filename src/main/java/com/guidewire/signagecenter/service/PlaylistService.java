package com.guidewire.signagecenter.service;

import com.guidewire.signagecenter.exception.ResourceNotFoundException;
import com.guidewire.signagecenter.model.db.OfficeEntity;
import com.guidewire.signagecenter.model.db.PlaylistEntity;
import com.guidewire.signagecenter.model.db.slide.AbstractSlideEntity;
import com.guidewire.signagecenter.model.dto.PlaylistPlayDTO;
import com.guidewire.signagecenter.repository.OfficeRepository;
import com.guidewire.signagecenter.repository.PlaylistRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * PlaylistService retrieves the saved Playlist details and save the newly created Playlists
 * @author
 */
@Service
public class PlaylistService {

    /**
     * The Logger for PlaylistService.
     */
    private static final Logger logger = LoggerFactory.getLogger(PlaylistService.class);

    /**
     *  Inject PlaylistRepository.
     */
    @Autowired
    private PlaylistRepository playlistRepository;

    /**
     *  Inject OfficeRepository.
     */
    @Autowired
    private OfficeRepository officeRepository;
  
    /**
     * Creates the PlaylistEntity .
     * @param playlistEntity <code>PlaylistEntity</code>.
     * @param officeId <code>Long</code>.
     * @param subscribedPlaylistIds <code>List<Long></code>
     * @return PlaylistEntity.
     * @throws ResourceNotFoundException
     */
    public PlaylistEntity createPlaylist(PlaylistEntity playlistEntity, Long officeId, List<Long> subscribedPlaylistIds) {

        OfficeEntity officeEntity = officeRepository.findById(officeId).orElseThrow(() ->
                new ResourceNotFoundException("OfficeEntity", "id", officeId));
        playlistEntity.setOffice(officeEntity);

        List<PlaylistEntity> subscribedPlaylists = subscribedPlaylistIds.stream()
                .map(id -> getPlaylist(id))
                .collect(Collectors.toList());
        playlistEntity.setSubscribedPlaylistEntities(subscribedPlaylists);

        return playlistRepository.save(playlistEntity);
    }

    public PlaylistEntity unsubscribe(Long playlistId, Long playlistSubscriptionId) {
        PlaylistEntity playlistEntity = getPlaylist(playlistId);

        List<PlaylistEntity> updatedSubscribedPlaylists = playlistEntity.getSubscribedPlaylists().stream()
                .filter(ply -> !ply.getId().equals(playlistSubscriptionId))
                .collect(Collectors.toList());
        playlistEntity.setSubscribedPlaylistEntities(updatedSubscribedPlaylists);
      
        return playlistRepository.save(playlistEntity);
    }

    /**
     * Retrieve the PlaylistEntity .
     * @param playlistId <code>Long</code>.
     * @return PlaylistEntity.
     * @throws ResourceNotFoundException
     */
    public PlaylistEntity getPlaylist(Long playlistId) {
        return playlistRepository.findById(playlistId).orElseThrow(() ->
                new ResourceNotFoundException("PlaylistEntity", "id", playlistId));
    }


    /**
     * Retrieves all  values from the  PlaylistEntity  in database.
     *  @return List<PlaylistEntity> .
     *  @throws
     */
    public List<PlaylistEntity> getAll() {
        return playlistRepository.findAll();
    }

    /**
     * Deletes the playlistEntity value matching playlistId.
     * @param playlistId <code>Long</code>.
     * @throws
     */
    public void deletePlaylist(Long playlistId) {
        PlaylistEntity playlistEntity = getPlaylist(playlistId);
        playlistRepository.delete(playlistEntity);
    }

    /**
     * Retrieve the PlaylistPlayDTO Object.
     * @param playlistId <code>Long</code>.
     * @return PlaylistPlayDTO.
     * @throws
     */
    public PlaylistPlayDTO play(Long playlistId) {

        // get the main playlist and it's subscribed playlistEntities
        List<PlaylistEntity> playlistEntities = new ArrayList<>();
        PlaylistEntity mainPlaylistEntity = getPlaylist(playlistId);
        playlistEntities.add(mainPlaylistEntity);
        playlistEntities.addAll(mainPlaylistEntity.getSubscribedPlaylists());

        // gather all of the valid slides
        List<AbstractSlideEntity> slides = playlistEntities.stream()
                .map(PlaylistEntity::getSlides)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());


        PlaylistPlayDTO playlistPlayDTO = new PlaylistPlayDTO();
        playlistPlayDTO.setId(mainPlaylistEntity.getId());
        return playlistPlayDTO;
    }
}
