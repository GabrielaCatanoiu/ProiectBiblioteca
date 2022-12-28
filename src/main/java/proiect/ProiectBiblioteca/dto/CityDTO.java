package proiect.ProiectBiblioteca.dto;

import lombok.*;
import proiect.ProiectBiblioteca.validator.OnlyLetters;

import javax.persistence.Column;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CityDTO {

    private Long id;

    @NonNull
    @OnlyLetters
    private String city_name;

    @NonNull
    @OnlyLetters
    private String country;

    @NonNull
    @OnlyLetters
    private String  district;
}
