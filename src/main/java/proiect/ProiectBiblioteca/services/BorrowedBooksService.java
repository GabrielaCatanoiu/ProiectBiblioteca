package proiect.ProiectBiblioteca.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proiect.ProiectBiblioteca.dto.BorrowedBooksDTO;
import proiect.ProiectBiblioteca.entity.BorrowedBooks;
import proiect.ProiectBiblioteca.entity.Member;
import proiect.ProiectBiblioteca.exceptions.BorrowedBookNotFoundException;
import proiect.ProiectBiblioteca.exceptions.MemberNotFoundException;
import proiect.ProiectBiblioteca.mapper.BorrowedBooksMapper;
import proiect.ProiectBiblioteca.repositories.BorrowedBooksRepository;
import proiect.ProiectBiblioteca.repositories.MemberRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static proiect.ProiectBiblioteca.constants.ProjectConstants.*;

@Service
@RequiredArgsConstructor
public class BorrowedBooksService implements BorrowedBooksServiceImpl{

    @Autowired
    private BorrowedBooksRepository borrowedBooksRepository;

    @Autowired
    private BorrowedBooksMapper borrowedBooksMapper;

    @Autowired
    private MemberRepository memberRepository;

    @Override
    public List<BorrowedBooks> getAllBorrowedBooks()
    {
        List<BorrowedBooks> allBorrowedBooks = new ArrayList<>();
        for (int i=0; i<borrowedBooksRepository.findAll().size();i++)
        {
            allBorrowedBooks.add(borrowedBooksRepository.findAll().get(i));
        }
        return allBorrowedBooks;
    }

    @Override
    public Optional<BorrowedBooks> getBorrowedBook(Long id)
    {
        return borrowedBooksRepository.findById(id);
    }

    @Override
    public List<BorrowedBooksDTO> getBorrowedBookByDate(String date_due)
    {
        List<BorrowedBooksDTO> borrowedBooksDTOS = borrowedBooksRepository.findAllByDate_due(date_due).stream()
                .map(barrow ->borrowedBooksMapper.mapToBorrowedBooksDTO(barrow)).collect(Collectors.toList());
        if(borrowedBooksDTOS.isEmpty())
        {
            throw new MemberNotFoundException(String.format(BORROWED_BOOK_NOT_FOUND, date_due));
        }
        return borrowedBooksDTOS;
    }

    @Override
    public BorrowedBooksDTO addBorrowedBook(BorrowedBooksDTO borrowedBooksDTO)
    {
        BorrowedBooks borrowedBooks = borrowedBooksMapper.mapToBorrowedBooks(borrowedBooksDTO);
        if(borrowedBooksDTO.getMemberDTO() != null)
        {
            Optional<Member> member = memberRepository.findById(borrowedBooksDTO.getMemberDTO().getId());
            System.out.println(member);
            if(member.isEmpty())
            {
                throw new MemberNotFoundException(String.format(MEMBER_NOT_FOUND,borrowedBooks.getMember()));
            }
            borrowedBooks.setMember(member.get());
        }
        System.out.println(borrowedBooks);
        return borrowedBooksMapper.mapToBorrowedBooksDTO(borrowedBooksRepository.save(borrowedBooks));
    }

    @Override
    public void deleteBorrowedBook(Long id)
    {
        Optional<BorrowedBooks> borrowedBooksFound = borrowedBooksRepository.findById(id);
        if(borrowedBooksFound.isPresent())
        {
            borrowedBooksRepository.delete(borrowedBooksFound.get());
        }
        else
        {
            throw new BorrowedBookNotFoundException(String.format(BORROWED_BOOK_ID_NOT_FOUND,id));
        }
    }

    @Override
    public BorrowedBooksDTO updateBorrowedBook(Long id, String newDate_returned)
    {
        BorrowedBooks borrowedBooks = borrowedBooksRepository.getReferenceById(id);
        if(borrowedBooks==null)
        {
            throw new BorrowedBookNotFoundException(String.format(BORROWED_BOOK_ID_NOT_FOUND,id));
        }
        borrowedBooks.setDate_returned(newDate_returned);
        return borrowedBooksMapper.mapToBorrowedBooksDTO(borrowedBooksRepository.save(borrowedBooks));
    }

}
