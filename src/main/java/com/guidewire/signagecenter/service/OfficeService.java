package com.guidewire.signagecenter.service;

import com.guidewire.signagecenter.exception.ResourceNotFoundException;
import com.guidewire.signagecenter.model.db.OfficeEntity;
import com.guidewire.signagecenter.repository.OfficeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OfficeService {

    private static final Logger logger = LoggerFactory.getLogger(OfficeService.class);

    @Autowired
    private OfficeRepository officeRepository;

    public OfficeEntity createOffice(OfficeEntity officeEntity) {
        return officeRepository.save(officeEntity);
    }

    public void deleteOffice(Long officeId) {
        OfficeEntity officeEntity = getOffice(officeId);
        officeRepository.delete(officeEntity);
    }

    public OfficeEntity getOffice(Long officeId) {
        return officeRepository.findById(officeId).orElseThrow(() ->
                new ResourceNotFoundException("OfficeEntity", "id", officeId));
    }

    public List<OfficeEntity> getAll() {
        return officeRepository.findAll();
    }
}
