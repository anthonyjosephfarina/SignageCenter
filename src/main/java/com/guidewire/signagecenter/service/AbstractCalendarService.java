package com.guidewire.signagecenter.service;

import com.guidewire.signagecenter.exception.ResourceNotFoundException;
import com.guidewire.signagecenter.model.db.calendar.AbstractCalendarEntity;
import com.guidewire.signagecenter.repository.AbstractCalendarRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * AbstractCalendarService
 * @author
 */
@Service
public class AbstractCalendarService {

    /**
     * The Logger for AbstractCalendarService.
     */
    private static final Logger logger = LoggerFactory.getLogger(AbstractCalendarService.class);

    /**
     *  Inject AbstractCalendarRepository.
     */
    @Autowired
    private AbstractCalendarRepository abstractCalendarRepository;

    /**
     * Retrieve the AbstractCalendarEntity .
     * @param calendarId <code>Long</code>.
     * @return AbstractCalendarEntity.
     * @throws ResourceNotFoundException
     */
    public AbstractCalendarEntity getCalendar(Long calendarId) {
        return abstractCalendarRepository.findById(calendarId).orElseThrow(() ->
                new ResourceNotFoundException("AbstractCalendarEntity", "id", calendarId));
    }

    /**
     * Retrieves all  values from the  AbstractCalendarEntity  in database.
     *  @return List<AbstractCalendarEntity> .
     *  @throws
     */
    public List<AbstractCalendarEntity> getAll() {
        return abstractCalendarRepository.findAll();
    }

    /**
     * Retrieve the AbstractCalendarEntity .
     * @param officeId <code>Long</code>.
     * @return AbstractCalendarEntity.
     * @throws
     */
    public List<AbstractCalendarEntity> getAllByOffice(Long officeId) {
        return abstractCalendarRepository.findByOfficeId(officeId);
    }

    /**
     * Deletes the AbstractCalendarEntity value matching calendarId.
     * @param calendarId <code>Long</code>.
     * @throws
     */
    public void deleteCalendar(Long calendarId) {
        AbstractCalendarEntity calendar = getCalendar(calendarId);
        abstractCalendarRepository.delete(calendar);
    }
}
