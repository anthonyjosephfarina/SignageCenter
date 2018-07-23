package com.guidewire.signagecenter.mapper;

import com.guidewire.signagecenter.model.db.OfficeEntity;
import com.guidewire.signagecenter.model.dto.OfficeCreateDTO;
import org.springframework.stereotype.Component;

@Component
public class OfficeCreateDTOMapper implements DTOMapper<OfficeEntity, OfficeCreateDTO> {

    @Override
    public OfficeCreateDTO mapToDTO(OfficeEntity officeEntity) {
        return null;
    }

    @Override
    public OfficeEntity mapFromDTO(OfficeCreateDTO officeCreateDTO) {
        OfficeEntity officeEntity = new OfficeEntity();
        officeEntity.setName(officeCreateDTO.getName());
        return officeEntity;
    }
}
