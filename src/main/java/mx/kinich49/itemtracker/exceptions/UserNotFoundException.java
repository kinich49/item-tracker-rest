package mx.kinich49.itemtracker.exceptions;

public class UserNotFoundException extends Exception {

    public UserNotFoundException(long userId) {
        super(String.format("User with id %d not found", userId));
    }
}
