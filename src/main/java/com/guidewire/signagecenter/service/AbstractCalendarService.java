package com.guidewire.signagecenter.service;

import com.guidewire.signagecenter.exception.ResourceNotFoundException;
import com.guidewire.signagecenter.model.calendar.AbstractCalendar;
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

    public AbstractCalendar getCalendar(Long calendarId) {
        return abstractCalendarRepository.findById(calendarId).orElseThrow(() ->
                new ResourceNotFoundException("AbstractCalendar", "id", calendarId));
    }

    public List<AbstractCalendar> getAll() {
        return abstractCalendarRepository.findAll();
    }

    public List<AbstractCalendar> getAllByOffice(Long officeId) {
        return abstractCalendarRepository.findByOfficeId(officeId);
    }

    public void deleteCalendar(Long calendarId) {
        AbstractCalendar calendar = getCalendar(calendarId);
        abstractCalendarRepository.delete(calendar);
    }
}
