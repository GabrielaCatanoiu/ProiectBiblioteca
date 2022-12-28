package proiect.ProiectBiblioteca.services;

import proiect.ProiectBiblioteca.dto.MemberDTO;
import proiect.ProiectBiblioteca.entity.Member;

import java.util.List;
import java.util.Optional;

public interface MemberServiceImpl {

    public MemberDTO addMember(MemberDTO memberDTO);
    public List<Member> getAllMembers();
    public List<MemberDTO> getByEmail(String email);
    public Optional<Member> getMember(Long id);
    public MemberDTO updateMember(Long id, String email);
}
