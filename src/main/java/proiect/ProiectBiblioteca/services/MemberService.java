package proiect.ProiectBiblioteca.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proiect.ProiectBiblioteca.constants.ProjectConstants;
import proiect.ProiectBiblioteca.dto.MemberDTO;
import proiect.ProiectBiblioteca.entity.Member;
import proiect.ProiectBiblioteca.exceptions.MemberNotFoundException;
import proiect.ProiectBiblioteca.mapper.MemberMapper;
import proiect.ProiectBiblioteca.repositories.MemberRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import static proiect.ProiectBiblioteca.constants.ProjectConstants.MEMBER_ID_NOT_FOUND;
import static proiect.ProiectBiblioteca.constants.ProjectConstants.MEMBER_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class MemberService implements MemberServiceImpl{

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private MemberMapper memberMapper;

    @Override
    public MemberDTO addMember(MemberDTO memberDTO)
    {
        return memberMapper.mapToMemberDTO(memberRepository.save(memberMapper.mapToMember(memberDTO)));
    }

    @Override
    public List<Member> getAllMembers()
    {
        List<Member> members = new ArrayList<>();
        for (int i = 0; i < memberRepository.findAll().size(); i++)
        {
            members.add(memberRepository.findAll().get(i));
        }
        return members;
    }

    @Override
    public List<MemberDTO> getByEmail(String email)
    {
        List<MemberDTO> memberDTOS = memberRepository.findMemberByEmail(email).stream()
                .map(member -> memberMapper.mapToMemberDTO(member)).collect(Collectors.toList());
        if (memberDTOS.isEmpty())
        {
            throw new MemberNotFoundException(String.format(MEMBER_NOT_FOUND, email));
        }
        return memberDTOS;
    }

    public Optional<Member> getMember(Long id)
    {
        return memberRepository.findById(id);
    }

    @Override
    public void deleteMember(Long id)
    {
        Optional<Member> memberFound = memberRepository.findById(id);
        if(memberFound.isPresent())
        {
            memberRepository.delete(memberFound.get());
        }
        else
        {
            throw new MemberNotFoundException(String.format(MEMBER_ID_NOT_FOUND,id));
        }
    }

    @Override
    public MemberDTO updateMember(Long id, String newEmail)
    {
        Member member = memberRepository.getReferenceById(id);
        if(member==null)
        {
            throw new MemberNotFoundException(String.format(MEMBER_ID_NOT_FOUND, id));
        }
        member.setEmail(newEmail);
        return memberMapper.mapToMemberDTO(memberRepository.save(member));
    }

}
