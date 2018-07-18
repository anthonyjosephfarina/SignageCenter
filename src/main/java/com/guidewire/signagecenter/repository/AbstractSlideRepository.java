package com.guidewire.signagecenter.repository;

import com.guidewire.signagecenter.model.db.slide.AbstractSlideEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AbstractSlideRepository extends JpaRepository<AbstractSlideEntity, Long> {

    List<AbstractSlideEntity> findByPlaylistId(Long playlistId);

}
