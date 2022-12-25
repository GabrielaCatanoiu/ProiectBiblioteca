package proiect.ProiectBiblioteca.entity;

import lombok.*;

import javax.persistence.*;

@Setter
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Table(name = "author")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "author_name")
    private String author_name;

    @Column(name = "author_surname")
    private String author_surname;

    @Column(name = "date_birth")
    private String date_birth;

    @Column(name = "date_death")
    private String date_death;
}
