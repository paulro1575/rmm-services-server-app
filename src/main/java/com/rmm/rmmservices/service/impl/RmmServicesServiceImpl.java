package com.rmm.rmmservices.service.impl;

import com.rmm.rmmservices.exceptions.DatabaseException;
import com.rmm.rmmservices.model.dto.RmmServiceDTO;
import com.rmm.rmmservices.model.persistence.entities.RmmService;
import com.rmm.rmmservices.model.persistence.repository.RmmServiceRepository;
import com.rmm.rmmservices.utils.MapperUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * @author Paul Rodríguez-Ch
 */
@Service("rmmServicesServiceImpl")
public class RmmServicesServiceImpl extends GeneralCRUDServiceImpl<RmmService, RmmServiceDTO>{


    @Autowired
    private RmmServiceRepository rmmServiceRepository;
    private final Logger LOGGER = LoggerFactory.getLogger(CustomerServicesServiceImpl.class);


    @Override
    public RmmServiceDTO update(Long id, RmmServiceDTO serviceToUpdateDto) throws DatabaseException, NoSuchElementException {
        Optional<RmmService> rmmOptionalActualService = this.rmmServiceRepository.findById(id);
        if(rmmOptionalActualService.isPresent()){
            Optional<RmmService> rmmOptionalServiceToUpdate = this.rmmServiceRepository
                    .findByRmmServiceName(serviceToUpdateDto.getServiceName());
            if (rmmOptionalServiceToUpdate.isPresent()) {
                throw new DatabaseException(String.format("The new object %s already exists into database",
                        serviceToUpdateDto.getServiceName()));
            } else {
                RmmService rmmService = rmmOptionalActualService.get();
                rmmService.setServiceName(serviceToUpdateDto.getServiceName());
                return mapToDTO(this.rmmServiceRepository.save(rmmService));
            }
        } else {
            throw new NoSuchElementException(String.format("Service %S was not found into database",
                    serviceToUpdateDto.getServiceName()));
        }
    }


    @Override
    public void deleteByCustomer(Long servicesId, String username) throws DatabaseException, UnsupportedOperationException {
        throw new UnsupportedOperationException("Unsupported Operation");
    }


    @Override
    public List<RmmServiceDTO> findAll(String customerName) throws UnsupportedOperationException {
        throw new UnsupportedOperationException("Unsupported Operation");
    }


    @Override
    public RmmService mapTo(RmmServiceDTO serviceDto) {
        return MapperUtils.unmapRmmService(serviceDto);
    }


    @Override
    public RmmServiceDTO mapToDTO(RmmService service) {
        return MapperUtils.mapRmmService(service);
    }


    @Override
    public Optional<RmmService> findExisting(RmmServiceDTO serviceDTO) {
        return this.rmmServiceRepository.findByRmmServiceName(serviceDTO.getServiceName());
    }
}
