package proiect.ProiectBiblioteca.dto;

import lombok.*;
import proiect.ProiectBiblioteca.validator.OnlyLetters;

import javax.persistence.Column;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthorDTO {

    private Long id;

    @NonNull
    @OnlyLetters
    private String author_name;

    @NonNull
    @OnlyLetters
    private String author_surname;

    @NonNull
    private String date_birth;

    private String date_death;
}
