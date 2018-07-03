package com.guidewire.signagecenter.service;

import com.guidewire.signagecenter.exception.ResourceNotFoundException;
import com.guidewire.signagecenter.model.Office;
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

    public Office createOffice(Office office) {
        return officeRepository.save(office);
    }

    public void deleteOffice(Long officeId) {
        Office office = getOffice(officeId);
        officeRepository.delete(office);
    }

    public Office getOffice(Long officeId) {
        return officeRepository.findById(officeId).orElseThrow(() ->
                new ResourceNotFoundException("Office", "id", officeId));
    }

    public List<Office> getAll() {
        return officeRepository.findAll();
    }
}
