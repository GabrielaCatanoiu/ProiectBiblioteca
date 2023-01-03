package proiect.ProiectBiblioteca.controllers;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import proiect.ProiectBiblioteca.dto.AuthorDTO;
import proiect.ProiectBiblioteca.entity.Author;
import proiect.ProiectBiblioteca.exceptions.AuthorNotFoundException;
import proiect.ProiectBiblioteca.services.AuthorService;

import java.util.List;
import java.util.Optional;

import static proiect.ProiectBiblioteca.constants.ProjectConstants.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/authors")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @PostMapping("/add")
    public ResponseEntity<AuthorDTO> addAuthor(@RequestBody AuthorDTO authorDTO)
    {
        return ResponseEntity.ok(authorService.addAuthor(authorDTO));
    }

    @GetMapping("/getAuthor/{id}")
    @ApiOperation("Search for information about an author by id")
    public ResponseEntity<Optional<Author>> getOneAuthor(@PathVariable Long id)
    {
        return ResponseEntity.ok(authorService.getAutor(id));
    }

    @GetMapping("/getByNameAndSurname/{name:[a-zA-Z ]*}/{surname:[a-zA-Z ]*}")
    @ApiOperation("Search for information about an author by name and surname")
    public ResponseEntity<List<AuthorDTO>> getByNameAndSurname(@PathVariable String name, @PathVariable String surname)
    {
        return ResponseEntity.ok(authorService.getAuthorByNameAndSurname(name,surname));
    }

    @GetMapping("/all")
    @ApiOperation("Provide a list with information about all the authors in the library")
    public ResponseEntity<List<Author>> getAllAuthors()
    {
        return ResponseEntity.ok(authorService.getAllAuthors());
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation("Remove an author from the library")
    public ResponseEntity<String> deleteAuthor(@PathVariable Long id)
    {
        authorService.delete(id);
        return ResponseEntity.ok(String.format(AUTHOR_WAS_DELETED,id));
    }
}
