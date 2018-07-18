package com.guidewire.signagecenter.service;

import com.guidewire.signagecenter.exception.ResourceNotFoundException;
import com.guidewire.signagecenter.model.db.OfficeEntity;
import com.guidewire.signagecenter.model.db.calendar.InternalCalendarEntity;
import com.guidewire.signagecenter.repository.InternalCalendarRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InternalCalendarService {

    private static final Logger logger = LoggerFactory.getLogger(InternalCalendarService.class);

    @Autowired
    private InternalCalendarRepository internalCalendarRepository;

    @Autowired
    private OfficeService officeService;

    public InternalCalendarEntity getCalendar(Long calendarId) {
        return internalCalendarRepository.findById(calendarId).orElseThrow(() ->
                new ResourceNotFoundException("InternalCalendarEntity", "id", calendarId));
    }

    public List<InternalCalendarEntity> getAll() {
        return internalCalendarRepository.findAll();
    }

    public InternalCalendarEntity create(InternalCalendarEntity calendar, Long officeId) {

        OfficeEntity officeEntity = officeService.getOffice(officeId);
        calendar.setOffice(officeEntity);

        return internalCalendarRepository.save(calendar);
    }

    public void delete(Long calendarId) {
        InternalCalendarEntity calendar = getCalendar(calendarId);
        internalCalendarRepository.delete(calendar);
    }
}
