package proiect.ProiectBiblioteca.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proiect.ProiectBiblioteca.dto.CategoryDTO;
import proiect.ProiectBiblioteca.entity.Category;
import proiect.ProiectBiblioteca.exceptions.CategorieNotFoundException;
import proiect.ProiectBiblioteca.mapper.CategoryMapper;
import proiect.ProiectBiblioteca.repositories.CategoryRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static proiect.ProiectBiblioteca.constants.ProjectConstants.CATEGORY_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class CategoryService implements CategoryServiceImpl{

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public CategoryDTO addCategory(CategoryDTO categoryDTO)
    {
        return categoryMapper.mapToCategoryDTO(categoryRepository.save(categoryMapper.mapToCategory(categoryDTO)));
    }

    @Override
    public Optional<Category> getCategory(Long id)
    {
        return categoryRepository.findById(id);
    }

    @Override
    public List<CategoryDTO> getByName(String category_name)
    {
        List<CategoryDTO> categoryDTOS = categoryRepository.findCategoriesByCategory_name(category_name).stream()
                .map(name-> categoryMapper.mapToCategoryDTO(name)).collect(Collectors.toList());
        if (categoryDTOS.isEmpty())
        {
            throw new CategorieNotFoundException(String.format(CATEGORY_NOT_FOUND,category_name));
        }
        return categoryDTOS;
    }
}
