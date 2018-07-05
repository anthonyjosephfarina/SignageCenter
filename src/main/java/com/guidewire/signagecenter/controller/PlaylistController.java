package com.guidewire.signagecenter.controller;

import com.guidewire.signagecenter.model.Playlist;
import com.guidewire.signagecenter.model.dto.PlaylistCreateDTO;
import com.guidewire.signagecenter.service.PlaylistService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/playlist")
public class PlaylistController {

    private static final Logger logger = LoggerFactory.getLogger(PlaylistController.class);

    @Autowired
    private PlaylistService playlistService;

    @PostMapping
    public Long createPlaylist(@RequestBody PlaylistCreateDTO playlistCreateDTO) {

        Playlist playlist = new Playlist();
        playlist.setName(playlistCreateDTO.getName());

        return playlistService.createPlaylist(playlist, playlistCreateDTO.getOfficeId()).getId();
    }

    @CrossOrigin
    @DeleteMapping("/{playlistId}")
    public void deletePlaylist(@PathVariable Long playlistId){
        playlistService.deletePlaylist(playlistId);
    }
    @GetMapping("/{playlistId}")
    public Playlist getPlaylist(@PathVariable Long playlistId) {
        return playlistService.getPlaylist(playlistId);
    }

    @GetMapping("/all")
    public List<Playlist> getAllPlaylists() {
        return playlistService.getAll();
    }
}
