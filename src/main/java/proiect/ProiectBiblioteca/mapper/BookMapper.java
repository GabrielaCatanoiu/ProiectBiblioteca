package proiect.ProiectBiblioteca.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import proiect.ProiectBiblioteca.dto.BookDTO;
import proiect.ProiectBiblioteca.entity.Book;

@Component
public class BookMapper {

    @Autowired
    private AuthorMapper authorMapper;

    @Autowired
    private PublishingHouseMapper publishingHouseMapper;

    @Autowired
    private CategoryMapper categoryMapper;

    public Book mapToBook(BookDTO bookDTO)
    {
        if((bookDTO.getAuthorDTO()!=null) && (bookDTO.getPublishingHouseDTO()!=null) && (bookDTO.getCategoryDTO()!=null))
        {
            return Book.builder()
                    .id(bookDTO.getId())
                    .title(bookDTO.getTitle())
                    .year_published(bookDTO.getYear_published())
                    .author(authorMapper.mapToAuthor(bookDTO.getAuthorDTO()))
                    .publishingHouse(publishingHouseMapper.mapToPublishingHouse(bookDTO.getPublishingHouseDTO()))
                    .category(categoryMapper.mapToCategory(bookDTO.getCategoryDTO()))
                    .build();
        }
        else
        {
            return Book.builder()
                    .id(bookDTO.getId())
                    .title(bookDTO.getTitle())
                    .year_published(bookDTO.getYear_published())
                    .build();
        }

    }

    public BookDTO mapToBookDTO(Book book)
    {
        if((book.getAuthor()!=null) && (book.getPublishingHouse()!=null) && (book.getCategory()!=null))
        {
            return BookDTO.builder()
                    .id(book.getId())
                    .title(book.getTitle())
                    .year_published(book.getYear_published())
                    .authorDTO(authorMapper.mapToAuthorDTO(book.getAuthor()))
                    .publishingHouseDTO(publishingHouseMapper.mapToPublishingHouseDTO(book.getPublishingHouse()))
                    .categoryDTO(categoryMapper.mapToCategoryDTO(book.getCategory()))
                    .build();
        }
       else
        {
            return BookDTO.builder()
                    .id(book.getId())
                    .title(book.getTitle())
                    .year_published(book.getYear_published())
                    .build();
        }
    }
}
