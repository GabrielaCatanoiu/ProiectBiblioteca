package proiect.ProiectBiblioteca.controllers;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import proiect.ProiectBiblioteca.dto.CategoryDTO;
import proiect.ProiectBiblioteca.entity.Category;
import proiect.ProiectBiblioteca.services.CategoryService;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/add")
    @ApiOperation("Add a new category of literature")
    public ResponseEntity<CategoryDTO> addNewCategory(@RequestBody CategoryDTO categoryDTO)
    {
        return ResponseEntity.ok(categoryService.addCategory(categoryDTO));
    }

    @GetMapping("/getById/{id}")
    @ApiOperation("Search for information about a book category by id")
    public ResponseEntity<Optional<Category>> getOneCategory(@PathVariable Long id)
    {
        return ResponseEntity.ok(categoryService.getCategory(id));
    }

    @GetMapping("/getByName/{category_name:[a-zA-Z ]*}")
    @ApiOperation("Search for information about a book category by name")
    private ResponseEntity<List<CategoryDTO>> getCategoryByName(@PathVariable String category_name)
    {
        return ResponseEntity.ok(categoryService.getByName(category_name));
    }
}
