package proiect.ProiectBiblioteca.mapper;

import org.springframework.stereotype.Component;
import proiect.ProiectBiblioteca.dto.CityDTO;
import proiect.ProiectBiblioteca.entity.City;

@Component
public class CityMapper {

    public City mapToCity(CityDTO cityDTO)
    {
        return City.builder()
                .city_name(cityDTO.getCity_name())
                .country(cityDTO.getCountry())
                .district(cityDTO.getDistrict())
                .build();
    }

    public CityDTO mapToCityDTO(City city)
    {
        return CityDTO.builder()
                .id(city.getId())
                .city_name(city.getCity_name())
                .country(city.getCountry())
                .district(city.getDistrict())
                .build();
    }
}
