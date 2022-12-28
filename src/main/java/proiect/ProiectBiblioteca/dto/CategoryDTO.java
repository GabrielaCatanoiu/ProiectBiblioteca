package proiect.ProiectBiblioteca.dto;

import lombok.*;
import proiect.ProiectBiblioteca.validator.OnlyLetters;

import javax.persistence.Column;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CategoryDTO {

    private Long id;

    @NonNull
    @OnlyLetters
    private String category_name;
}
