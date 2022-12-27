package proiect.ProiectBiblioteca.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import proiect.ProiectBiblioteca.entity.Book;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    @Override
    Optional<Book> findById(Long id);

    @Query("SELECT bo FROM Book bo WHERE bo.title = :title")
    List<Book> findBookByTitle(String title);
}
