package proiect.ProiectBiblioteca.servicesTests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import proiect.ProiectBiblioteca.dto.MemberDTO;
import proiect.ProiectBiblioteca.entity.Member;
import proiect.ProiectBiblioteca.exceptions.MemberNotFoundException;
import proiect.ProiectBiblioteca.mapper.MemberMapper;
import proiect.ProiectBiblioteca.repositories.MemberRepository;
import proiect.ProiectBiblioteca.services.MemberService;
import proiect.ProiectBiblioteca.utils.MemberMocks;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static proiect.ProiectBiblioteca.constants.ProjectConstants.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class MemberServiceTests {

    @InjectMocks
    MemberService memberService;

    @Mock
    MemberRepository memberRepository;

    @Mock
    MemberMapper memberMapper;

    Member member;

    MemberDTO memberDTO;

    @Test
    public void testAddMember(){

        //GIVEN
        member = MemberMocks.mockMember();
        memberDTO = MemberMocks.mockMemberDTO();

        //WHEN
        when(memberRepository.save(member)).thenReturn(member);
        when(memberMapper.mapToMemberDTO(member)).thenReturn(memberDTO);
        when(memberMapper.mapToMember(memberDTO)).thenReturn(member);

        MemberDTO result = memberService.addMember(memberDTO);

        //THEN
        assertTrue(result.getEmail().equals(memberDTO.getEmail()));
        assertThat(result.getPhone()).isNotNull();
        assertNotNull(result);
        verifyNoMoreInteractions(memberRepository,memberMapper);
    }

    @Test
    public void testGetAllMembers(){

        //GIVEN
        member = MemberMocks.mockMember();

        List<Member> members = new ArrayList<>();
        members.add(member);

        //WHEN
        when(memberRepository.findAll()).thenReturn(members);

        List<Member> result = memberService.getAllMembers();

        //THEN
        assertEquals(result,members);
        assertThat(result).isNotNull();
    }

    @Test
    public void testGetByEmail(){

        //GIVEN
        member = MemberMocks.mockMember();
        memberDTO = MemberMocks.mockMemberDTO();

        List<Member> members = new ArrayList<>();
        members.add(member);

        List<MemberDTO> memberDTOS = new ArrayList<>();
        memberDTOS.add(memberDTO);

        //WHEN
        when(memberRepository.findMemberByEmail(member.getEmail())).thenReturn(members);
        when(memberMapper.mapToMemberDTO(member)).thenReturn(memberDTO);

        //THEN
        List<MemberDTO> result = memberService.getByEmail(member.getEmail());
        assertEquals(result,memberDTOS);
        assertThat(result).isNotNull();
    }

    @Test
    public void testMemberException() {
        //GIVEN
        member = null;

        List<Member> members = new ArrayList<>();

        //WHEN
        when(memberRepository.findMemberByEmail("ciucaalexandru@gmail.com")).thenReturn(members);

        //THEN
        MemberNotFoundException memberNotFoundException = assertThrows(MemberNotFoundException.class, () -> memberService.getByEmail("ciucaalexandru@gmail.com"));
        assertEquals(String.format(MEMBER_NOT_FOUND, "ciucaalexandru@gmail.com"), memberNotFoundException.getMessage());
    }

    @Test
    public void testGetMember(){

        //GIVEN
        member = MemberMocks.mockMember();

        //WHEN
        when(memberRepository.findById(member.getId())).thenReturn(Optional.ofNullable(member));

        Optional<Member> result = memberService.getMember(member.getId());

        //THEN
        assertEquals(result,Optional.ofNullable(member));
        assertThat(result).isNotNull();

    }

    @Test
    public void testDeleteMember(){

        //GIVEN
        member = MemberMocks.mockMember();

        //WHEN
        when(memberRepository.findById(member.getId())).thenReturn(Optional.ofNullable(member));

        boolean result = memberService.delete(member.getId());

        //THEN
        assertTrue(result);
    }

    @Test
    public void testDeleteMemberException(){

        //GIVEN
        member = null;

        //WHEN
        when(memberRepository.findById(1L)).thenReturn(Optional.ofNullable(member));

        //THEN
        MemberNotFoundException memberNotFoundException = assertThrows(MemberNotFoundException.class, () -> memberService.delete(1L));
        assertEquals(String.format(MEMBER_ID_NOT_FOUND, 1L), memberNotFoundException.getMessage());
    }

    @Test
    public void testUpdateMember(){

        //GIVEN
        member = MemberMocks.mockMember();
        memberDTO = MemberMocks.mockMemberDTO();
        memberDTO.setEmail("ciucalex@gmail.com");

        //WHEN
        when(memberRepository.getReferenceById(member.getId())).thenReturn(member);
        Member member1 = MemberMocks.mockMember();
        member1.setEmail("ciucalex@gmail.com");
        when(memberMapper.mapToMemberDTO(member1)).thenReturn(memberDTO);
        when(memberRepository.save(member)).thenReturn(member1);

        //THEN
        MemberDTO result = memberService.updateMember(1L, "ciucalex@gmail.com");
        assertThat(result).isNotNull();
        assertTrue(result.getEmail().equals("ciucalex@gmail.com"));
    }

    @Test
    public void testUpdateMemberException() {
        //GIVEN
        member = null;
        memberDTO = null;

        //WHEN
        when(memberRepository.getReferenceById(1L)).thenReturn(member);

        //THEN
        MemberNotFoundException memberNotFoundException = assertThrows(MemberNotFoundException.class, () -> memberService.updateMember(1L, "ciucalex@gmail.com"));
        assertEquals(String.format(MEMBER_ID_NOT_FOUND, 1L), memberNotFoundException.getMessage());
    }
}
