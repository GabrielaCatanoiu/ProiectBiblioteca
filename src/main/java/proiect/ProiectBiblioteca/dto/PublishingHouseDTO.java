package proiect.ProiectBiblioteca.dto;

import lombok.*;
import proiect.ProiectBiblioteca.validator.OnlyLetters;

@Data
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PublishingHouseDTO {

    private Long id;

    @NonNull
    @OnlyLetters
    private String publishing_name;

    private CityDTO cityDTO;
}
