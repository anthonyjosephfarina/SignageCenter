package com.guidewire.signagecenter.service;

import com.guidewire.signagecenter.exception.ResourceNotFoundException;
import com.guidewire.signagecenter.model.Office;
import com.guidewire.signagecenter.model.Playlist;
import com.guidewire.signagecenter.repository.PlaylistRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlaylistService {

    private static final Logger logger = LoggerFactory.getLogger(PlaylistService.class);

    @Autowired
    private PlaylistRepository playlistRepository;

    @Autowired
    private OfficeService officeService;

    public Playlist createPlaylist(Playlist playlist, Long officeId) {

        Office office = officeService.getOffice(officeId);
        playlist.setOffice(office);

        return playlistRepository.save(playlist);
    }

    public Playlist getPlaylist(Long playlistId) {
        return playlistRepository.findById(playlistId).orElseThrow(() ->
                new ResourceNotFoundException("Playlist", "id", playlistId));
    }

    public List<Playlist> getAll() {
        return playlistRepository.findAll();
    }

    public void deletePlaylist(Long playlistId) {
        playlistRepository.deleteById(playlistId);
    }
}
