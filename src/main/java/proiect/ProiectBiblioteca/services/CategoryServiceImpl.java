package proiect.ProiectBiblioteca.services;

import proiect.ProiectBiblioteca.dto.CategoryDTO;
import proiect.ProiectBiblioteca.entity.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryServiceImpl {

    public CategoryDTO addCategory(CategoryDTO categoryDTO);
    public Optional<Category> getCategory(Long id);
    public List<CategoryDTO> getByName(String category_name);
}
