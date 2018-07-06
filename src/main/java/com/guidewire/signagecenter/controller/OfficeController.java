package com.guidewire.signagecenter.controller;

import com.guidewire.signagecenter.model.Office;
import com.guidewire.signagecenter.model.dto.OfficeCreateDTO;
import com.guidewire.signagecenter.model.dto.OfficeGetDTO;
import com.guidewire.signagecenter.service.OfficeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/office")
public class OfficeController {

    private static final Logger logger = LoggerFactory.getLogger(OfficeController.class);

    @Autowired
    private OfficeService officeService;

    @PostMapping
    public OfficeGetDTO createOffice(@RequestBody OfficeCreateDTO officeCreateDTO) {

        // create office
        Office office = new Office();
        office.setName(officeCreateDTO.getName());
        office = officeService.createOffice(office);

        // convert new office object to dto
        OfficeGetDTO officeGetDTO = new OfficeGetDTO();
        officeGetDTO.setId(office.getId());
        officeGetDTO.setName(office.getName());
        officeGetDTO.setCreatedAt(office.getCreatedAt());

        return officeGetDTO;
    }

    @DeleteMapping("/{officeId}")
    public ResponseEntity<?> deleteOffice(@PathVariable Long officeId) {
        officeService.deleteOffice(officeId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/{officeId}")
    public OfficeGetDTO getOffice(@PathVariable Long officeId) {

        Office office = officeService.getOffice(officeId);

        OfficeGetDTO officeGetDTO = new OfficeGetDTO();
        officeGetDTO.setId(office.getId());
        officeGetDTO.setName(office.getName());
        officeGetDTO.setCreatedAt(office.getCreatedAt());

        return officeGetDTO;
    }

    @GetMapping("/all")
    public List<OfficeGetDTO> getAllOffices() {
        return officeService.getAll().stream().map(o -> {
            OfficeGetDTO officeGetDTO = new OfficeGetDTO();
            officeGetDTO.setId(o.getId());
            officeGetDTO.setName(o.getName());
            officeGetDTO.setCreatedAt(o.getCreatedAt());
            return officeGetDTO;
        }).collect(Collectors.toList());
    }
}
