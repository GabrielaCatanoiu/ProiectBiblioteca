package proiect.ProiectBiblioteca.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import proiect.ProiectBiblioteca.entity.City;

import java.util.List;
import java.util.Optional;

@Repository
public interface CityRepository extends JpaRepository<City, Long>{

    @Override
    Optional<City> findById(Long id);

    @Query("SELECT C FROM City C WHERE C.city_name = :city_name")
    List<City> findCityByCity_name(String city_name);
}
