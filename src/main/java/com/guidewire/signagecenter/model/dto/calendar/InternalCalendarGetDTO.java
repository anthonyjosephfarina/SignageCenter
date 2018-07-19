package com.guidewire.signagecenter.model.dto.calendar;

import com.guidewire.signagecenter.model.db.calendar.InternalCalendarEntity;

import java.util.List;
import java.util.stream.Collectors;
/**
 * DTO for InternalCalendar .
 *
 * @author
 */
public class InternalCalendarGetDTO extends AbstractCalendarGetDTO {

    /**
     * Map InternalCalendarEntity to InternalCalendarGetDTO
     *
     * @param cal (InternalCalendarEntity)
     * @return InternalCalendarGetDTO
     */
    public static InternalCalendarGetDTO map(InternalCalendarEntity cal) {
        InternalCalendarGetDTO internalCalendarGetDTO = new InternalCalendarGetDTO();
        internalCalendarGetDTO.setId(cal.getId());
        internalCalendarGetDTO.setName(cal.getName());
        internalCalendarGetDTO.setDescription(cal.getDescription());
        internalCalendarGetDTO.setType(cal.getType());
        internalCalendarGetDTO.setOfficeId(cal.getOffice().getId());
        internalCalendarGetDTO.setOfficeName(cal.getOffice().getName());
        internalCalendarGetDTO.setCreatedAt(cal.getCreatedAt());

        // events
        List<CalendarEventGetDTO> events = cal.getEvents().stream()
                .map(CalendarEventGetDTO::map).collect(Collectors.toList());
        internalCalendarGetDTO.setEvents(events);

        return internalCalendarGetDTO;
    }
}
