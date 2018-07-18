package com.guidewire.signagecenter.model.dto;

import com.guidewire.signagecenter.model.db.OfficeEntity;

import java.time.Instant;

public class OfficeGetDTO {

    private Long id;

    private String name;

    private Instant createdAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public static OfficeGetDTO map(OfficeEntity officeEntity) {
        OfficeGetDTO officeGetDTO = new OfficeGetDTO();
        officeGetDTO.setId(officeEntity.getId());
        officeGetDTO.setName(officeEntity.getName());
        officeGetDTO.setCreatedAt(officeEntity.getCreatedAt());
        return officeGetDTO;
    }
}
