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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinTable(name = "city_publishing_house",
            joinColumns = {
                    @JoinColumn(name = "city", referencedColumnName = "id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "publishing_house", referencedColumnName = "id")
            }
    )
    private PublishingHouse publishingHouse;

    @Column(name = "city_name")
    private String city_name;

    @Column(name = "country")
    private String country;

    @Column(name = "district")
    private String  district;
}
