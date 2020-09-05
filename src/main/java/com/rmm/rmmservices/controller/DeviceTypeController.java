package com.rmm.rmmservices.controller;

import com.rmm.rmmservices.exceptions.DatabaseException;
import com.rmm.rmmservices.model.dto.DeviceTypeDTO;
import com.rmm.rmmservices.model.persistence.entities.DeviceType;
import com.rmm.rmmservices.service.GeneralCRUDService;
import com.rmm.rmmservices.utils.ConfigUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;

/**
 * @author Paul Rodríguez-Ch
 */
@RestController
@RequestMapping(value = "/device-type/")
@Validated
public class DeviceTypeController extends GeneralCrudController<DeviceType, DeviceTypeDTO>{

    @Autowired
    @Qualifier("deviceTypeServiceImpl")
    private GeneralCRUDService<DeviceType, DeviceTypeDTO> deviceTypeService;
    @Autowired
    private ConfigUtils configUtils;


    @RequestMapping(produces = {MediaType.APPLICATION_JSON_VALUE},
            method = RequestMethod.POST,
            path="/")
    public ResponseEntity<Object> create(@Valid @RequestBody DeviceTypeDTO deviceTypeDTO) throws Exception {
        deviceTypeDTO.setDevicePrice(new BigDecimal(configUtils.getDeviceCost()));
        return super.create(deviceTypeDTO);
    }


    @RequestMapping(produces = {MediaType.APPLICATION_JSON_VALUE},
            method = RequestMethod.PUT,
            path="/{device_type_id}")
    public ResponseEntity<Object> update(
            @PathVariable(name="device_type_id") Long deviceTypeId,
            @Valid @RequestBody DeviceTypeDTO deviceTypeDTO
    ) throws Exception {
        return super.update(deviceTypeId, deviceTypeDTO);
    }


    @RequestMapping(produces = {MediaType.APPLICATION_JSON_VALUE},
            method = RequestMethod.GET,
            path="/")
    public ResponseEntity<Object> findAll() throws Exception {
        return super.findAll("id", Sort.Direction.ASC);
    }


    @RequestMapping(produces = {MediaType.APPLICATION_JSON_VALUE},
            method = RequestMethod.DELETE,
            path = "/{device_type_id}")
    public ResponseEntity<Object> delete(@PathVariable(name="device_type_id") Long deviceTypeId)
            throws DatabaseException {
        return super.delete(deviceTypeId);
    }
}
