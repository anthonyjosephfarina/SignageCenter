package com.guidewire.signagecenter.repository;

import com.guidewire.signagecenter.model.db.slide.AbstractSlideEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.Instant;
import java.util.List;

public interface AbstractSlideRepository extends JpaRepository<AbstractSlideEntity, Long> {

    // queries
    String PLAYLIST_DATE_QUERY = "select * from ABSTRACT_SLIDE s where s.playlist_id = ?1 and ((s.start_date IS NULL and s.end_date IS NULL) " +
            "or (s.start_date < ?2 and s.end_date IS NULL) or (s.start_date < ?2 and s.end_date > ?2))";

    List<AbstractSlideEntity> findByPlaylistId(Long playlistId);

    @Query(value = PLAYLIST_DATE_QUERY, nativeQuery = true)
    List<AbstractSlideEntity> findByPlaylistIdAndDate(Long playlistId, Instant date);
}
