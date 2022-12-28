package proiect.ProiectBiblioteca.controllers;

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
    public ResponseEntity<Optional<Author>> getOneAuthor(@PathVariable Long id)
    {
        return ResponseEntity.ok(authorService.getAutor(id));
    }

    @GetMapping("/getByNameAndSurname/{name}/{surname}")
    public ResponseEntity<List<AuthorDTO>> getByNameAndSurname(@PathVariable String name, @PathVariable String surname)
    {
        return ResponseEntity.ok(authorService.getAuthorByNameAndSurname(name,surname));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Author>> getAllAuthors()
    {
        return ResponseEntity.ok(authorService.getAllAuthors());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteAuthor(@PathVariable Long id)
    {
        try {
            authorService.delete(id);
            return ResponseEntity.ok(String.format(AUTHOR_WAS_DELETED,id));
        }
        catch (AuthorNotFoundException ex)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(String.format(AUTHOR_ID_NOT_FOUND, id));
        }
    }
}
