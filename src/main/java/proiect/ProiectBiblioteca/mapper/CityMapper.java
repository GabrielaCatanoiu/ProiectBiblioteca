package proiect.ProiectBiblioteca.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import proiect.ProiectBiblioteca.dto.CityDTO;
import proiect.ProiectBiblioteca.entity.City;

@Component
public class CityMapper {

    @Autowired
    private PublishingHouseMapper publishingHouseMapper;

    public City mapToCity(CityDTO cityDTO)
    {
        if(cityDTO.getPublishingHouseDTO()!= null)
        {
            return City.builder()
                    .id(cityDTO.getId())
                    .city_name(cityDTO.getCity_name())
                    .country(cityDTO.getCountry())
                    .district(cityDTO.getDistrict())
                    .publishingHouse(publishingHouseMapper.mapToPublishingHouse(cityDTO.getPublishingHouseDTO()))
                    .build();
        }
        else
        {
            return City.builder()
                    .id(cityDTO.getId())
                    .city_name(cityDTO.getCity_name())
                    .country(cityDTO.getCountry())
                    .district(cityDTO.getDistrict())
                    .build();
        }
    }

    public CityDTO mapToCityDTO(City city)
    {
        if (city.getPublishingHouse()!= null)
        {
            return CityDTO.builder()
                    .id(city.getId())
                    .city_name(city.getCity_name())
                    .country(city.getCountry())
                    .district(city.getDistrict())
                    .publishingHouseDTO(publishingHouseMapper.mapToPublishingHouseDTO(city.getPublishingHouse()))
                    .build();
        }
       else
        {
            return CityDTO.builder()
                    .id(city.getId())
                    .city_name(city.getCity_name())
                    .country(city.getCountry())
                    .district(city.getDistrict())
                    .build();
        }
    }
}
