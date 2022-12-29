package proiect.ProiectBiblioteca.controllerTests;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import proiect.ProiectBiblioteca.controllers.CityController;
import proiect.ProiectBiblioteca.dto.CityDTO;
import proiect.ProiectBiblioteca.dto.MemberDTO;
import proiect.ProiectBiblioteca.entity.City;
import proiect.ProiectBiblioteca.services.CityService;
import proiect.ProiectBiblioteca.utils.CityMocks;
import proiect.ProiectBiblioteca.utils.MemberMocks;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static proiect.ProiectBiblioteca.constants.ProjectConstants.CITY_WAS_DELETED;
import static proiect.ProiectBiblioteca.constants.ProjectConstants.MEMBER_WAS_DELETED;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(controllers = CityController.class)
public class CityControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    CityService cityService;

    City city;

    CityDTO cityDTO;

    @Test
    public void addCityTest() throws Exception {

        //GIVEN
        cityDTO = CityMocks.mockCityDTO();
        city = CityMocks.mockCity();

        //WHEN
        when(cityService.addCity(cityDTO)).thenReturn(cityDTO);

        //THEN
        mockMvc.perform(post("/city/add")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(cityDTO)))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(cityDTO)));
    }

    @Test
    public void getOneCityTest() throws Exception {

        //GIVEN
        cityDTO = CityMocks.mockCityDTO();
        city = CityMocks.mockCity();

        //WHEN
        when(cityService.getCity(city.getId())).thenReturn(Optional.ofNullable(city));

        //THEN
        mockMvc.perform(get("/city/getCity/"+city.getId()))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(city)));
    }

    @Test
    public void getCityByNameTest() throws Exception {

        //GIVEN
        cityDTO = CityMocks.mockCityDTO();
        city = CityMocks.mockCity();

        List<CityDTO> cityDTOS = new ArrayList<>();
        cityDTOS.add(cityDTO);

        //WHEN
        when(cityService.getCityByName(city.getCity_name())).thenReturn(cityDTOS);

        //THEN
        mockMvc.perform(get("/city/getByName/"+city.getCity_name()))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(cityDTOS)));
    }

    @Test
    public void deleteCityTest() throws Exception{

        //GIVEN
        cityDTO = CityMocks.mockCityDTO();
        city = CityMocks.mockCity();

        //WHEN
        when(cityService.delete(city.getId())).thenReturn(true);

        //THEN
        mockMvc.perform(delete("/city/delete/"+city.getId()))
                .andExpect(status().isOk())
                .andExpect(content().string(String.format(CITY_WAS_DELETED,city.getId())));
    }
}
