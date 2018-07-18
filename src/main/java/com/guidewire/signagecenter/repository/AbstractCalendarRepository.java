package com.guidewire.signagecenter.repository;

import com.guidewire.signagecenter.model.db.calendar.AbstractCalendarEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AbstractCalendarRepository extends JpaRepository<AbstractCalendarEntity, Long> {

    List<AbstractCalendarEntity> findByOfficeId(Long officeId);

}
