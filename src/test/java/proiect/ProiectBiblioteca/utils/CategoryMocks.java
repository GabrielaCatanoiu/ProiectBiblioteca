package proiect.ProiectBiblioteca.utils;

import proiect.ProiectBiblioteca.dto.CategoryDTO;
import proiect.ProiectBiblioteca.entity.Category;

public class CategoryMocks {

    public static Category mockCategory(){

        return Category.builder()
                .id(1L)
                .category_name("Fantasy")
                .build();
    }

    public static CategoryDTO mockCategoryDTO(){

        return CategoryDTO.builder()
                .id(1L)
                .category_name("Fantasy")
                .build();
    }
}
