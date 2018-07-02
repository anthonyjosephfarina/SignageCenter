package com.guidewire.signagecenter.repository;

import com.guidewire.signagecenter.model.slide.AbstractSlide;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AbstractSlideRepository extends JpaRepository<AbstractSlide, Long> {

    List<AbstractSlide> findByPlaylistId(Long playlistId);

}
