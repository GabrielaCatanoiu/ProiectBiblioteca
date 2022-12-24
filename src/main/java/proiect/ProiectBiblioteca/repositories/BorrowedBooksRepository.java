package proiect.ProiectBiblioteca.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import proiect.ProiectBiblioteca.entity.BorrowedBooks;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.hibernate.loader.Loader.SELECT;

@Repository
public interface BorrowedBooksRepository extends JpaRepository<BorrowedBooks, Long> {

    @Override
    Optional<BorrowedBooks> findById(Long id);
    @Query("SELECT B FROM BorrowedBooks B WHERE B.date_due = :date_due")
    List<BorrowedBooks> findAllByDate_due(Date date_due);
}
