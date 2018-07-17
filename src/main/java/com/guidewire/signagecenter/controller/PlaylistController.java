package com.guidewire.signagecenter.controller;

import com.guidewire.signagecenter.model.Playlist;
import com.guidewire.signagecenter.model.dto.PlaylistCreateDTO;
import com.guidewire.signagecenter.model.dto.PlaylistGetDTO;
import com.guidewire.signagecenter.service.PlaylistService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/playlist")
public class PlaylistController {

    private static final Logger logger = LoggerFactory.getLogger(PlaylistController.class);

    @Autowired
    private PlaylistService playlistService;

    @PostMapping
    public PlaylistGetDTO createPlaylist(@RequestBody PlaylistCreateDTO playlistCreateDTO) {

        // create playlist
        Playlist playlist = new Playlist();
        playlist.setName(playlistCreateDTO.getName());
        playlist = playlistService.createPlaylist(playlist, playlistCreateDTO.getOfficeId());

        // convert playlist to dto
        return PlaylistGetDTO.map(playlist);
    }

    @DeleteMapping("/{playlistId}")
    public ResponseEntity<?> deletePlaylist(@PathVariable Long playlistId) {
        playlistService.deletePlaylist(playlistId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/{playlistId}")
    public PlaylistGetDTO getPlaylist(@PathVariable Long playlistId) {
        Playlist playlist = playlistService.getPlaylist(playlistId);
        return PlaylistGetDTO.map(playlist);
    }

    @GetMapping("/all")
    public List<PlaylistGetDTO> getAllPlaylists() {
        return playlistService.getAll().stream().map(PlaylistGetDTO::map).collect(Collectors.toList());
    }
}
