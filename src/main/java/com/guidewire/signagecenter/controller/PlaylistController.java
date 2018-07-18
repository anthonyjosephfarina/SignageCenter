package com.guidewire.signagecenter.controller;

import com.guidewire.signagecenter.model.db.PlaylistEntity;
import com.guidewire.signagecenter.model.db.calendar.AbstractCalendarEntity;
import com.guidewire.signagecenter.model.db.calendar.InternalCalendarEntity;
import com.guidewire.signagecenter.model.db.slide.*;
import com.guidewire.signagecenter.model.dto.PlaylistCreateDTO;
import com.guidewire.signagecenter.model.dto.PlaylistGetDTO;
import com.guidewire.signagecenter.model.dto.PlaylistPlayDTO;
import com.guidewire.signagecenter.model.dto.calendar.CalendarEventGetDTO;
import com.guidewire.signagecenter.model.dto.slide.*;
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

    @PostMapping
    public PlaylistGetDTO createPlaylist(@RequestBody PlaylistCreateDTO playlistCreateDTO) {

        // create playlistEntity
        PlaylistEntity playlistEntity = new PlaylistEntity();
        playlistEntity.setName(playlistCreateDTO.getName());
        playlistEntity = playlistService.createPlaylist(playlistEntity, playlistCreateDTO.getOfficeId());

        // convert playlistEntity to dto
        return PlaylistGetDTO.map(playlistEntity);
    }

    @DeleteMapping("/{playlistId}")
    public ResponseEntity<?> deletePlaylist(@PathVariable Long playlistId) {
        playlistService.deletePlaylist(playlistId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/{playlistId}")
    public PlaylistGetDTO getPlaylist(@PathVariable Long playlistId) {
        PlaylistEntity playlistEntity = playlistService.getPlaylist(playlistId);
        return PlaylistGetDTO.map(playlistEntity);
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

        // gather all of the slides from the playlists
        List<AbstractSlideEntity> slides = playlistEntities.stream()
                .map(PlaylistEntity::getSlides)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());

        // convert to dtos
        List<AbstractSlideGetDTO> slideDTOs = slides.stream().map(slide -> {
            AbstractSlideGetDTO slideDTO = null;

            switch (slide.getSlideType()) {
                case MAP: {
                    slideDTO = MapSlideGetDTO.map((MapSlideEntity) slide);
                    break;
                }
                case CALENDAR: {
                    CalendarSlideEntity calendarSlide = (CalendarSlideEntity) slide;

                    // combine events from the calendars
                    List<CalendarEventGetDTO> events = new ArrayList<>();
                    for (AbstractCalendarEntity calendar : calendarSlide.getCalendars()) {
                        switch (calendar.getType()) {
                            case INTERNAL: {
                                InternalCalendarEntity internalCalendarEntity = (InternalCalendarEntity) calendar;
                                events.addAll(internalCalendarEntity.getEvents().stream().map(CalendarEventGetDTO::map).collect(Collectors.toList()));
                                break;
                            }
                            case OUTLOOK: {
                                // TODO: implement outlook apis and retrieve events
                                break;
                            }
                            case WORKDAY: {
                                // TODO: implement workday apis and retrieve events
                                break;
                            }
                            case GMAIL: {
                                // TODO: implement gmail apis and retrieve events
                                break;
                            }
                        }
                    }
                    slideDTO = CalendarSlideGetDTO.map(calendarSlide, events);
                    break;
                }
                case IMAGE: {
                    slideDTO = ImageSlideGetDTO.map((ImageSlideEntity) slide);
                    break;
                }
                case WEATHER: {
                    slideDTO = WeatherSlideGetDTO.map((WeatherSlideEntity) slide);
                    break;
                }
            }

            return slideDTO;
        }).collect(Collectors.toList());

        PlaylistPlayDTO playlistPlayDTO = new PlaylistPlayDTO();
        playlistPlayDTO.setId(mainPlaylistEntity.getId());
        playlistPlayDTO.setSlides(slideDTOs);
        return playlistPlayDTO;
    }
}
