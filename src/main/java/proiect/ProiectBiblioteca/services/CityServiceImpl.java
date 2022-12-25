package proiect.ProiectBiblioteca.services;

import proiect.ProiectBiblioteca.dto.CityDTO;
import proiect.ProiectBiblioteca.entity.City;

import java.util.List;
import java.util.Optional;

public interface CityServiceImpl {

    public CityDTO addCity(CityDTO cityDTO);
    public Optional<City> getCity(Long id);
    public List<CityDTO> getCityByName(String city_name);
    public void deleteCity(Long id);
}
