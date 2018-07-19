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
/**
 * InternalCalendarService
 * @author
 */
@Service
public class InternalCalendarService {
    /**
     * The Logger for InternalCalendarService.
     */
    private static final Logger logger = LoggerFactory.getLogger(InternalCalendarService.class);

    /**
     * Inject InternalCalendarRepository.
     */
    @Autowired
    private InternalCalendarRepository internalCalendarRepository;

    /**
     * Inject OfficeService.
     */
    @Autowired
    private OfficeService officeService;

    /**
     * Retrieve the InternalCalendarEntity .
     * @param calendarId <code>Long</code>.
     * @return InternalCalendarEntity.
     * @throws
     */
    public InternalCalendarEntity getCalendar(Long calendarId) {
        return internalCalendarRepository.findById(calendarId).orElseThrow(() ->
                new ResourceNotFoundException("InternalCalendarEntity", "id", calendarId));
    }

    /**
     *  Retrieves all  values from the  InternalCalendarEntity  in database.
     *  @return List<InternalCalendarEntity> .
     *  @throws
     */
    public List<InternalCalendarEntity> getAll() {
        return internalCalendarRepository.findAll();
    }

    /**
     * Creates the InternalCalendarEntity .
     * @param calendar <code>InternalCalendarEntity</code>.
     * @param officeId <code>Long</code>.
     * @return InternalCalendarEntity.
     * @throws
     */
    public InternalCalendarEntity create(InternalCalendarEntity calendar, Long officeId) {

        OfficeEntity officeEntity = officeService.getOffice(officeId);
        calendar.setOffice(officeEntity);

        return internalCalendarRepository.save(calendar);
    }
    /**
     * Deletes the InternalCalendarEntity value matching calendarId.
     * @param calendarId <code>Long</code>.
     * @throws
     */
    public void delete(Long calendarId) {
        InternalCalendarEntity calendar = getCalendar(calendarId);
        internalCalendarRepository.delete(calendar);
    }
}
