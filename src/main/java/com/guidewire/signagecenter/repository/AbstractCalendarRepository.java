package com.guidewire.signagecenter.repository;

import com.guidewire.signagecenter.model.calendar.AbstractCalendar;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AbstractCalendarRepository extends JpaRepository<AbstractCalendar, Long> {

    List<AbstractCalendar> findByOfficeId(Long officeId);

}
