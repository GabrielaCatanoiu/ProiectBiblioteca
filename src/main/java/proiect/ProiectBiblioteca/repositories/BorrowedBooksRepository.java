package proiect.ProiectBiblioteca.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import proiect.ProiectBiblioteca.entity.BorrowedBooks;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface BorrowedBooksRepository extends JpaRepository<BorrowedBooks, Long> {

    @Override
    Optional<BorrowedBooks> findById(Long id);
    List<BorrowedBooks> findAllByDate_due(Date date_due);
}
