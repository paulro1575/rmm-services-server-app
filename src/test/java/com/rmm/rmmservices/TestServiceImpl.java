package com.rmm.rmmservices;

import com.rmm.rmmservices.model.dto.RmmServiceDTO;
import com.rmm.rmmservices.model.persistence.entities.RmmService;
import com.rmm.rmmservices.model.persistence.repository.RmmServiceRepository;
import com.rmm.rmmservices.service.impl.RmmServicesServiceImpl;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/**
 * @author Paul Rodríguez-Ch
 */
@RunWith(MockitoJUnitRunner.class)
public class TestServiceImpl {

    @Mock
    private RmmServiceRepository rmmServiceRepository;

    static final RmmServiceDTO rmmServiceDTO = new RmmServiceDTO(1L, "newService");

    static final RmmService expectedRmmService = new RmmService(
            1L, "newService"
    );

    @InjectMocks
    RmmServicesServiceImpl rmmServicesServiceImpl;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testRmmServiceCreation() {
        when(this.rmmServiceRepository.findById(any())).thenReturn(Optional.empty());
        when(this.rmmServiceRepository.save(any())).thenReturn(expectedRmmService);
        RmmService actualRmmService = this.rmmServicesServiceImpl.create(rmmServiceDTO);
        Assert.assertEquals(expectedRmmService.toString(), actualRmmService.toString());
    }

    @Test
    public void testRmmServiceUpdate() {
        when(this.rmmServiceRepository.save(any())).thenReturn(expectedRmmService);
        when(this.rmmServiceRepository.findById(any())).thenReturn(Optional.of(expectedRmmService));
        RmmServiceDTO actualRmmService = this.rmmServicesServiceImpl.update(1L, rmmServiceDTO);
        Assert.assertEquals(rmmServiceDTO.toString(), actualRmmService.toString());
    }

    @Test
    public void testRmmServiceFindAll() {
        RmmServiceDTO firstRmmServiceDtoToList = new RmmServiceDTO(1L, "FirstService");
        RmmServiceDTO secondRmmServiceDtoToList = new RmmServiceDTO(2L, "SecondService");
        List<RmmServiceDTO> expectedRmmServiceDtoList = new ArrayList<>();
        expectedRmmServiceDtoList.add(firstRmmServiceDtoToList);
        expectedRmmServiceDtoList.add(secondRmmServiceDtoToList);

        RmmService firstRmmServiceToList = new RmmService(1L, "FirstService");
        RmmService secondRmmServiceToList = new RmmService(2L, "SecondService");
        List<RmmService> expectedRmmServiceList = new ArrayList<>();
        expectedRmmServiceList.add(firstRmmServiceToList);
        expectedRmmServiceList.add(secondRmmServiceToList);

        when(this.rmmServiceRepository.findAll((Sort) any())).thenReturn(expectedRmmServiceList);
        List<RmmServiceDTO> actualRmmServicesToList = this.rmmServicesServiceImpl.findAll("id", Sort.Direction.ASC);
        Assert.assertEquals(actualRmmServicesToList.get(0).toString(), expectedRmmServiceDtoList.get(0).toString());
    }
}
