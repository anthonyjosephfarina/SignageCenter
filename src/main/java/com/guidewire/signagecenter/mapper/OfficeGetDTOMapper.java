package com.guidewire.signagecenter.mapper;

import com.guidewire.signagecenter.model.db.OfficeEntity;
import com.guidewire.signagecenter.model.dto.OfficeGetDTO;
import org.springframework.stereotype.Component;

@Component
public class OfficeGetDTOMapper implements DTOMapper<OfficeEntity, OfficeGetDTO> {

    @Override
    public OfficeGetDTO mapToDTO(OfficeEntity officeEntity) {
        OfficeGetDTO officeGetDTO = new OfficeGetDTO();
        officeGetDTO.setId(officeEntity.getId());
        officeGetDTO.setName(officeEntity.getName());
        officeGetDTO.setCreatedAt(officeEntity.getCreatedAt());
        return officeGetDTO;
    }

    @Override
    public OfficeEntity mapFromDTO(OfficeGetDTO officeGetDTO) {
        return null;
    }
}
