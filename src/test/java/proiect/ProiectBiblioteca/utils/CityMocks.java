package proiect.ProiectBiblioteca.utils;

import proiect.ProiectBiblioteca.dto.CityDTO;
import proiect.ProiectBiblioteca.entity.City;

public class CityMocks {

    public static City mockCity(){

        return City.builder()
                .id(1L)
                .city_name("Slatina")
                .country("Romania")
                .district("Olt")
                .build();
    }

    public static CityDTO mockCityDTO(){

        return CityDTO.builder()
                .id(1L)
                .city_name("Slatina")
                .country("Romania")
                .district("Olt")
                .build();
    }
}
