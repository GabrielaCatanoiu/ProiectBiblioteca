package proiect.ProiectBiblioteca.servicesTests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import proiect.ProiectBiblioteca.dto.CityDTO;
import proiect.ProiectBiblioteca.dto.PublishingHouseDTO;
import proiect.ProiectBiblioteca.entity.City;
import proiect.ProiectBiblioteca.entity.PublishingHouse;
import proiect.ProiectBiblioteca.exceptions.PublishingHouseNotFoundException;
import proiect.ProiectBiblioteca.mapper.PublishingHouseMapper;
import proiect.ProiectBiblioteca.repositories.CityRepository;
import proiect.ProiectBiblioteca.repositories.PublishingHouseRepository;
import proiect.ProiectBiblioteca.services.PublishingHouseService;
import proiect.ProiectBiblioteca.utils.PublishingHouseMocks;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static proiect.ProiectBiblioteca.constants.ProjectConstants.PUBLISHING_HOUSE_NOT_FOUND;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class PublishingHouseServiceTests {

    @InjectMocks
    PublishingHouseService publishingHouseService;

    @Mock
    CityRepository cityRepository;

    @Mock
    PublishingHouseMapper publishingHouseMapper;

    @Mock
    PublishingHouseRepository publishingHouseRepository;

    PublishingHouse publishingHouse;
    PublishingHouseDTO publishingHouseDTO;
    City city;
    CityDTO cityDTO;

    @Test
    public void testGetAllPublishingHouse(){

        //GIVEN
        publishingHouse = PublishingHouseMocks.mockPublishingHouse();

        List<PublishingHouse> publishingHouses = new ArrayList<>();
        publishingHouses.add(publishingHouse);

        //WHEN
        when(publishingHouseRepository.findAll()).thenReturn(publishingHouses);

        List<PublishingHouse> result = publishingHouseService.getAllPublishingHouse();

        //THEN
        assertEquals(result,publishingHouses);
        assertThat(result).isNotNull();
    }

    @Test
    public void testGetPublishingHouse(){

        //GIVEN
        publishingHouse = PublishingHouseMocks.mockPublishingHouse();

        //WHEN
        when(publishingHouseRepository.findById(publishingHouse.getId())).thenReturn(Optional.ofNullable(publishingHouse));

        Optional<PublishingHouse> result = publishingHouseService.getPublishById(publishingHouse.getId());

        //THEN
        assertEquals(result,Optional.ofNullable(publishingHouse));
        assertThat(result).isNotNull();
    }

    @Test
    public void testGetPublishByName(){

        //GIVEN
        publishingHouse = PublishingHouseMocks.mockPublishingHouse();
        publishingHouseDTO = PublishingHouseMocks.mockPublishingHouseDTO();

        List<PublishingHouse> publishingHouses = new ArrayList<>();
        publishingHouses.add(publishingHouse);

        List<PublishingHouseDTO> publishingHouseDTOS = new ArrayList<>();
        publishingHouseDTOS.add(publishingHouseDTO);

        //WHEN
        when(publishingHouseRepository.findByPublishing_name(publishingHouse.getPublishing_name())).thenReturn(publishingHouses);
        when(publishingHouseMapper.mapToPublishingHouseDTO(publishingHouse)).thenReturn(publishingHouseDTO);

        //THEN
        List<PublishingHouseDTO> result = publishingHouseService.getPublishByName(publishingHouse.getPublishing_name());
        assertEquals(result,publishingHouseDTOS);
        assertThat(result).isNotNull();
    }

    @Test
    public void testPublishingHouseException() {
        //GIVEN
        publishingHouse = null;

        List<PublishingHouse> publishingHouses = new ArrayList<>();

        //WHEN
        when(publishingHouseRepository.findByPublishing_name("Astra")).thenReturn(publishingHouses);

        //THEN
        PublishingHouseNotFoundException publishingHouseNotFoundException = assertThrows(PublishingHouseNotFoundException.class, () -> publishingHouseService.getPublishByName("Astra"));
        assertEquals(String.format(PUBLISHING_HOUSE_NOT_FOUND, "Astra"), publishingHouseNotFoundException.getMessage());
    }

    @Test
    public void testDeletePublishingHouse(){

        //GIVEN
        publishingHouse = PublishingHouseMocks.mockPublishingHouse();

        //WHEN
        when(publishingHouseRepository.findById(publishingHouse.getId())).thenReturn(Optional.ofNullable(publishingHouse));

        boolean result = publishingHouseService.delete(publishingHouse.getId());

        //THEN
        assertTrue(result);
    }

    @Test
    public void testAddPublishingHouse(){

        //GIVEN
        publishingHouse = PublishingHouseMocks.mockPublishingHouse();
        publishingHouseDTO = PublishingHouseMocks.mockPublishingHouseDTO();

        //WHEN
        when(publishingHouseMapper.mapToPublishingHouse(publishingHouseDTO)).thenReturn(publishingHouse);
        when(cityRepository.findById(publishingHouseDTO.getCityDTO().getId())).thenReturn(Optional.ofNullable(publishingHouse.getCity()));

        when(publishingHouseRepository.save(publishingHouse)).thenReturn(publishingHouse);
        when(publishingHouseMapper.mapToPublishingHouseDTO(publishingHouse)).thenReturn(publishingHouseDTO);
        when(publishingHouseMapper.mapToPublishingHouse(publishingHouseDTO)).thenReturn(publishingHouse);

        PublishingHouseDTO result = publishingHouseService.addPublishingHouse(publishingHouseDTO);

        //THEN
        assertTrue(result.getPublishing_name().equals(publishingHouseDTO.getPublishing_name()));
        assertThat(result.getPublishing_name()).isNotNull();
        assertNotNull(result);
        verifyNoMoreInteractions(publishingHouseRepository,publishingHouseMapper);
    }
}
