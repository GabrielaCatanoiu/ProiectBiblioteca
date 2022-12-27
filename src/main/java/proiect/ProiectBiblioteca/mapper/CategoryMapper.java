package proiect.ProiectBiblioteca.mapper;

import org.springframework.stereotype.Component;
import proiect.ProiectBiblioteca.dto.CategoryDTO;
import proiect.ProiectBiblioteca.entity.Category;

@Component
public class CategoryMapper {

    public Category mapToCategory(CategoryDTO categoryDTO)
    {
        return Category.builder()
                .category_name(categoryDTO.getCategory_name())
                .build();
    }

    public CategoryDTO mapToCategoryDTO(Category category)
    {
        return CategoryDTO.builder()
                .id(category.getId())
                .category_name(category.getCategory_name())
                .build();
    }
}
