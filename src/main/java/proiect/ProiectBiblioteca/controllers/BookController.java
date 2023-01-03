package proiect.ProiectBiblioteca.controllers;

import io.swagger.annotations.ApiOperation;
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
    @ApiOperation("Add a new book to the library")
    public ResponseEntity<BookDTO> addBooks(@RequestBody BookDTO bookDTO)
    {
        return ResponseEntity.ok(bookService.addBook(bookDTO));
    }

    @GetMapping("/all")
    @ApiOperation("Provide a list with information about all the books in the library")
    public ResponseEntity<List<Book>> getAllBooks()
    {
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    @GetMapping("/getBookById/{id}")
    @ApiOperation("Search for information about a book by id")
    public ResponseEntity<Optional<Book>> getBookById(@PathVariable Long id)
    {
        return ResponseEntity.ok(bookService.getOneBook(id));
    }

    @GetMapping("/getBookByTitle/{title}")
    @ApiOperation("Search for information about a book by title")
    public ResponseEntity<List<BookDTO>> getBookByTitle(@PathVariable String title)
    {
        return ResponseEntity.ok(bookService.geByTitle(title));
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation("Remove a book from the library")
    public ResponseEntity<String> deleteBookById(@PathVariable Long id)
    {
        bookService.delete(id);
        return ResponseEntity.ok(String.format(BOOK_WAS_DELETED,id));
    }

    @PutMapping("/update/{id}/{title}")
    @ApiOperation("Updates the information about a book in the library")
    public ResponseEntity<BookDTO> updateBooks(@PathVariable Long id, @PathVariable String title)
    {
        return ResponseEntity.ok(bookService.updateBookName(id,title));
    }
}
