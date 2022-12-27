package proiect.ProiectBiblioteca.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "publishing_house")
public class PublishingHouse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinTable(name = "city_publishing_house",
            joinColumns = {
                    @JoinColumn(name = "publishing_house", referencedColumnName = "id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "city", referencedColumnName = "id")
            }
    )
    private City city;

    @Column(name = "publishing_name")
    private String publishing_name;
}
