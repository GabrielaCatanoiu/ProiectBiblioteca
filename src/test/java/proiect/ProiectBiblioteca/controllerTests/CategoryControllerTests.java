package proiect.ProiectBiblioteca.controllerTests;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import proiect.ProiectBiblioteca.controllers.CategoryController;
import proiect.ProiectBiblioteca.dto.CategoryDTO;
import proiect.ProiectBiblioteca.dto.MemberDTO;
import proiect.ProiectBiblioteca.entity.Category;
import proiect.ProiectBiblioteca.services.CategoryService;
import proiect.ProiectBiblioteca.utils.CategoryMocks;
import proiect.ProiectBiblioteca.utils.MemberMocks;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(controllers = CategoryController.class)
public class CategoryControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    CategoryService categoryService;

    Category category;

    CategoryDTO categoryDTO;

    @Test
    public void addCategoryTest() throws Exception {

        //GIVEN
        categoryDTO = CategoryMocks.mockCategoryDTO();
        category = CategoryMocks.mockCategory();

        //WHEN
        when(categoryService.addCategory(categoryDTO)).thenReturn(categoryDTO);

        //THEN
        mockMvc.perform(post("/category/add")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(categoryDTO)))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(categoryDTO)));
    }

    @Test
    public void getCategoryByNameTest() throws Exception {

        //GIVEN
        categoryDTO = CategoryMocks.mockCategoryDTO();
        category = CategoryMocks.mockCategory();

        List<CategoryDTO> categoryDTOS = new ArrayList<>();
        categoryDTOS.add(categoryDTO);

        //WHEN
        when(categoryService.getByName(category.getCategory_name())).thenReturn(categoryDTOS);

        //THEN
        mockMvc.perform(get("/category/getByName/"+category.getCategory_name()))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(categoryDTOS)));
    }

    @Test
    public void getOneCategoryTest() throws Exception {

        //GIVEN
        categoryDTO = CategoryMocks.mockCategoryDTO();
        category = CategoryMocks.mockCategory();

        //WHEN
        when(categoryService.getCategory(category.getId())).thenReturn(Optional.ofNullable(category));

        //THEN
        mockMvc.perform(get("/category/getById/"+category.getId()))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(category)));
    }

}
