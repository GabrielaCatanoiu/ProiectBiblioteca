package proiect.ProiectBiblioteca.dto;

import lombok.*;

import javax.persistence.Column;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookDTO {

    private Long id;

    @NonNull
    private String title;

    @NonNull
    private String year_published;

    private AuthorDTO authorDTO;
    private PublishingHouseDTO publishingHouseDTO;
    private CategoryDTO categoryDTO;
}
