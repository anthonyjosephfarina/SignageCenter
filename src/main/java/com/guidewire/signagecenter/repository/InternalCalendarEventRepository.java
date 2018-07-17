package com.guidewire.signagecenter.repository;

import com.guidewire.signagecenter.model.db.calendar.InternalCalendarEvent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InternalCalendarEventRepository extends JpaRepository<InternalCalendarEvent, Long> {
}
