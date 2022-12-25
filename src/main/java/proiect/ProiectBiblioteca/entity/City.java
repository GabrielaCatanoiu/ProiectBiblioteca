package proiect.ProiectBiblioteca.entity;

import lombok.*;

import javax.persistence.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Table(name = "city")
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "city_name")
    private String city_name;

    @Column(name = "country")
    private String country;

    @Column(name = "district")
    private String  district;
}
