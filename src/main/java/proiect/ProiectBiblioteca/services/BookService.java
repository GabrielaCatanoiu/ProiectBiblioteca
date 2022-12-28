package proiect.ProiectBiblioteca.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proiect.ProiectBiblioteca.dto.BookDTO;
import proiect.ProiectBiblioteca.entity.Author;
import proiect.ProiectBiblioteca.entity.Book;
import proiect.ProiectBiblioteca.entity.Category;
import proiect.ProiectBiblioteca.entity.PublishingHouse;
import proiect.ProiectBiblioteca.exceptions.AuthorNotFoundException;
import proiect.ProiectBiblioteca.exceptions.BookNotFoundException;
import proiect.ProiectBiblioteca.exceptions.ConditionNotFoundException;
import proiect.ProiectBiblioteca.exceptions.PublishingHouseNotFoundException;
import proiect.ProiectBiblioteca.mapper.BookMapper;
import proiect.ProiectBiblioteca.repositories.AuthorRepository;
import proiect.ProiectBiblioteca.repositories.BookRepository;
import proiect.ProiectBiblioteca.repositories.CategoryRepository;
import proiect.ProiectBiblioteca.repositories.PublishingHouseRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static proiect.ProiectBiblioteca.constants.ProjectConstants.*;

@Service
@RequiredArgsConstructor
public class BookService implements BookServiceImpl, DeleteServiceImpl {

    @Autowired
    private BookMapper bookMapper;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private PublishingHouseRepository publishingHouseRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public BookDTO addBook(BookDTO bookDTO)
    {
        Book book = bookMapper.mapToBook(bookDTO);
        if((bookDTO.getAuthorDTO() != null) && (bookDTO.getPublishingHouseDTO() != null) && (bookDTO.getCategoryDTO() != null))
        {
            Optional<Author> author = authorRepository.findById(bookDTO.getAuthorDTO().getId());
            Optional<PublishingHouse> publishingHouse = publishingHouseRepository.findById(bookDTO.getPublishingHouseDTO().getId());
            Optional<Category> category = categoryRepository.findById(bookDTO.getCategoryDTO().getId());

            if (author.isEmpty() && publishingHouse.isEmpty() && category.isEmpty())
            {
                throw new ConditionNotFoundException(String.format(CONDITION_NOT_FOUND,book.getAuthor(),book.getPublishingHouse(),book.getCategory()));
            }
            book.setAuthor(author.get());
            book.setPublishingHouse(publishingHouse.get());
            book.setCategory(category.get());
        }
        return bookMapper.mapToBookDTO(bookRepository.save(book));
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
    public void delete(Long id)
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
