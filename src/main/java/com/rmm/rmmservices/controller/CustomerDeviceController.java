package com.rmm.rmmservices.controller;

import com.rmm.rmmservices.model.dto.DeviceDTO;
import com.rmm.rmmservices.model.persistence.entities.Device;
import com.rmm.rmmservices.service.GeneralCRUDService;
import com.rmm.rmmservices.utils.ResponseEntityHeaderUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author Paul Rodríguez-Ch
 */
@RestController
@RequestMapping(value = "/customer/device/")
@Validated
public class CustomerDeviceController extends GeneralCrudController<Device, DeviceDTO> {

    @Autowired
    @Qualifier("customerDeviceImpl")
    private GeneralCRUDService<Device, DeviceDTO> customerDeviceService;

    @RequestMapping(produces = {MediaType.APPLICATION_JSON_VALUE}, method = RequestMethod.POST, path="/")
    public ResponseEntity<Object> create(@Valid @RequestBody DeviceDTO deviceDTO) throws Exception {
        return super.create(deviceDTO);
    }

    @RequestMapping(produces = {MediaType.APPLICATION_JSON_VALUE}, method = RequestMethod.PUT,
            path="/{customer_device_id}")
    public ResponseEntity<Object> update(
            @PathVariable(name="customer_device_id") Long customerDeviceId,
            @Valid @RequestBody DeviceDTO deviceDTO
    ) throws Exception {
        return super.update(customerDeviceId, deviceDTO);
    }

    @RequestMapping(produces = {MediaType.APPLICATION_JSON_VALUE}, method = RequestMethod.GET, path="/")
    public ResponseEntity<Object> findAll() throws Exception {
        return super.findAll("id", Sort.Direction.ASC);
    }

    @RequestMapping(produces = {MediaType.APPLICATION_JSON_VALUE},
            method = RequestMethod.DELETE,
            path = "/{customer_device_id}")
    public ResponseEntity<Object> delete(@PathVariable(name="customer_device_id") Long customerDeviceId) {
        super.delete(customerDeviceId);
        return new ResponseEntity<>(ResponseEntityHeaderUtils.getJsonContentTypeHeaders(), HttpStatus.OK);
    }
}
