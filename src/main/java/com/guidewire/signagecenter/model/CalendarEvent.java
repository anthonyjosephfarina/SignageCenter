package com.guidewire.signagecenter.model;

import javax.persistence.*;

@Entity
public class CalendarEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(length = 30)
    private CalendarEventType type;

}
