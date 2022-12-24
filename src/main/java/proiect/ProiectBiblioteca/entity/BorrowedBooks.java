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
@Table(name = "borrowedBooks")
public class BorrowedBooks {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinTable(name = "member_borrowedBooks",
            joinColumns = {
                    @JoinColumn(name = "member", referencedColumnName = "id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "borrowedBooks", referencedColumnName = "id")
            }
    )
    private Member member;

    @Column(name = "date_due")
    private Date date_due;

    @Column(name = "date_returned")
    private Date date_returned;

}