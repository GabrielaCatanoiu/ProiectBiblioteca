package proiect.ProiectBiblioteca.utils;

import proiect.ProiectBiblioteca.dto.AuthorDTO;
import proiect.ProiectBiblioteca.entity.Author;

public class AuthorMocks {

    public static Author mockAuthor()
    {
        return Author.builder()
                .id(1L)
                .author_name("Eminescu")
                .author_surname("Mihai")
                .date_birth("1850-01-15")
                .date_death("1889-06-15")
                .build();
    }

    public static AuthorDTO mockAuthorDTO()
    {
        return AuthorDTO.builder()
                .id(1L)
                .author_name("Eminescu")
                .author_surname("Mihai")
                .date_birth("1850-01-15")
                .date_death("1889-06-15")
                .build();
    }
}
