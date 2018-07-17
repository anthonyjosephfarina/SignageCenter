package com.guidewire.signagecenter.service;

import com.guidewire.signagecenter.model.db.calendar.AbstractCalendar;
import com.guidewire.signagecenter.model.dto.calendar.CalendarEventGetDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CalendarEventService {

    private static final Logger logger = LoggerFactory.getLogger(CalendarEventService.class);

    public List<CalendarEventGetDTO> getEvents(AbstractCalendar calendar) {

        switch (calendar.getType()) {
            case INTERNAL: {

                break;
            }
            case OUTLOOK: {
                // TODO: implement outlook apis and retrieve events
                break;
            }
            case WORKDAY: {
                // TODO: implement workday apis and retrieve events
                break;
            }
            case GMAIL: {
                // TODO: implement gmail apis and retrieve events
                break;
            }
        }

        return null;
    }
}
