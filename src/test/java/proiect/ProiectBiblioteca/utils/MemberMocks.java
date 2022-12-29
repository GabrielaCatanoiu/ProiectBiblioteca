package proiect.ProiectBiblioteca.utils;

import proiect.ProiectBiblioteca.dto.MemberDTO;
import proiect.ProiectBiblioteca.entity.Member;

public class MemberMocks {

    public static Member mockMember(){

        return Member.builder()
                .id(1L)
                .m_name("Ciurea")
                .surname("Alexandru")
                .address("Str.Nicolae Cosbuc nr.6, bl.C3, sc.B")
                .city("Craiova")
                .email("ciureaalaxandru@gamil.com")
                .phone("0778965332")
                .build();
    }

    public static MemberDTO mockMemberDTO(){

        return MemberDTO.builder()
                .id(1L)
                .m_name("Ciurea")
                .surname("Alexandru")
                .address("Str.Nicolae Cosbuc nr.6, bl.C3, sc.B")
                .city("Craiova")
                .email("ciureaalaxandru@gamil.com")
                .phone("0778965332")
                .build();
    }
}
