package proiect.ProiectBiblioteca.controllers;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import proiect.ProiectBiblioteca.dto.MemberDTO;
import proiect.ProiectBiblioteca.entity.Member;
import proiect.ProiectBiblioteca.exceptions.MemberNotFoundException;
import proiect.ProiectBiblioteca.services.MemberService;

import java.util.List;
import java.util.Optional;

import static proiect.ProiectBiblioteca.constants.ProjectConstants.*;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

    @Autowired
    private MemberService memberService;

    @PostMapping("/add")
    @ApiOperation("Add information about a new member to the library")
    public ResponseEntity<MemberDTO> addMember(@RequestBody MemberDTO memberDTO)
    {
        return ResponseEntity.ok(memberService.addMember(memberDTO));
    }

    @GetMapping("/allMembers")
    @ApiOperation("Provide a list of all members in the library")
    public ResponseEntity<List<Member>> getMembers()
    {
        return ResponseEntity.ok(memberService.getAllMembers());
    }

    @GetMapping("/getByEmail/{email}")
    @ApiOperation("Search information about a member by email")
    public ResponseEntity<List<MemberDTO>> getMemberByEmail(@PathVariable String email){
        return ResponseEntity.ok(memberService.getByEmail(email));
    }

    @GetMapping("/getById/{id}")
    @ApiOperation("Search information about a member by id")
    public ResponseEntity<Optional<Member>> getMember(@PathVariable Long id)
    {
        return ResponseEntity.ok(memberService.getMember(id));
    }

    @DeleteMapping("/deleteMember/{id}")
    @ApiOperation("Delete a user who has not borrowed books for a long time")
    public ResponseEntity<String> deleteMember(@PathVariable Long id)
    {
        try {
            memberService.delete(id);
            return ResponseEntity.ok(String.format(MEMBER_WAS_DELETED, id));
        }
        catch (MemberNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(String.format(MEMBER_ID_NOT_FOUND, id));
        }
    }

    @PutMapping("/update/{id}/{email}")
    @ApiOperation("Updating a user's email")
    public ResponseEntity<MemberDTO> updateEmailMember(@PathVariable Long id, @PathVariable String email){
        return ResponseEntity.ok(memberService.updateMember(id,email));
    }
}
