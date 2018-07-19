package com.guidewire.signagecenter.service;

import com.guidewire.signagecenter.exception.ResourceNotFoundException;
import com.guidewire.signagecenter.model.db.OfficeEntity;
import com.guidewire.signagecenter.repository.OfficeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * OfficeService retrieves the saved office details and save the newly created office
 * details.
 *
 * @author
 */
@Service
public class OfficeService {

    /**
     * The Logger for OfficeService.
     */
    private static final Logger logger = LoggerFactory.getLogger(OfficeService.class);

    /**
     * OfficeRepository.
     */
    @Autowired
    private OfficeRepository officeRepository;

    /**
     * Creates the  officeEntity
     * @param officeEntity <code>OfficeEntity</code>.
     * @return OfficeEntity.
     * @throws
     */
    public OfficeEntity createOffice(OfficeEntity officeEntity) {

        return officeRepository.save(officeEntity);
    }

    /**
     * Deletes the OfficeEntity value matching officeId.
     * @param officeId <code>Long</code>.
     * @throws
     */
    public void deleteOffice(Long officeId) {
        OfficeEntity officeEntity = getOffice(officeId);
        officeRepository.delete(officeEntity);
    }

    /**
     * Retrieves the OfficeEntity .
     * @param officeId <code>Long</code>.
     * @return OfficeEntity .
     * @throws
     */
    public OfficeEntity getOffice(Long officeId) {
        return officeRepository.findById(officeId).orElseThrow(() ->
                new ResourceNotFoundException("OfficeEntity", "id", officeId));
    }

    /**
     *  Retrieves all  values from the  OfficeEntity  in database.
     *  @return List<OfficeEntity> .
     *  @throws
     */
    public List<OfficeEntity> getAll() {
        return officeRepository.findAll();
    }
}
