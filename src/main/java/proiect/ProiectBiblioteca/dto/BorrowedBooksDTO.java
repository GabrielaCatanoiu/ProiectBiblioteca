package proiect.ProiectBiblioteca.dto;

import lombok.*;

import java.util.Date;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BorrowedBooksDTO {

    private Long id;

    @NonNull
    private String date_due;

    @NonNull
    private String date_returned;

    private MemberDTO memberDTO;
    private BookDTO bookDTO;
}
