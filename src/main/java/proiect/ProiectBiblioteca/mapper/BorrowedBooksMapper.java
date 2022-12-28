package proiect.ProiectBiblioteca.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import proiect.ProiectBiblioteca.dto.BorrowedBooksDTO;
import proiect.ProiectBiblioteca.entity.BorrowedBooks;

@Component
public class BorrowedBooksMapper {

    @Autowired
    private MemberMapper memberMapper;

    @Autowired
    private BookMapper bookMapper;

    public BorrowedBooks mapToBorrowedBooks(BorrowedBooksDTO borrowedBooksDTO)
    {
        if((borrowedBooksDTO.getMemberDTO() != null) && (borrowedBooksDTO.getBookDTO() != null))
        {
            return BorrowedBooks.builder()
                    .id(borrowedBooksDTO.getId())
                    .date_due(borrowedBooksDTO.getDate_due())
                    .date_returned(borrowedBooksDTO.getDate_returned())
                    .member(memberMapper.mapToMember(borrowedBooksDTO.getMemberDTO()))
                    .book(bookMapper.mapToBook(borrowedBooksDTO.getBookDTO()))
                    .build();
        }
        else
        {
            return BorrowedBooks.builder()
                    .id(borrowedBooksDTO.getId())
                    .date_due(borrowedBooksDTO.getDate_due())
                    .date_returned(borrowedBooksDTO.getDate_returned())
                    .build();
        }
    }

    public BorrowedBooksDTO mapToBorrowedBooksDTO(BorrowedBooks borrowedBooks)
    {
        if((borrowedBooks.getMember() != null) && (borrowedBooks.getBook() != null)) {
            return BorrowedBooksDTO.builder()
                    .id(borrowedBooks.getId())
                    .date_due(borrowedBooks.getDate_due())
                    .date_returned(borrowedBooks.getDate_returned())
                    .memberDTO(memberMapper.mapToMemberDTO(borrowedBooks.getMember()))
                    .bookDTO(bookMapper.mapToBookDTO(borrowedBooks.getBook()))
                    .build();
        }
        else
        {
            return BorrowedBooksDTO.builder()
                    .id(borrowedBooks.getId())
                    .date_due(borrowedBooks.getDate_due())
                    .date_returned(borrowedBooks.getDate_returned())
                    .build();
        }
    }
}
