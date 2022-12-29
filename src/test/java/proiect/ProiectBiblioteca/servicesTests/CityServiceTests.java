package proiect.ProiectBiblioteca.servicesTests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import proiect.ProiectBiblioteca.dto.CityDTO;
import proiect.ProiectBiblioteca.entity.City;
import proiect.ProiectBiblioteca.exceptions.CityNotFloundException;
import proiect.ProiectBiblioteca.mapper.CityMapper;
import proiect.ProiectBiblioteca.repositories.CityRepository;
import proiect.ProiectBiblioteca.services.CityService;
import proiect.ProiectBiblioteca.utils.CityMocks;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static proiect.ProiectBiblioteca.constants.ProjectConstants.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class CityServiceTests {

    @InjectMocks
    CityService cityService;

    @Mock
    CityRepository cityRepository;

    @Mock
    CityMapper cityMapper;

    City city;

    CityDTO cityDTO;

    @Test
    public void testAddCity(){

        //GIVEN
        city = CityMocks.mockCity();
        cityDTO = CityMocks.mockCityDTO();

        //WHEN
        when(cityRepository.save(city)).thenReturn(city);
        when(cityMapper.mapToCityDTO(city)).thenReturn(cityDTO);
        when(cityMapper.mapToCity(cityDTO)).thenReturn(city);

        CityDTO result = cityService.addCity(cityDTO);

        //THEN
        assertTrue(result.getCity_name().equals(cityDTO.getCity_name()));
        assertThat(result.getDistrict()).isNotNull();
        assertNotNull(result);
        verifyNoMoreInteractions(cityRepository,cityMapper);
    }

    @Test
    public void testGetCity(){

        //GIVEN
        city = CityMocks.mockCity();

        //WHEN
        when(cityRepository.findById(city.getId())).thenReturn(Optional.ofNullable(city));

        Optional<City> result = cityService.getCity(city.getId());

        //THEN
        assertEquals(result,Optional.ofNullable(city));
        assertThat(result).isNotNull();
    }

    @Test
    public void testGetCityByName(){

        //GIVEN
        city = CityMocks.mockCity();
        cityDTO = CityMocks.mockCityDTO();

        List<City> cities = new ArrayList<>();
        cities.add(city);

        List<CityDTO> cityDTOS = new ArrayList<>();
        cityDTOS.add(cityDTO);

        //WHEN
        when(cityRepository.findCityByCity_name(city.getCity_name())).thenReturn(cities);
        when(cityMapper.mapToCityDTO(city)).thenReturn(cityDTO);

        //THEN
        List<CityDTO> result = cityService.getCityByName(city.getCity_name());
        assertEquals(result,cityDTOS);
        assertThat(result).isNotNull();
    }

    @Test
    public void testCityException() {
        //GIVEN
        city = null;
        cityDTO = null;

        List<City> cities = new ArrayList<>();

        List<CityDTO> cityDTOS = new ArrayList<>();

        //WHEN
        when(cityRepository.findCityByCity_name("Bucuresti")).thenReturn(cities);

        //THEN
        CityNotFloundException cityNotFloundException = assertThrows(CityNotFloundException.class, () -> cityService.getCityByName("Bucuresti"));
        assertEquals(String.format(CITY_NOT_FOUND, "Bucuresti"), cityNotFloundException.getMessage());
    }

    @Test
    public void testDeleteCity(){

        //GIVEN
        city = CityMocks.mockCity();

        //WHEN
        when(cityRepository.findById(city.getId())).thenReturn(Optional.ofNullable(city));

        boolean result = cityService.delete(city.getId());

        //THEN
        assertTrue(result);
    }

    @Test
    public void testDeleteCityException(){

        //GIVEN
        city = null;

        //WHEN
        when(cityRepository.findById(1L)).thenReturn(Optional.ofNullable(city));

        //THEN
        CityNotFloundException cityNotFloundException = assertThrows(CityNotFloundException.class, () -> cityService.delete(1L));
        assertEquals(String.format(CITY_ID_NOT_FOUND, 1L), cityNotFloundException.getMessage());
    }
}
