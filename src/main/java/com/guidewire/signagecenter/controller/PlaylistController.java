package com.guidewire.signagecenter.controller;

import com.guidewire.signagecenter.mapper.AbstractSlideGetMapper;
import com.guidewire.signagecenter.model.db.PlaylistEntity;
import com.guidewire.signagecenter.model.db.slide.AbstractSlideEntity;
import com.guidewire.signagecenter.model.dto.PlaylistCreateDTO;
import com.guidewire.signagecenter.model.dto.PlaylistGetDTO;
import com.guidewire.signagecenter.model.dto.PlaylistPlayDTO;
import com.guidewire.signagecenter.model.dto.slide.AbstractSlideGetDTO;
import com.guidewire.signagecenter.service.AbstractSlideService;
import com.guidewire.signagecenter.service.PlaylistService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/playlist")
public class PlaylistController {

    private static final Logger logger = LoggerFactory.getLogger(PlaylistController.class);

    @Autowired
    private PlaylistService playlistService;

    @Autowired
    private AbstractSlideService abstractSlideService;

    @Autowired
    private AbstractSlideGetMapper slideGetMapper;

    @PostMapping
    public PlaylistGetDTO createPlaylist(@RequestBody PlaylistCreateDTO playlistCreateDTO) {

        // create playlistEntity
        PlaylistEntity playlistEntity = new PlaylistEntity();
        playlistEntity.setName(playlistCreateDTO.getName());
        playlistEntity = playlistService.createPlaylist(playlistEntity, playlistCreateDTO.getOfficeId(),
                playlistCreateDTO.getSubscribedPlaylistIds());

        // convert playlistEntity to dto
        return PlaylistGetDTO.map(playlistEntity);
    }

    @PutMapping("/unsubscribe/{playlistId}")
    public ResponseEntity<?> unsubscribeFromPlaylist(@PathVariable Long playlistId,
                                                     @RequestParam Long playlistSubscriptionId) {
        playlistService.unsubscribe(playlistId, playlistSubscriptionId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/{playlistId}")
    public ResponseEntity<?> deletePlaylist(@PathVariable Long playlistId) {
        playlistService.deletePlaylist(playlistId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/{playlistId}")
    public PlaylistGetDTO getPlaylist(@PathVariable Long playlistId,
                                      @RequestParam(required = false, defaultValue = "false") Boolean withSlides,
                                      @RequestParam(required = false, defaultValue = "false") Boolean withSubscriptions) {
        PlaylistEntity playlistEntity = playlistService.getPlaylist(playlistId);

        PlaylistGetDTO playlistGetDTO = PlaylistGetDTO.map(playlistEntity);

        if (withSlides) {
            List<AbstractSlideGetDTO> slideDTOs = playlistEntity.getSlides().stream()
                    .map(slideGetMapper::mapToDTO)
                    .collect(Collectors.toList());
            playlistGetDTO.setSlides(slideDTOs);
        }

        if (withSubscriptions) {
            List<PlaylistGetDTO> subscribedPlaylists = playlistEntity.getSubscribedPlaylists().stream()
                    .map(PlaylistGetDTO::map)
                    .collect(Collectors.toList());
            playlistGetDTO.setSubscribedPlaylists(subscribedPlaylists);
        }

        return playlistGetDTO;
    }

    @GetMapping("/all")
    public List<PlaylistGetDTO> getAllPlaylists() {
        return playlistService.getAll().stream().map(PlaylistGetDTO::map).collect(Collectors.toList());
    }

    /**
     * Get all of the slides for the playlist
     *
     * @param playlistId
     * @return
     */
    @GetMapping("/play/{playlistId}")
    public PlaylistPlayDTO playPlaylist(@PathVariable Long playlistId) {

        // get main playlist and it's subscribed playlists
        List<PlaylistEntity> playlistEntities = new ArrayList<>();
        PlaylistEntity mainPlaylistEntity = playlistService.getPlaylist(playlistId);
        playlistEntities.add(mainPlaylistEntity);
        playlistEntities.addAll(mainPlaylistEntity.getSubscribedPlaylists());

        // gather all of the active slides from the playlists
        List<AbstractSlideEntity> slides = playlistEntities.stream()
                .map(ply -> abstractSlideService.getAllActiveByPlaylist(ply.getId()))
                .flatMap(Collection::stream)
                .collect(Collectors.toList());

        // convert to dtos
        List<AbstractSlideGetDTO> slideDTOs = slides.stream()
                .map(slideGetMapper::mapToDTO)
                .collect(Collectors.toList());

        PlaylistPlayDTO playlistPlayDTO = new PlaylistPlayDTO();
        playlistPlayDTO.setId(mainPlaylistEntity.getId());
        playlistPlayDTO.setSlides(slideDTOs);
        return playlistPlayDTO;
    }

}
