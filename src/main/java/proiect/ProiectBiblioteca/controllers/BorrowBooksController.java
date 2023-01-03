package proiect.ProiectBiblioteca.controllers;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import proiect.ProiectBiblioteca.dto.BorrowedBooksDTO;
import proiect.ProiectBiblioteca.entity.BorrowedBooks;
import proiect.ProiectBiblioteca.exceptions.BorrowedBookNotFoundException;
import proiect.ProiectBiblioteca.services.BorrowedBooksService;


import java.util.Date;
import java.util.List;
import java.util.Optional;

import static proiect.ProiectBiblioteca.constants.ProjectConstants.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/borrowed_books")
public class BorrowBooksController {

    @Autowired
    private BorrowedBooksService borrowedBooksService;

    @GetMapping("/all")
    @ApiOperation("Provide all borrowed books from the library")
    public ResponseEntity<List<BorrowedBooks>> getAllBorrowedBooks()
    {
        return ResponseEntity.ok(borrowedBooksService.getAllBorrowedBooks());
    }

    @GetMapping("/getById/{id}")
    @ApiOperation("Search for information about a borrowed book by id")
    public ResponseEntity<Optional<BorrowedBooks>> getOneBorrowedBook(@PathVariable Long id)
    {
        return ResponseEntity.ok(borrowedBooksService.getBorrowedBook(id));
    }

    @GetMapping("/getByDate/{date_due}")
    @ApiOperation("Search for information about all the books borrowed on a certain date")
    public ResponseEntity<List<BorrowedBooksDTO>> getAllBorrowedBookByDate(@PathVariable String date_due)
    {
        return ResponseEntity.ok(borrowedBooksService.getBorrowedBookByDate(date_due));
    }

    @PostMapping("/add")
    @ApiOperation("Add a new information abdout a borrowed book from the library")
    public ResponseEntity<BorrowedBooksDTO> addBorrowedBook(@RequestBody BorrowedBooksDTO borrowedBooksDTO)
    {
        return ResponseEntity.ok(borrowedBooksService.addBorrowedBook(borrowedBooksDTO));
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation("Delete the data about a certain borrowed book")
    public ResponseEntity<String> deleteBorrowedBook(@PathVariable Long id)
    {
        borrowedBooksService.delete(id);
        return ResponseEntity.ok(String.format(BORROWED_BOOK_WAS_DELETED,id));
    }

    @PutMapping("/update/{id}/{date_returned}")
    @ApiOperation("Updates the information about a book borrowed on a certain date")
    public ResponseEntity<BorrowedBooksDTO> updateBorrowedBook(@PathVariable Long id, @PathVariable String date_returned)
    {
        return ResponseEntity.ok(borrowedBooksService.updateBorrowedBook(id,date_returned));
    }
}
