package proiect.ProiectBiblioteca.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthorDTO {

    private Long id;
    private String author_name;
    private String author_surname;
    private String date_birth;
    private String date_death;
}
