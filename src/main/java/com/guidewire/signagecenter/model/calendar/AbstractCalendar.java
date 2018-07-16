package com.guidewire.signagecenter.model.calendar;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.guidewire.signagecenter.model.Office;
import com.guidewire.signagecenter.model.audit.DateAuditable;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)
public abstract class AbstractCalendar extends DateAuditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(length = 30, nullable = false, insertable = false, updatable = false)
    private CalendarType type;

    @NotBlank
    @Size(max = 100)
    private String name;

    @JsonManagedReference
    @ManyToOne
    private Office office;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CalendarType getType() {
        return type;
    }

    public void setType(CalendarType type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Office getOffice() {
        return office;
    }

    public void setOffice(Office office) {
        this.office = office;
    }
}
