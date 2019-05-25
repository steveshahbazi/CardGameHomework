package main.java.exception;

/**
 * Created by Steven Shahbazi on 24/05/2019.
 */
public class DeckAlreadyExistException extends Exception {
    public DeckAlreadyExistException(String message){
        super(message);
    }
}
