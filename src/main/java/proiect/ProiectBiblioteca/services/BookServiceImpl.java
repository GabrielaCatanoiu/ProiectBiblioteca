package proiect.ProiectBiblioteca.services;

import proiect.ProiectBiblioteca.dto.BookDTO;
import proiect.ProiectBiblioteca.entity.Book;

import java.util.List;
import java.util.Optional;

public interface BookServiceImpl {

    public BookDTO addBook(BookDTO bookDTO);
    public List<Book> getAllBooks();
    public Optional<Book> getOneBook(Long id);
    public List<BookDTO> geByTitle(String title);
    public void deleteBook(Long id);
    public BookDTO updateBookName(Long id, String newTitle);
}
