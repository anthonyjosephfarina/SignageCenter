package com.guidewire.signagecenter.controller;

import com.guidewire.signagecenter.mapper.OfficeCreateDTOMapper;
import com.guidewire.signagecenter.mapper.OfficeGetDTOMapper;
import com.guidewire.signagecenter.model.db.OfficeEntity;
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

    @Autowired
    private OfficeCreateDTOMapper officeCreateDTOMapper;

    @Autowired
    private OfficeGetDTOMapper officeGetDTOMapper;

    @PostMapping
    public OfficeGetDTO createOffice(@RequestBody OfficeCreateDTO officeCreateDTO) {

        // create officeEntity
        OfficeEntity officeEntity = officeCreateDTOMapper.mapFromDTO(officeCreateDTO);
        officeEntity = officeService.createOffice(officeEntity);

        return officeGetDTOMapper.mapToDTO(officeEntity);
    }

    @DeleteMapping("/{officeId}")
    public ResponseEntity<?> deleteOffice(@PathVariable Long officeId) {
        officeService.deleteOffice(officeId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/{officeId}")
    public OfficeGetDTO getOffice(@PathVariable Long officeId) {
        OfficeEntity officeEntity = officeService.getOffice(officeId);
        return officeGetDTOMapper.mapToDTO(officeEntity);
    }

    @GetMapping("/all")
    public List<OfficeGetDTO> getAllOffices() {
        return officeService.getAll().stream().map(officeGetDTOMapper::mapToDTO).collect(Collectors.toList());
    }
}
