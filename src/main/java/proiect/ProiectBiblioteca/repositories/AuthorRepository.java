package proiect.ProiectBiblioteca.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import proiect.ProiectBiblioteca.entity.Author;
import proiect.ProiectBiblioteca.entity.Member;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    List<Author> findAuthorByAuthor_nameAndAuthor_surname(String author_name, String author_surname);

    @Override
    Optional<Author> findById(Long id);
}
