package proiect.ProiectBiblioteca.dto;

import lombok.*;
import proiect.ProiectBiblioteca.validator.OnlyLetters;

import javax.persistence.Column;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberDTO {

    private Long id;
    @NonNull
    @OnlyLetters
    private String m_name;

    @NonNull
    @OnlyLetters
    private String surname;

    @NonNull
    private String address;

    @NonNull
    @OnlyLetters
    private String city;

    @NonNull
    private String email;

    @NonNull
    @OnlyLetters
    private String phone;
}
