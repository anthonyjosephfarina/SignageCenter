package com.guidewire.signagecenter.controller;

import com.guidewire.signagecenter.model.Office;
import com.guidewire.signagecenter.model.dto.OfficeCreateDTO;
import com.guidewire.signagecenter.service.OfficeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/office")
public class OfficeController {

    private static final Logger logger = LoggerFactory.getLogger(OfficeController.class);

    @Autowired
    private OfficeService officeService;

    @PostMapping
    public Long createOffice(@RequestBody OfficeCreateDTO officeCreateDTO) {

        Office office = new Office();
        office.setName(officeCreateDTO.getName());

        return officeService.createOffice(office).getId();
    }

    @DeleteMapping("/{officeId}")
    public ResponseEntity<?> deleteOffice(@PathVariable Long officeId) {
        officeService.deleteOffice(officeId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/{officeId}")
    public Office getOffice(@PathVariable Long officeId) {
        return officeService.getOffice(officeId);
    }

    @GetMapping("/all")
    public List<Office> getAllOffices() {
        return officeService.getAll();
    }
}
