package proiect.ProiectBiblioteca.controllerTests;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import proiect.ProiectBiblioteca.controllers.MemberController;
import proiect.ProiectBiblioteca.dto.AuthorDTO;
import proiect.ProiectBiblioteca.dto.MemberDTO;
import proiect.ProiectBiblioteca.entity.Member;
import proiect.ProiectBiblioteca.services.MemberService;
import proiect.ProiectBiblioteca.utils.AuthorMocks;
import proiect.ProiectBiblioteca.utils.MemberMocks;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static proiect.ProiectBiblioteca.constants.ProjectConstants.AUTHOR_WAS_DELETED;
import static proiect.ProiectBiblioteca.constants.ProjectConstants.MEMBER_WAS_DELETED;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(controllers = MemberController.class)
public class MemberControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    MemberService memberService;

    Member member;

    MemberDTO memberDTO;

    @Test
    public void addMemberTest() throws Exception {

        //GIVEN
        memberDTO = MemberMocks.mockMemberDTO();
        member = MemberMocks.mockMember();

        //WHEN
        when(memberService.addMember(memberDTO)).thenReturn(memberDTO);

        //THEN
        mockMvc.perform(post("/members/add")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(memberDTO)))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(memberDTO)));
    }

    @Test
    public void getAllMembersTest() throws Exception{

        //GIVEN
        member = MemberMocks.mockMember();

        List<Member> members = new ArrayList<>();
        members.add(member);

        //WHEN
        when(memberService.getAllMembers()).thenReturn(members);

        //THEN
        mockMvc.perform(get("/members/allMembers"))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(members)));
    }

    @Test
    public void getMemberByEmailTest() throws Exception {

        //GIVEN
        memberDTO = MemberMocks.mockMemberDTO();
        member = MemberMocks.mockMember();

        List<MemberDTO> memberDTOS = new ArrayList<>();
        memberDTOS.add(memberDTO);

        //WHEN
        when(memberService.getByEmail(member.getEmail())).thenReturn(memberDTOS);

        //THEN
        mockMvc.perform(get("/members/getByEmail/"+member.getEmail()))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(memberDTOS)));
    }

    @Test
    public void getOneMemberTest() throws Exception {

        //GIVEN
        memberDTO = MemberMocks.mockMemberDTO();
        member = MemberMocks.mockMember();

        //WHEN
        when(memberService.getMember(member.getId())).thenReturn(Optional.ofNullable(member));

        //THEN
        mockMvc.perform(get("/members/getById/"+member.getId()))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(member)));
    }

    @Test
    public void deleteMemberTest() throws Exception{

        //GIVEN
        memberDTO = MemberMocks.mockMemberDTO();
        member = MemberMocks.mockMember();

        //WHEN
        when(memberService.delete(member.getId())).thenReturn(true);

        //THEN
        mockMvc.perform(delete("/members/deleteMember/"+member.getId()))
                .andExpect(status().isOk())
                .andExpect(content().string(String.format(MEMBER_WAS_DELETED,member.getId())));
    }

    @Test
    public void updateEmailMemberTest() throws Exception{

        //GIVEN
        memberDTO = MemberMocks.mockMemberDTO();
        member = MemberMocks.mockMember();

        //WHEN
        when(memberService.updateMember(member.getId(),member.getEmail())).thenReturn(memberDTO);

        //THEN
        mockMvc.perform(put("/members/update/"+member.getId()+"/"+member.getEmail()))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(member)));
    }
}
