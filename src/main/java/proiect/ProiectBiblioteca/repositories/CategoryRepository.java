package proiect.ProiectBiblioteca.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import proiect.ProiectBiblioteca.entity.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Override
    Optional<Category> findById(Long id);

    @Query("SELECT CA FROM Category CA WHERE CA.category_name = :category_name")
    List<Category> findCategoriesByCategory_name(String category_name);
}
