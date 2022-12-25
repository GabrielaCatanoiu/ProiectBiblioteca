package proiect.ProiectBiblioteca.mapper;

import org.springframework.stereotype.Component;
import proiect.ProiectBiblioteca.dto.AuthorDTO;
import proiect.ProiectBiblioteca.entity.Author;

@Component
public class AuthorMapper {

    public Author mapToAuthor(AuthorDTO authorDTO)
    {
        return Author.builder()
                .author_name(authorDTO.getAuthor_name())
                .author_surname(authorDTO.getAuthor_surname())
                .date_birth(authorDTO.getDate_birth())
                .date_death(authorDTO.getDate_death())
                .build();
    }

    public AuthorDTO mapToAuthorDTO(Author author)
    {
        return AuthorDTO.builder()
                .id(author.getId())
                .author_name(author.getAuthor_name())
                .author_surname(author.getAuthor_surname())
                .date_birth(author.getDate_birth())
                .date_death(author.getDate_death())
                .build();
    }
}
