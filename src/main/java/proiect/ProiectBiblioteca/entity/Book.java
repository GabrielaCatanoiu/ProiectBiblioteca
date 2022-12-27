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

    @Column(name = "title")
    private String title;

    @Column(name = "year_published")
    private String year_published;
}
