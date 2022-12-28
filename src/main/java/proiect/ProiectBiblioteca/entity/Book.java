package proiect.ProiectBiblioteca.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinTable(name = "author_book",
            joinColumns = {
                    @JoinColumn(name = "book", referencedColumnName = "id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "author", referencedColumnName = "id")
            }
    )
    private Author author;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinTable(name = "city_publishing_house_book",
            joinColumns = {
                    @JoinColumn(name = "book", referencedColumnName = "id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "city_publishing_house", referencedColumnName = "id")
            }
    )
    private PublishingHouse publishingHouse;

    @Column(name = "title")
    private String title;

    @Column(name = "year_published")
    private String year_published;
}
