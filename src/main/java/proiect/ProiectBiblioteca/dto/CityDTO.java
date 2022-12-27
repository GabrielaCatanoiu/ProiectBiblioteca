package proiect.ProiectBiblioteca.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CityDTO {

    private Long id;
    private String city_name;
    private String country;
    private String  district;
    private PublishingHouseDTO publishingHouseDTO;
}
