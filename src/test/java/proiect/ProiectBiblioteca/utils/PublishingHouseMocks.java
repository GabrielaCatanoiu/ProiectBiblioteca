package proiect.ProiectBiblioteca.utils;

import proiect.ProiectBiblioteca.dto.CityDTO;
import proiect.ProiectBiblioteca.dto.PublishingHouseDTO;
import proiect.ProiectBiblioteca.entity.City;
import proiect.ProiectBiblioteca.entity.PublishingHouse;

public class PublishingHouseMocks {

    public static PublishingHouse mockPublishingHouse()
    {
        return PublishingHouse.builder()
                .id(1L)
                .publishing_name("Astra")
                .city(City.builder()
                        .id(1L)
                        .city_name("Slatina")
                        .country("Romania")
                        .district("Olt")
                        .build())
                .build();
    }

    public static PublishingHouseDTO mockPublishingHouseDTO()
    {
        return PublishingHouseDTO.builder()
                .id(1L)
                .publishing_name("Astra")
                .cityDTO(CityDTO.builder()
                        .id(1L)
                        .city_name("Slatina")
                        .country("Romania")
                        .district("Olt")
                        .build())
                .build();
    }
}
