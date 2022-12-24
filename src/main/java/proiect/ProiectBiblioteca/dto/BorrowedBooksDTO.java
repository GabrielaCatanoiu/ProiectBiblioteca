package proiect.ProiectBiblioteca.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BorrowedBooksDTO {

    private Long id;
    private Date date_due;
    private Date date_returned;

}
