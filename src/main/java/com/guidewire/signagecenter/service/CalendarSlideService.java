package com.guidewire.signagecenter.service;

import com.guidewire.signagecenter.messaging.MessageType;
import com.guidewire.signagecenter.messaging.MessagingService;
import com.guidewire.signagecenter.messaging.payload.SlideMessage;
import com.guidewire.signagecenter.model.db.PlaylistEntity;
import com.guidewire.signagecenter.model.db.calendar.AbstractCalendarEntity;
import com.guidewire.signagecenter.model.db.slide.CalendarSlideEntity;
import com.guidewire.signagecenter.repository.CalendarSlideRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
/**
 * CalendarSlideService
 * @author
 */
@Primary
@Service
public class CalendarSlideService extends MessagingService {

    /**
     * The Logger for CalendarSlideService.
     */
    private static final Logger logger = LoggerFactory.getLogger(CalendarSlideService.class);

    /**
     *  Inject CalendarSlideRepository.
     */
    @Autowired
    private CalendarSlideRepository calendarSlideRepository;

    /**
     *  Inject PlaylistService.
     */
    @Autowired
    private PlaylistService playlistService;

    /**
     *  Inject AbstractCalendarService.
     */
    @Autowired
    private AbstractCalendarService abstractCalendarService;

    /**
     * Creates the CalendarSlideEntity .
     * @param calendarSlide <code>CalendarSlideEntity</code>.
     * @param playlistId <code>Long</code>.
     * @param calendarIds <code>Long</code>.
     * @return CalendarSlideEntity.
     * @throws
     */
    public CalendarSlideEntity createCalendarSlide(CalendarSlideEntity calendarSlide, Long playlistId, List<Long> calendarIds) {

        PlaylistEntity playlistEntity = playlistService.getPlaylist(playlistId);
        calendarSlide.setPlaylistEntity(playlistEntity);

        List<AbstractCalendarEntity> calendars = calendarIds.stream()
                .map(id -> abstractCalendarService.getCalendar(id)).collect(Collectors.toList());
        calendarSlide.setCalendars(calendars);

        return calendarSlideRepository.save(calendarSlide);
    }

    /**
     * Retrieves all  values from the  CalendarSlideEntity  in database.
     *  @return List<CalendarSlideEntity> .
     *  @throws
     */
    public List<CalendarSlideEntity> getAll() {
        return calendarSlideRepository.findAll();
    }

    public void addCreateMessage(CalendarSlideEntity calendarSlide) {
        String destination = "/topic/playlist-" + calendarSlide.getPlaylist().getId();
        SlideMessage payload = new SlideMessage(calendarSlide.getId(), MessageType.SLIDE_ADD);

        this.sendMessage(destination, payload);
    }

    public void addUpdateMessage(CalendarSlideEntity calendarSlide) {
        String destination = "/topic/playlist-" + calendarSlide.getPlaylist().getId();
        SlideMessage payload = new SlideMessage(calendarSlide.getId(), MessageType.SLIDE_UPDATE);

        this.sendMessage(destination, payload);
    }

    public void addDeleteMessage(CalendarSlideEntity calendarSlide) {
        String destination = "/topic/playlist-" + calendarSlide.getPlaylist().getId();
        SlideMessage payload = new SlideMessage(calendarSlide.getId(), MessageType.SLIDE_DELETE);

        this.sendMessage(destination, payload);
    }
}
