package proiect.ProiectBiblioteca.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import proiect.ProiectBiblioteca.dto.BookDTO;
import proiect.ProiectBiblioteca.entity.Book;
import proiect.ProiectBiblioteca.exceptions.BookNotFoundException;
import proiect.ProiectBiblioteca.services.BookService;

import java.util.List;
import java.util.Optional;

import static proiect.ProiectBiblioteca.constants.ProjectConstants.BOOK_ID_NOT_FOUND;
import static proiect.ProiectBiblioteca.constants.ProjectConstants.BOOK_WAS_DELETED;

@RestController
@RequiredArgsConstructor
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping("/add")
    public ResponseEntity<BookDTO> addBooks(@RequestBody BookDTO bookDTO)
    {
        return ResponseEntity.ok(bookService.addBook(bookDTO));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Book>> getAllBooks()
    {
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    @GetMapping("/getBookById/{id}")
    public ResponseEntity<Optional<Book>> getBookById(@PathVariable Long id)
    {
        return ResponseEntity.ok(bookService.getOneBook(id));
    }

    @GetMapping("/getBookByTitle/{title}")
    public ResponseEntity<List<BookDTO>> getBookByTitle(@PathVariable String title)
    {
        return ResponseEntity.ok(bookService.geByTitle(title));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteBookById(@PathVariable Long id)
    {
        try {
            bookService.delete(id);
            return ResponseEntity.ok(String.format(BOOK_WAS_DELETED,id));
        }
        catch (BookNotFoundException ex)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(String.format(BOOK_ID_NOT_FOUND,id));
        }
    }

    @PutMapping("/update/{id}/{title}")
    public ResponseEntity<BookDTO> updateBooks(@PathVariable Long id, @PathVariable String title)
    {
        return ResponseEntity.ok(bookService.updateBookName(id,title));
    }
}
