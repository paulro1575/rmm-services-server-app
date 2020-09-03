package com.rmm.rmmservices.service.impl;

import com.rmm.rmmservices.model.dto.RmmServiceDTO;
import com.rmm.rmmservices.model.persistence.entities.RmmService;
import com.rmm.rmmservices.model.persistence.repository.RmmServiceRepository;
import com.rmm.rmmservices.utils.MapperUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public RmmServiceDTO update(Long id, RmmServiceDTO dtoObject) throws Exception {
        Optional<RmmService> rmmOptionalService = this.rmmServiceRepository.findById(id);
        if(rmmOptionalService.isPresent()){
            RmmService rmmService = rmmOptionalService.get();
            rmmService.setServiceName(dtoObject.getServiceName());
            return mapToDTO(this.rmmServiceRepository.save(rmmService));
        } else {
            LOGGER.warn(String.format("Service %s not found into database",
                    dtoObject.toString()));
            throw new NoSuchElementException(String.format("Service: %S not found on the database",
                    dtoObject.getServiceName()));
        }
    }

    @Override
    public RmmService mapTo(RmmServiceDTO dtoObject) {
        return MapperUtils.unmapRmmService(dtoObject);
    }

    @Override
    public RmmServiceDTO mapToDTO(RmmService domainObject) {
        return MapperUtils.mapRmmService(domainObject);
    }

    @Override
    public Optional<RmmService> findExisting(RmmServiceDTO dtoObject) {
        return this.rmmServiceRepository.findByRmmServiceName(dtoObject.getServiceName());
    }
}
