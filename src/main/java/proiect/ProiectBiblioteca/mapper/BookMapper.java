package proiect.ProiectBiblioteca.mapper;

import org.springframework.stereotype.Component;
import proiect.ProiectBiblioteca.dto.BookDTO;
import proiect.ProiectBiblioteca.entity.Book;

@Component
public class BookMapper {

    public Book mapToBook(BookDTO bookDTO)
    {
        return Book.builder()
                .title(bookDTO.getTitle())
                .year_published(bookDTO.getYear_published())
                .build();
    }

    public BookDTO mapToBookDTO(Book book)
    {
        return BookDTO.builder()
                .id(book.getId())
                .title(book.getTitle())
                .year_published(book.getYear_published())
                .build();
    }

}
