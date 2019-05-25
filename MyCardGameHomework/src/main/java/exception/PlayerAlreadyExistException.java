package main.java.exception;

/**
 * Created by Steven Shahbazi on 24/05/2019.
 */
public class PlayerAlreadyExistException extends Exception {
    public PlayerAlreadyExistException(String message){
        super(message);
    }
}
