package proiect.ProiectBiblioteca.exceptions;

public class BorrowedBookNotFoundException extends RuntimeException{

    public BorrowedBookNotFoundException(String message) {
        super(message);
    }
}
