package proiect.ProiectBiblioteca.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import proiect.ProiectBiblioteca.entity.PublishingHouse;

import java.util.List;
import java.util.Optional;

@Repository
public interface PublishingHouseRepository extends JpaRepository<PublishingHouse, Long> {

    @Override
    @Query("SELECT P FROM PublishingHouse P WHERE P.id = :id")
    Optional<PublishingHouse> findById(Long id);

    @Query("SELECT P FROM PublishingHouse P WHERE P.publishing_name = :publishing_name")
    List<PublishingHouse> findByPublishing_name(String publishing_name);
}
