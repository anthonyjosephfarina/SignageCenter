package com.guidewire.signagecenter.repository;

import com.guidewire.signagecenter.model.db.slide.CalendarSlideEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CalendarSlideRepository extends JpaRepository<CalendarSlideEntity, Long> {
}
