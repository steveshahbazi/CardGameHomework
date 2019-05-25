package main.java.exception;

/**
 * Created by Steven Shahbazi on 24/05/2019.
 */
public class PlayerNotExistException extends Exception {
    public PlayerNotExistException(String message){
        super(message);
    }
}
