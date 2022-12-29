package proiect.ProiectBiblioteca.servicesTests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import proiect.ProiectBiblioteca.dto.CategoryDTO;
import proiect.ProiectBiblioteca.entity.Category;
import proiect.ProiectBiblioteca.exceptions.CategorieNotFoundException;
import proiect.ProiectBiblioteca.mapper.CategoryMapper;
import proiect.ProiectBiblioteca.repositories.CategoryRepository;
import proiect.ProiectBiblioteca.services.CategoryService;
import proiect.ProiectBiblioteca.utils.CategoryMocks;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static proiect.ProiectBiblioteca.constants.ProjectConstants.CATEGORY_NOT_FOUND;
import static proiect.ProiectBiblioteca.constants.ProjectConstants.MEMBER_NOT_FOUND;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class CategoryServiceTests {

    @InjectMocks
    CategoryService categoryService;

    @Mock
    CategoryRepository categoryRepository;

    @Mock
    CategoryMapper categoryMapper;

    Category category;

    CategoryDTO categoryDTO;

    @Test
    public void testAddCategory(){
        //GIVEN
        category = CategoryMocks.mockCategory();
        categoryDTO = CategoryMocks.mockCategoryDTO();

        //WHEN
        when(categoryRepository.save(category)).thenReturn(category);
        when(categoryMapper.mapToCategoryDTO(category)).thenReturn(categoryDTO);
        when(categoryMapper.mapToCategory(categoryDTO)).thenReturn(category);

        CategoryDTO result = categoryService.addCategory(categoryDTO);

        //THEN
        assertTrue(result.getCategory_name().equals(categoryDTO.getCategory_name()));
        assertThat(result.getCategory_name()).isNotNull();
        assertNotNull(result);
        verifyNoMoreInteractions(categoryRepository,categoryMapper);
    }

    @Test
    public void testGetCategory(){

        //GIVEN
        category = CategoryMocks.mockCategory();

        //WHEN
        when(categoryRepository.findById(category.getId())).thenReturn(Optional.ofNullable(category));

        Optional<Category> result = categoryService.getCategory(category.getId());

        //THEN
        assertEquals(result,Optional.ofNullable(category));
        assertThat(result).isNotNull();
    }

    @Test
    public void testGetByCategory(){

        //GIVEN
        category = CategoryMocks.mockCategory();
        categoryDTO = CategoryMocks.mockCategoryDTO();

        List<Category> categories = new ArrayList<>();
        categories.add(category);

        List<CategoryDTO> categoryDTOS = new ArrayList<>();
        categoryDTOS.add(categoryDTO);

        //WHEN
        when(categoryRepository.findCategoriesByCategory_name(category.getCategory_name())).thenReturn(categories);
        when(categoryMapper.mapToCategoryDTO(category)).thenReturn(categoryDTO);

        //THEN
        List<CategoryDTO> result = categoryService.getByName(category.getCategory_name());
        assertEquals(result,categoryDTOS);
        assertThat(result).isNotNull();
    }

    @Test
    public void testCategoryException() {
        //GIVEN
        category = null;
        categoryDTO = null;

        List<Category> categories = new ArrayList<>();

        List<CategoryDTO> categoryDTOS = new ArrayList<>();

        //WHEN
        when(categoryRepository.findCategoriesByCategory_name("Fantasy")).thenReturn(categories);

        //THEN
        CategorieNotFoundException categorieNotFoundException = assertThrows(CategorieNotFoundException.class, () -> categoryService.getByName("Fantasy"));
        assertEquals(String.format(CATEGORY_NOT_FOUND, "Fantasy"), categorieNotFoundException.getMessage());
    }
}
