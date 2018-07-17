package com.guidewire.signagecenter.model.dto.calendar;

import com.guidewire.signagecenter.model.db.calendar.InternalCalendar;

import java.util.List;
import java.util.stream.Collectors;

public class InternalCalendarGetDTO extends AbstractCalendarGetDTO {

    /**
     * Map InternalCalendar to InternalCalendarGetDTO
     *
     * @param cal (InternalCalendar)
     * @return InternalCalendarGetDTO
     */
    public static InternalCalendarGetDTO map(InternalCalendar cal) {
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
