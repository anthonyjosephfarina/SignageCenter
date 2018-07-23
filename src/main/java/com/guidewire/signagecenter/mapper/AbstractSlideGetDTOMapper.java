package com.guidewire.signagecenter.mapper;

import com.guidewire.signagecenter.model.db.calendar.AbstractCalendarEntity;
import com.guidewire.signagecenter.model.db.calendar.InternalCalendarEntity;
import com.guidewire.signagecenter.model.db.slide.*;
import com.guidewire.signagecenter.model.dto.calendar.CalendarEventGetDTO;
import com.guidewire.signagecenter.model.dto.slide.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class AbstractSlideGetDTOMapper implements DTOMapper<AbstractSlideEntity, AbstractSlideGetDTO> {

    @Override
    public AbstractSlideGetDTO mapToDTO(AbstractSlideEntity abstractSlideEntity) {
        AbstractSlideGetDTO slideDTO = null;

        switch (abstractSlideEntity.getSlideType()) {
            case MAP: {
                slideDTO = MapSlideGetDTO.map((MapSlideEntity) abstractSlideEntity);
                break;
            }
            case CALENDAR: {
                CalendarSlideEntity calendarSlide = (CalendarSlideEntity) abstractSlideEntity;

                // combine events from the calendars
                List<CalendarEventGetDTO> events = new ArrayList<>();
                for (AbstractCalendarEntity calendar : calendarSlide.getCalendars()) {
                    switch (calendar.getType()) {
                        case INTERNAL: {
                            InternalCalendarEntity internalCalendarEntity = (InternalCalendarEntity) calendar;
                            events.addAll(internalCalendarEntity.getEvents().stream().map(CalendarEventGetDTO::map).collect(Collectors.toList()));
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
                }
                slideDTO = CalendarSlideGetDTO.map(calendarSlide, events);
                break;
            }
            case IMAGE: {
                slideDTO = ImageSlideGetDTO.map((ImageSlideEntity) abstractSlideEntity);
                break;
            }
            case WEATHER: {
                slideDTO = WeatherSlideGetDTO.map((WeatherSlideEntity) abstractSlideEntity);
                break;
            }
        }

        return slideDTO;
    }

    @Override
    public AbstractSlideEntity mapFromDTO(AbstractSlideGetDTO abstractSlideGetDTO) {
        return null;
    }
}
