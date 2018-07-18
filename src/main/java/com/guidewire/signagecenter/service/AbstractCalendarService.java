package com.guidewire.signagecenter.service;

import com.guidewire.signagecenter.exception.ResourceNotFoundException;
import com.guidewire.signagecenter.model.db.calendar.AbstractCalendarEntity;
import com.guidewire.signagecenter.repository.AbstractCalendarRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AbstractCalendarService {

    private static final Logger logger = LoggerFactory.getLogger(AbstractCalendarService.class);

    @Autowired
    private AbstractCalendarRepository abstractCalendarRepository;

    public AbstractCalendarEntity getCalendar(Long calendarId) {
        return abstractCalendarRepository.findById(calendarId).orElseThrow(() ->
                new ResourceNotFoundException("AbstractCalendarEntity", "id", calendarId));
    }

    public List<AbstractCalendarEntity> getAll() {
        return abstractCalendarRepository.findAll();
    }

    public List<AbstractCalendarEntity> getAllByOffice(Long officeId) {
        return abstractCalendarRepository.findByOfficeId(officeId);
    }

    public void deleteCalendar(Long calendarId) {
        AbstractCalendarEntity calendar = getCalendar(calendarId);
        abstractCalendarRepository.delete(calendar);
    }
}
