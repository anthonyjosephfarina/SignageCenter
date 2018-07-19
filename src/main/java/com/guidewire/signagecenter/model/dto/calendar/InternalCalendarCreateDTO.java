package com.guidewire.signagecenter.model.dto.calendar;
/**
 * DTO for InternalCalendar .
 *
 * @author
 */
public class InternalCalendarCreateDTO {

    private String name;

    private String description;

    private Long officeId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getOfficeId() {
        return officeId;
    }

    public void setOfficeId(Long officeId) {
        this.officeId = officeId;
    }
}
