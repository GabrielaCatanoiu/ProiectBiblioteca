package proiect.ProiectBiblioteca.mapper;

import org.springframework.stereotype.Component;
import proiect.ProiectBiblioteca.dto.MemberDTO;
import proiect.ProiectBiblioteca.entity.Member;

@Component
public class MemberMapper {

    public Member mapToMember(MemberDTO memberDTO)
    {
        return Member.builder()
                .m_name(memberDTO.getM_name())
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
                .m_name(member.getM_name())
                .surname(member.getSurname())
                .address(member.getAddress())
                .city(member.getCity())
                .email(member.getEmail())
                .phone(member.getPhone())
                .build();
    }
}
