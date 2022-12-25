package proiect.ProiectBiblioteca.exceptions;

public class AuthorNotFoundException extends RuntimeException{

    public AuthorNotFoundException(String message){
        super(message);
    }
}
