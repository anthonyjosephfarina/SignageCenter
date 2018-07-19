package com.guidewire.signagecenter.model.dto.slide;

import com.guidewire.signagecenter.model.db.slide.CalendarSlideEntity;
import com.guidewire.signagecenter.model.dto.calendar.CalendarEventGetDTO;

import java.util.ArrayList;
import java.util.List;
/**
 * DTO for CalendarSlideGet.
 *
 * @author
 */
public class CalendarSlideGetDTO extends AbstractSlideGetDTO {

    private List<CalendarEventGetDTO> events = new ArrayList<>();

    public List<CalendarEventGetDTO> getEvents() {
        return events;
    }

    public void setEvents(List<CalendarEventGetDTO> events) {
        this.events = events;
    }

    public static CalendarSlideGetDTO map(CalendarSlideEntity calendarSlide) {
        CalendarSlideGetDTO calendarSlideGetDTO = new CalendarSlideGetDTO();
        calendarSlideGetDTO.setId(calendarSlide.getId());
        calendarSlideGetDTO.setName(calendarSlide.getName());
        calendarSlideGetDTO.setSlideType(calendarSlide.getSlideType());
        calendarSlideGetDTO.setDuration(calendarSlide.getDuration());
        calendarSlideGetDTO.setStartDate(calendarSlide.getStartDate());
        calendarSlideGetDTO.setEndDate(calendarSlide.getEndDate());
        return calendarSlideGetDTO;
    }

    public static CalendarSlideGetDTO map(CalendarSlideEntity calendarSlide, List<CalendarEventGetDTO> events) {
        CalendarSlideGetDTO calendarSlideGetDTO = new CalendarSlideGetDTO();
        calendarSlideGetDTO.setId(calendarSlide.getId());
        calendarSlideGetDTO.setName(calendarSlide.getName());
        calendarSlideGetDTO.setSlideType(calendarSlide.getSlideType());
        calendarSlideGetDTO.setDuration(calendarSlide.getDuration());
        calendarSlideGetDTO.setStartDate(calendarSlide.getStartDate());
        calendarSlideGetDTO.setEndDate(calendarSlide.getEndDate());
        calendarSlideGetDTO.setEvents(events);
        return calendarSlideGetDTO;
    }
}
