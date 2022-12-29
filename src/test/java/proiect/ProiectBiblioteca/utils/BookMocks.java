package proiect.ProiectBiblioteca.utils;

import proiect.ProiectBiblioteca.dto.*;
import proiect.ProiectBiblioteca.entity.*;

public class BookMocks {

    public static Book mockBook(){

        return Book.builder()
                .id(1L)
                .title("Luceafarul")
                .year_published("1886-05-21")
                .author(Author.builder()
                        .id(1L)
                        .author_name("Eminescu")
                        .author_surname("Mihai")
                        .date_birth("1850-01-15")
                        .date_death("1889-06-15")
                        .build())
                .publishingHouse(PublishingHouse.builder()
                        .id(1L)
                        .publishing_name("Astra")
                        .city(City.builder()
                                .id(1L)
                                .city_name("Slatina")
                                .country("Romania")
                                .district("Olt")
                                .build())
                        .build())
                .category(Category.builder()
                        .id(1L)
                        .category_name("Fantasy")
                        .build())
                .build();
    }

    public static BookDTO mockBookDTO(){

        return BookDTO.builder()
                .id(1L)
                .title("Luceafarul")
                .year_published("1886-05-21")
                .authorDTO(AuthorDTO.builder()
                        .id(1L)
                        .author_name("Eminescu")
                        .author_surname("Mihai")
                        .date_birth("1850-01-15")
                        .date_death("1889-06-15")
                        .build())
                .publishingHouseDTO(PublishingHouseDTO.builder()
                        .id(1L)
                        .publishing_name("Astra")
                        .cityDTO(CityDTO.builder()
                                .id(1L)
                                .city_name("Slatina")
                                .country("Romania")
                                .district("Olt")
                                .build())
                        .build())
                .categoryDTO(CategoryDTO.builder()
                        .id(1L)
                        .category_name("Fantasy")
                        .build())
                .build();
    }
}
