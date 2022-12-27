package proiect.ProiectBiblioteca.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proiect.ProiectBiblioteca.dto.BookDTO;
import proiect.ProiectBiblioteca.entity.Book;
import proiect.ProiectBiblioteca.exceptions.BookNotFoundException;
import proiect.ProiectBiblioteca.mapper.BookMapper;
import proiect.ProiectBiblioteca.repositories.BookRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static proiect.ProiectBiblioteca.constants.ProjectConstants.BOOK_ID_NOT_FOUND;
import static proiect.ProiectBiblioteca.constants.ProjectConstants.BOOK_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class BookService implements BookServiceImpl {

    @Autowired
    private BookMapper bookMapper;

    @Autowired
    private BookRepository bookRepository;

    @Override
    public BookDTO addBook(BookDTO bookDTO)
    {
        return bookMapper.mapToBookDTO(bookRepository.save(bookMapper.mapToBook(bookDTO)));
    }

    @Override
    public List<Book> getAllBooks()
    {
        List<Book> books = new ArrayList<>();
        for (int i=0;i< bookRepository.findAll().size();i++)
        {
            books.add(bookRepository.findAll().get(i));
        }
        return books;
    }

    @Override
    public Optional<Book> getOneBook(Long id)
    {
        return bookRepository.findById(id);
    }

    @Override
    public List<BookDTO> geByTitle(String title)
    {
        List<BookDTO> bookDTOS = bookRepository.findBookByTitle(title).stream()
                .map(titlu -> bookMapper.mapToBookDTO(titlu)).collect(Collectors.toList());
        if(bookDTOS.isEmpty())
        {
            throw new BookNotFoundException(String.format(BOOK_NOT_FOUND,title));
        }
        return bookDTOS;
    }

    @Override
    public void deleteBook(Long id)
    {
        Optional<Book> bookFound = bookRepository.findById(id);
        if(bookFound.isPresent())
        {
            bookRepository.delete(bookFound.get());
        }
        else
        {
            throw new BookNotFoundException(String.format(BOOK_ID_NOT_FOUND,id));
        }
    }

    @Override
    public BookDTO updateBookName(Long id, String newTitle)
    {
        Book book = bookRepository.getReferenceById(id);
        if (book==null)
        {
            throw new BookNotFoundException(String.format(BOOK_ID_NOT_FOUND,id));
        }
        book.setTitle(newTitle);
        return bookMapper.mapToBookDTO(bookRepository.save(book));
    }
}
