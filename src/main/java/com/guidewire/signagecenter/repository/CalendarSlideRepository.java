package com.guidewire.signagecenter.repository;

import com.guidewire.signagecenter.model.db.slide.CalendarSlide;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CalendarSlideRepository extends JpaRepository<CalendarSlide, Long> {
}
