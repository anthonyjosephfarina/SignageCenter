package com.guidewire.signagecenter.service;

import com.guidewire.signagecenter.exception.ResourceNotFoundException;
import com.guidewire.signagecenter.model.Office;
import com.guidewire.signagecenter.model.calendar.InternalCalendar;
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

    public InternalCalendar getCalendar(Long calendarId) {
        return internalCalendarRepository.findById(calendarId).orElseThrow(() ->
                new ResourceNotFoundException("InternalCalendar", "id", calendarId));
    }

    public List<InternalCalendar> getAll() {
        return internalCalendarRepository.findAll();
    }

    public InternalCalendar create(InternalCalendar calendar, Long officeId) {

        Office office = officeService.getOffice(officeId);
        calendar.setOffice(office);

        return internalCalendarRepository.save(calendar);
    }

    public void delete(Long calendarId) {
        InternalCalendar calendar = getCalendar(calendarId);
        internalCalendarRepository.delete(calendar);
    }
}
