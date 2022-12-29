package proiect.ProiectBiblioteca.controllerTests;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import proiect.ProiectBiblioteca.controllers.PublishingHouseController;
import proiect.ProiectBiblioteca.dto.MemberDTO;
import proiect.ProiectBiblioteca.dto.PublishingHouseDTO;
import proiect.ProiectBiblioteca.entity.Member;
import proiect.ProiectBiblioteca.entity.PublishingHouse;
import proiect.ProiectBiblioteca.services.PublishingHouseService;
import proiect.ProiectBiblioteca.utils.MemberMocks;
import proiect.ProiectBiblioteca.utils.PublishingHouseMocks;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static proiect.ProiectBiblioteca.constants.ProjectConstants.MEMBER_WAS_DELETED;
import static proiect.ProiectBiblioteca.constants.ProjectConstants.PUBLISHING_HOUSE_WAS_DELETED;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(controllers = PublishingHouseController.class)
public class PublishingHouseControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private PublishingHouseService publishingHouseService;

    PublishingHouse publishingHouse;

    PublishingHouseDTO publishingHouseDTO;

    @Test
    public void addPublishingHouseTest() throws Exception {

        //GIVEN
        publishingHouseDTO = PublishingHouseMocks.mockPublishingHouseDTO();
        publishingHouse = PublishingHouseMocks.mockPublishingHouse();

        //WHEN
        when(publishingHouseService.addPublishingHouse(publishingHouseDTO)).thenReturn(publishingHouseDTO);

        //THEN
        mockMvc.perform(post("/publishingHouse/add")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(publishingHouseDTO)))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(publishingHouseDTO)));
    }

    @Test
    public void getAllPublishingHouseTest() throws Exception{

        //GIVEN
        publishingHouse = PublishingHouseMocks.mockPublishingHouse();

        List<PublishingHouse> publishingHouses = new ArrayList<>();
        publishingHouses.add(publishingHouse);

        //WHEN
        when(publishingHouseService.getAllPublishingHouse()).thenReturn(publishingHouses);

        //THEN
        mockMvc.perform(get("/publishingHouse/all"))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(publishingHouses)));
    }

    @Test
    public void getOnePublicationTest() throws Exception {

        //GIVEN
        publishingHouseDTO = PublishingHouseMocks.mockPublishingHouseDTO();
        publishingHouse = PublishingHouseMocks.mockPublishingHouse();

        //WHEN
        when(publishingHouseService.getPublishById(publishingHouse.getId())).thenReturn(Optional.ofNullable(publishingHouse));

        //THEN
        mockMvc.perform(get("/publishingHouse/getPublishHouseById/"+publishingHouse.getId()))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(publishingHouse)));
    }

    @Test
    public void ggetPublishHouseByNameTest() throws Exception {

        //GIVEN
        publishingHouseDTO = PublishingHouseMocks.mockPublishingHouseDTO();
        publishingHouse = PublishingHouseMocks.mockPublishingHouse();

        List<PublishingHouseDTO> publishingHouseDTOS = new ArrayList<>();
        publishingHouseDTOS.add(publishingHouseDTO);

        //WHEN
        when(publishingHouseService.getPublishByName(publishingHouse.getPublishing_name())).thenReturn(publishingHouseDTOS);

        //THEN
        mockMvc.perform(get("/publishingHouse/getPublishHouseByName/"+publishingHouse.getPublishing_name()))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(publishingHouseDTOS)));
    }

    @Test
    public void deletePublicationTest() throws Exception{

        //GIVEN
        publishingHouseDTO = PublishingHouseMocks.mockPublishingHouseDTO();
        publishingHouse = PublishingHouseMocks.mockPublishingHouse();

        //WHEN
        when(publishingHouseService.delete(publishingHouse.getId())).thenReturn(true);

        //THEN
        mockMvc.perform(delete("/publishingHouse/delete/"+publishingHouse.getId()))
                .andExpect(status().isOk())
                .andExpect(content().string(String.format(PUBLISHING_HOUSE_WAS_DELETED,publishingHouse.getId())));
    }
}
