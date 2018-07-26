package com.guidewire.signagecenter.repository;

import com.guidewire.signagecenter.model.db.PlaylistEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigInteger;
import java.util.List;

public interface PlaylistRepository extends JpaRepository<PlaylistEntity, Long> {

    String DISTINCT_PLAYLIST_CALENDAR_QUERY = "select DISTINCT(p.id) from PLAYLIST p " +
            "left join ABSTRACT_SLIDE s on p.id = s.playlist_id " +
            "left join ABSTRACT_SLIDE_CALENDARS c on s.id = c.calendar_slide_entity_id " +
            "where s.slide_type = 'CALENDAR' and c.calendars_id = '1'";

    /**
     * Returns distinct playlist entity IDs that contain a calendar slide
     * that has the specified calendar associated with it.
     * <p>
     * Note: Hibernate returns BigInteger for ID column
     *
     * @param calendarId
     * @return
     */
    @Query(value = DISTINCT_PLAYLIST_CALENDAR_QUERY, nativeQuery = true)
    List<BigInteger> findDistinctByCalendarId(Long calendarId);
}
