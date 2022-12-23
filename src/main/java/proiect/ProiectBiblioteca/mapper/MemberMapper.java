package proiect.ProiectBiblioteca.mapper;

import org.springframework.stereotype.Component;
import proiect.ProiectBiblioteca.dto.MemberDTO;
import proiect.ProiectBiblioteca.entity.Member;

@Component
public class MemberMapper {

    public Member mapToMember(MemberDTO memberDTO)
    {
        return Member.builder()
                .name(memberDTO.getName())
                .surname(memberDTO.getSurname())
                .address(memberDTO.getAddress())
                .city(memberDTO.getCity())
                .email(memberDTO.getEmail())
                .phone(memberDTO.getPhone())
                .build();
    }

    public MemberDTO mapToMemberDTO(Member member)
    {
        return MemberDTO.builder()
                .id(member.getId())
                .name(member.getName())
                .surname(member.getSurname())
                .email(member.getEmail())
                .build();
    }
}
