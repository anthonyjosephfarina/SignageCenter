package com.guidewire.signagecenter.repository;

import com.guidewire.signagecenter.model.db.calendar.InternalCalendarEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InternalCalendarRepository extends JpaRepository<InternalCalendarEntity, Long> {

}
