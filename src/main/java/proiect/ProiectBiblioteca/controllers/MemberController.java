package proiect.ProiectBiblioteca.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import proiect.ProiectBiblioteca.dto.MemberDTO;
import proiect.ProiectBiblioteca.entity.Member;
import proiect.ProiectBiblioteca.services.MemberService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

    @Autowired
    private MemberService memberService;

    @PostMapping("/add")
    public ResponseEntity<MemberDTO> addMember(@RequestBody MemberDTO memberDTO)
    {
        return ResponseEntity.ok(memberService.addMember(memberDTO));
    }

    @GetMapping("/{email}")
    public ResponseEntity<List<MemberDTO>> getMemberByEmail(@PathVariable String email){
        return ResponseEntity.ok(memberService.getByEmail(email));
    }

    @GetMapping("/oneMember")
    public ResponseEntity<Optional<Member>> getMember(@PathVariable Long id)
    {
        return ResponseEntity.ok(memberService.getMember(id));
    }

    @DeleteMapping("/deleteMember")
    public void deleteMember(@PathVariable Long id)
    {

        memberService.deleteMember(id);
    }

    @PutMapping("/update/{id}/{email}")
    public ResponseEntity<MemberDTO> updateEmailMember(@PathVariable Long id, @PathVariable String email){
        return ResponseEntity.ok(memberService.updateMember(id, email));
    }


}
