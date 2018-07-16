package com.guidewire.signagecenter.model.calendar;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.guidewire.signagecenter.model.Office;
import com.guidewire.signagecenter.model.audit.DateAuditable;
import org.hibernate.type.CalendarType;

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
    @Column(length = 30)
    private CalendarType type;

    @NotBlank
    @Size(max = 100)
    private String name;

    @JsonManagedReference
    @ManyToOne
    private Office office;
}
