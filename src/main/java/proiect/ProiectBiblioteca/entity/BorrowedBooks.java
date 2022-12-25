package proiect.ProiectBiblioteca.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;


@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "borrowed_books")
public class BorrowedBooks {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinTable(name = "member_borrowedBooks",
            joinColumns = {
                    @JoinColumn(name = "borrowedBooks", referencedColumnName = "id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "member", referencedColumnName = "id")
            }
    )
    private Member member;

    @Column(name = "date_due")
    private String date_due;

    @Column(name = "date_returned")
    private String date_returned;

}
